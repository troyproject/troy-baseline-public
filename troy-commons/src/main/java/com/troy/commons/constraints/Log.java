package com.troy.commons.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * controller统一日志
 *
 * @author ydp
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Log {

    /**
     * 用于输出日志前缀
     * @return
     */
    String value();

    /**
     * 是否打印入参，默认：打印
     * @return
     */
    boolean inputPrint() default true;

    /**
     * 是否打印出参，默认：打印
     * @return
     */
    boolean outputPrint() default true;
}