package com.troy.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author troy
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object result = operations.get(key);
        return result;
    }

    /**
     * 读取缓存(String类型)
     *
     * @param key
     * @return
     */
    public String getString(final String key) {
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * lpush 压栈
     *
     * @param key
     * @param value
     * @return
     */
    public Long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * lpushAll 压栈
     *
     * @param key
     * @param collection
     * @return
     */
    public Long lpushAll(String key, Collection<Object> collection) {
        return redisTemplate.opsForList().leftPushAll(key, collection);
    }

    /**
     * lpush 压栈
     *
     * @param key
     * @param vs
     * @return
     */
    public Long lpushAll(String key, Object... vs) {
        return redisTemplate.opsForList().leftPushAll(key, vs);
    }

    /**
     * lpush 压栈
     *
     * @param key
     * @param value
     * @return
     */
    public Long lpush(String key, Object value) {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        return listOperations.leftPush(key, value);
    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<? extends Object> lRangeExt(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 列表修剪
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public void ltrim(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.trim(k, l, l1);
    }

    /**
     * rpush 压栈
     *
     * @param key
     * @param value
     * @return
     */
    public Long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * rpush 压栈
     *
     * @param key
     * @param value
     * @return
     */
    public Long rpush(String key, List value) {
        ListOperations<String, List> opsForList = redisTemplate.opsForList();
        return opsForList.rightPush(key, value);
    }

    /**
     * rpushAll 压栈
     *
     * @param key
     * @param value
     * @return
     */
    public Long rpushAll(String key, List value) {
        ListOperations<String, List> opsForList = redisTemplate.opsForList();
        return opsForList.rightPushAll(key, value);
    }


    /**
     * llen 获取list的长度
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * lpop 出栈
     *
     * @param key
     * @return
     */
    public Object lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * rpop 出栈
     *
     * @param key
     * @return
     */
    public Object rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 缓存中的int值自增1
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Long increment(final String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 缓存中的int值自减1
     * @param key
     * @return
     */
    public Long decrement(final String key) {
        return redisTemplate.opsForValue().increment(key, -1);
    }

    /**
     * 给指定的key设置超时时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(final String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 查询key的过期时间ttl
     *
     * @param key
     * @return
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 根据表达式获取指定的key列表
     *
     * @param key -- 需要查询的key表达式
     * @return
     */
    public Set<String> keys(final String key) {
        return redisTemplate.keys(key);
    }

    /**
     * 保存Map格式的数据
     *
     * @param key      -- 要保存的key
     * @param valueMap -- 要保存的map
     * @return
     */
    public void putAll(final String key, final Map valueMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, valueMap);
    }

    /**
     * 保存Map格式的数据
     *
     * @param key    -- 要保存的key
     * @param mapKey -- 要保存的Map key
     * @param value  -- 要保存的Map value
     * @return
     */
    public void put(final String key, final String mapKey, final Object value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, mapKey, value);
    }

    /**
     * 获取Map的大小
     *
     * @param key -- 要获取长度的map key
     * @return
     */
    public Long hlen(final String key) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.size(key);
    }

    /**
     * 获取Map格式的数据
     *
     * @param key    -- 要查询的key
     * @param mapKey -- 要查询的map中的key
     * @return
     */
    public Object entriesMapKey(final String key, final String mapKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, mapKey);
    }

    /**
     * 获取指定的key Map
     *
     * @param key -- key
     * @return
     */
    public Map entries(final String key) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    /**
     * 删除map中的某个对象
     *
     * @param key   map对应的key
     * @param field map中该对象的key
     */
    public void delMapField(String key, String... field) {
        BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }

    /**
     * 获取指定的key Map
     *
     * @param key -- key
     * @return
     */
    public long ttl(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 添加set
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 删除set集合中的对象
     *
     * @param key
     * @param value
     */
    public void srem(String key, String... value) {
        redisTemplate.boundSetOps(key).remove(value);
    }

    /**
     * set重命名
     *
     * @param oldkey
     * @param newkey
     */
    public void srename(String oldkey, String newkey) {
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    /**
     * 获取set的值
     *
     * @param key
     * @return
     */
    public Set smembers(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    /**
     * 修改redis中map里的值为BigDecimal类型的值，做加/减操作
     *
     * @param key
     * @param mapKey
     * @param changeBig
     * @return
     * @author troy
     */
    public Object updateMapSync(final String key, final String mapKey, final BigDecimal changeBig) {
        if (null == changeBig) {
            return this.entriesMapKey(key, mapKey);
        }

        String incrKey = key + "_" + mapKey + "_incr";
        boolean hacCreateIncr = false;
        try {
            int count = 0;
            while (true) {
                if (count > 0) {
                    Thread.sleep(count * 500L);
                }
                Long incrCount = this.increment(incrKey);
                if (incrCount == 1) {
                    hacCreateIncr = true;
                    this.expire(incrKey, 10, TimeUnit.SECONDS);
                    Object temp = this.entriesMapKey(key, mapKey);
                    if (null != temp) {
                        BigDecimal tempBig = new BigDecimal((String) temp);
                        tempBig = tempBig.add(changeBig);
                        this.put(key, mapKey, tempBig.toString());
                    }
                    break;
                }
                count++;

                if (count > 7) {
                    break;
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            if (hacCreateIncr) {
                this.remove(incrKey);
            }
        }

        return this.entriesMapKey(key, mapKey);
    }

    /**
     * hash操作
     *
     * @param table
     * @param key
     * @param value
     * @return
     */
    public void hset(Object table, Object key, Object value) {
        redisTemplate.opsForHash().put(table, key, value);
    }

    /**
     * hash操作-自增
     *
     * @param table
     * @param key
     * @param value
     * @return
     */
    public Long hsetIncrement(Object table, Object key, long value) {
        return redisTemplate.opsForHash().increment(table, key, value);
    }

    /**
     * @param table
     * @param key
     * @return
     */
    public Object hGet(Object table, Object key) {
        return redisTemplate.opsForHash().get(table, key);
    }

    /**
     * @param table
     * @return
     */
    public Object hGet(Object table) {
        return redisTemplate.opsForHash().values(table);
    }

    /**
     * @param table
     * @return
     */
    public Long hDel(Object table, Object key) {
        return redisTemplate.opsForHash().delete(table, key);
    }

    /**
     * 根据table名称 删除table所有内容
     * @param table
     * @return
     */
    public void hDelAll(Object table) {
        Set keys = redisTemplate.opsForHash().keys(table);
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.opsForHash().delete(table, keys.toArray());
    }

    /**
     * 自增add分数
     *
     * @param key
     * @param value
     * @param add
     */
    public double zincrby(String key, Object value, double add){
        return redisTemplate.opsForZSet().incrementScore(key, value, add);
    }

    /**
     * 返回分数最小的第一个value
     *
     * @param key
     * @return
     */
    public Object rangeFirstSmall(String key) {
        LinkedHashSet<Long> range = rangeAsc(key);
        if(range == null || range.size() == 0){
            return null;
        }
        Object[] objects = range.toArray();
        return objects[0];
    }

    /**
     * 按照分数从小到大 返回排名value
     *
     * @param key
     * @return
     */
    public LinkedHashSet rangeAsc(String key) {
        return (LinkedHashSet) redisTemplate.opsForZSet().range(key, 0, -1);
    }

    /**
     * 根据key value删除zset中的值
     *
     * @param key
     * @param value
     * @return
     */
    public Long removeZSet(String key, Object value){
        return redisTemplate.opsForZSet().remove(key, value);
    }

}
