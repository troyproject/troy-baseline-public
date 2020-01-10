package com.troy.redis.lock;

import java.lang.annotation.*;

/**
 * 分布式锁--复杂对象
 *
 * @author troy
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {

    /**
     * 含有成员变量的复杂对象中需要加锁的成员变量，如一个数据库中某条记录的ID
     * @return
     */
    String[] field() default {};

    /**
     * 所含的复杂对象是否根据值排序
     * @return
     */
    boolean sort() default false;

}
