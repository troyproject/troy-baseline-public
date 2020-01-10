package com.troy.redis.lock;

import java.lang.annotation.*;

/**
 * 分布式锁--方法（如果在入参中不配置锁定对象）
 *
 * @author troy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    /**
     * redis 锁key的前缀
     *
     * @return
     */
    String lockedPrefix() default "";

    /**
     * 重试锁超时时间，20s
     *
     * @return
     */
    long timeOut() default 20000;

    /**
     * 重试锁间隔，30ms
     *
     * @return
     */
    long tryInterval() default 30;

    /**
     * key在redis里存在的时间，60S
     *
     * @return
     */
    long expireTime() default 60000;
}
