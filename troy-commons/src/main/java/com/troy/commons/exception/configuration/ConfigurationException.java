package com.troy.commons.exception.configuration;


import com.troy.commons.exception.enums.StateTypeSuper;

import java.text.MessageFormat;

/**
 * 配置异常
 *
 * @author Han
 */
public class ConfigurationException extends RuntimeException {

    private static final long serialVersionUID = -7457097190745743639L;

    /**
     * 状态
     */
    private StateTypeSuper stateTypeSuper = StateTypeSuper.FAIL_PARAMETER;
    /**
     * 状态描述参数（替换描述字符里的占位符）
     */
    private Object[] stateDepictArguments;

    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    public ConfigurationException(StateTypeSuper stateTypeSuper, Object... stateDepictArguments) {
        this.stateTypeSuper = stateTypeSuper;
        this.stateDepictArguments = stateDepictArguments;
    }

    public ConfigurationException(StateTypeSuper stateTypeSuper, Exception cause, Object... stateDepictArguments) {
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
        return new StateTypeSuperProxy();
    }

    public void setState(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "ConfigurationException [code=" + this.getStateCode() + ", depict="
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
            return ConfigurationException.this.getStateCode();
        }

        @Override
        public String getDepict() {
            return ConfigurationException.this.getStateDepict();
        }
    }
}
