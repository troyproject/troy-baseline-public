package com.troy.redis.lock;

import java.lang.annotation.*;

/**
 * 分布式锁--简单对象（java基础类型）
 *
 * @author troy
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {

}
