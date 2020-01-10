package com.troy.commons.error;

/**
 * 错误码接口
 * @author dp
 */
public interface ErrorCode {

    /**
     * 获取码值
     * @return
     */
    int getCode();

    /**
     * 获取错误描述
     * @return
     */
    String getMessage();
}
