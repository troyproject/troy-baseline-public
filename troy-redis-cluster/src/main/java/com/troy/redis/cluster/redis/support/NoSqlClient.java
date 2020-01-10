package com.troy.redis.cluster.redis.support;

import com.troy.redis.cluster.redis.support.enums.KeyExpireStrategy;
import com.troy.redis.cluster.redis.support.enums.KeyExpireTimeUnit;

/**
 * 通用NoSqlClient，供不同业务端实现
 * @author
 */
public interface NoSqlClient<T> {
    void set(final String key, final T value);

    void set(String key, T value, Long expiry);

    void set(String key, T value, KeyExpireStrategy keyExpireStrategy, KeyExpireTimeUnit keyExpireTimeUnit, Long expiry);

    <T> boolean exist(Class<T> clazz, String key);

    T get(final String key, Class<T> clazz);

    <T> void delete(String key);

    <T> void hashSet(String key, String hashKey, T hashValue);

    Object hashGet(String key, String hashKey, Class<?> hashValueClazz);

}
