package com.troy.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理锁注解切面
 *
 * @author troy
 */
@Slf4j
@Aspect
@Component
public class CacheLockAspectHandle {

    @Autowired
    DistributedLockHandler distributedLockHandler;

    ThreadLocal<Lock> lockLocal = new ThreadLocal<>();

    /**
     * 在所有标注@CacheLock的地方切入
     *
     * @param joinPoint
     * @throws CacheLockException
     */
    @Before("@annotation(lock)")
    public void beforeExec(JoinPoint joinPoint, CacheLock lock) throws CacheLockException {
        String cacheName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object obj = getLockedObject(joinPoint);
        if (obj != null) {
            cacheName = obj.toString();
        }
        Lock distributedLock = new Lock(lock.lockedPrefix() + "_" + cacheName + "_lock");
        lockLocal.set(distributedLock);
        log.debug("before试图加锁：{}" ,distributedLock.getName());
        boolean result = distributedLockHandler.tryLock(distributedLock, lock.timeOut(), lock.tryInterval(), lock.expireTime());
        // 取锁失败
        if (!result) {
            throw new CacheLockException("加锁失败:" + distributedLock);
        }
    }

    @After("@annotation(lock)")
    public void afterExec(JoinPoint joinPoint, CacheLock lock) {
        // 解锁
        log.debug("afterExec 释放锁 {}", lockLocal.get().getName());
        // 释放锁
        distributedLockHandler.releaseLock(lockLocal.get());
    }

    /**
     * 获取需要锁定的对象（支持复杂对象及简单对象）
     *
     * @param joinPoint
     * @return
     * @throws CacheLockException
     */
    private Object getLockedObject(JoinPoint joinPoint) throws CacheLockException {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        Annotation[][] annotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();

        if (null == args || args.length == 0) {
            log.warn("方法参数为空，没有被锁定的对象");
            return null;
        }

        if (null == annotations || annotations.length == 0) {
            log.warn("没有被注解的参数");
            return null;
        }

        // 不支持多个参数加锁，只支持第一个注解为lockedObject或者lockedComplexObject的参数
        // 标记参数的位置指针
        int index = -1;
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                // 注解为LockedComplexObject
                if (annotations[i][j] instanceof LockedComplexObject) {
                    index = i;
                    try {
                        Class<? extends Object> clazz = args[i].getClass();
                        List<Object> list = new ArrayList();
                        String[] lockedFields = ((LockedComplexObject) annotations[i][j]).field();
                        boolean isSort = ((LockedComplexObject) annotations[i][j]).sort();
                        for(String lockedString : lockedFields){
                            PropertyDescriptor pd = new PropertyDescriptor((lockedString), clazz);
                            // 获得get方法
                            Method getMethod = pd.getReadMethod();
                            // 执行get方法返回一个Object;
                            Object invoke = getMethod.invoke(args[i]);
                            list.add(invoke.toString());
                        }
                        if (isSort) {
                            return list.stream().sorted().reduce((o, o2) -> o + "_" + o2).get();
                        } else {
                            return list.stream().reduce((o, o2) -> o + "_" + o2).get();
                        }
                    } catch (Exception e) {
                        log.error("获取需要锁定的对象异常", e);
                        throw new CacheLockException("注解对象中没有该属性" + ((LockedComplexObject) annotations[i][j]).field());
                    }
                }

                if (annotations[i][j] instanceof LockedObject) {
                    index = i;
                    break;
                }
            }
            // 找到第一个后直接break，不支持多参数加锁
            if (index != -1) {
                break;
            }
        }

        if (index == -1) {
            log.warn("请指定被锁定参数");
            return null;
        }

        return args[index];
    }
}
