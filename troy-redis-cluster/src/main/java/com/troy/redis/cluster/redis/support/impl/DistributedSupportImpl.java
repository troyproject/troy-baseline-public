package com.troy.redis.cluster.redis.support.impl;

import com.troy.redis.cluster.redis.support.DistributedSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * implementation for DistributedSupport
 */
@Service
public class DistributedSupportImpl implements DistributedSupport {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JedisCluster jedisCluster;
    private StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    @Override
    public Long generateId(String key) {
        final byte[] rawKey = stringRedisSerializer.serialize(key);
        Long seq = jedisCluster.incr(rawKey);
        // Jedis not supported time function,NTP server was requred
        Long current = System.currentTimeMillis();
        logger.debug("----------------generateId------{seq} is:{}", seq);
        return buildId(current / 1000, current, 0L, seq);
    }

    @Override
    public Long count(String key) {
        final byte[] rawKey = stringRedisSerializer.serialize(key);
        Long count = jedisCluster.incr(rawKey);
        logger.debug("----------------generateId------{$count} is:{}", count);
        return count;
    }


    @Override
    public boolean isLocked(String key) {
        return false;
    }

    @Override
    public boolean tryLocke(String key) {
        return false;
    }

    @Override
    public boolean tryLocke(String key, long timeout, TimeUnit unit) {
        return false;
    }

    @Override
    public void lock(String key) {

    }

    @Override
    public boolean tryLock(List<String> keyList) {
        return false;
    }

    @Override
    public boolean tryLock(List<String> keyList, long timeout, TimeUnit unit) {
        return false;
    }

    @Override
    public void unLock(String key) {

    }

    @Override
    public void unLock(List<String> keyList) {

    }

    private static long buildId(long second, long microSecond, long shardId,
                                long seq) {
        long miliSecond = (second * 1000 + microSecond / 1000);
        return (miliSecond << (12 + 10)) + (shardId << 10) + seq;
    }
}
