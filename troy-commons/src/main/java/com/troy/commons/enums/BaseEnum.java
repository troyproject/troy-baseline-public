package com.troy.commons.enums;

/**
 * 枚举值类型
 * @author dp
 */
public interface BaseEnum<K> {

    /**
     * 获取编码
     * @return
     */
    K code();

    /**
     * 获取描述
     * @return
     */
    String desc();

}
