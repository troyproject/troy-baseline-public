package com.troy.redis.cluster.redis.support;

import java.lang.annotation.*;

/**
 * Redis key 前缀
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RedisKeyPrefix {
    String value() default "";
}
