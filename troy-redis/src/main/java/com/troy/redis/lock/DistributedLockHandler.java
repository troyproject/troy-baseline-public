package com.troy.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁处理
 *
 * @author troy
 */
@Slf4j
@Component
public class DistributedLockHandler {

    /**
     * 单个业务持有锁的时间30s，防止死锁
     */
    private final static long LOCK_EXPIRE = 30 * 1000L;
    /**
     * 默认30ms尝试一次
     */
    private final static long LOCK_TRY_INTERVAL = 30L;
    /**
     * 默认尝试20s
     */
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 尝试获取全局锁
     *
     * @param lock 锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock    锁的名称
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock        锁的名称
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取锁的超时时间
     * @param tryInterval    多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false获取失败
     */
    public boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        try {
            if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())) {
                log.warn("分布式锁name或value为空 {}", lock);
                return false;
            }
            long startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime <= timeout) {
                if (redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(lock.getName().getBytes(), lock.getValue().getBytes()))) {
                    // 设置锁过期时间是为了在没有释放
                    redisTemplate.expire(lock.getName(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                } else {// 存在锁
                    log.debug("lock is exist!！！");
                }
                Thread.sleep(tryInterval);
            }
        } catch (InterruptedException e) {
            log.error("", e);
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void releaseLock(Lock lock) {
        if (!StringUtils.isEmpty(lock.getName())) {
            redisTemplate.delete(lock.getName());
        }
    }
}
