/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：BusinessException.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */
package com.troy.commons.exception.business;


import com.troy.commons.exception.enums.StateTypeSuper;

import java.text.MessageFormat;

/**
 * TokenException
 *
 * @author dp
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = -7457097190745743639L;

    /**
     * 状态
     */
    private StateTypeSuper stateTypeSuper = StateTypeSuper.FAIL_AUTH_USER;
    /**
     * 状态描述参数（替换描述字符里的占位符）
     */
    private Object[] stateDepictArguments;

    public TokenException() {
        super();
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

    public TokenException(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    public TokenException(StateTypeSuper stateTypeSuper, Object... stateDepictArguments) {
        this.stateTypeSuper = stateTypeSuper;
        this.stateDepictArguments = stateDepictArguments;
    }

    public TokenException(Exception cause, Object... stateDepictArguments) {
        super(cause);
        this.stateDepictArguments = stateDepictArguments;
    }

    public TokenException(StateTypeSuper stateTypeSuper, Exception cause, Object... stateDepictArguments) {
        super(cause);
        this.stateTypeSuper = stateTypeSuper;
        this.stateDepictArguments = stateDepictArguments;
    }

    /**
     * 状态代码
     *
     * @return
     */
    public String getStateCode() {
        return stateTypeSuper.getCode();
    }

    /**
     * 状态描述
     *
     * @return
     */
    public String getStateDepict() {
        return MessageFormat.format(stateTypeSuper.getDepict(), stateDepictArguments);
    }

    public StateTypeSuper getState() {
        return stateTypeSuper;
    }

    public void setState(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    @Override
    public String getMessage() {
        return "TokenException [code=" + this.getStateCode() + ", depict="
                + this.getStateDepict() + "]";
    }

    @Override
    public String getLocalizedMessage() {
        return this.getMessage();
    }

    /**
     * 替换描述里的变量
     */
    private class StateTypeSuperProxy implements StateTypeSuper {

        @Override
        public String getCode() {
            return TokenException.this.getStateCode();
        }

        @Override
        public String getDepict() {
            return TokenException.this.getStateDepict();
        }
    }
}
