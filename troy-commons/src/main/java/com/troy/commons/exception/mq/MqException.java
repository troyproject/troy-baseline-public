package com.troy.commons.exception.mq;


import com.troy.commons.exception.enums.StateTypeSuper;

import java.text.MessageFormat;

/**
 * MQ异常
 */
public class MqException extends RuntimeException {

    private static final long serialVersionUID = -7457097190745743639L;

    /**
     * 状态
     */
    private StateTypeSuper stateTypeSuper = StateTypeSuper.FAIL_PARAMETER;
    /**
     * 状态描述参数（替换描述字符里的占位符）
     */
    private Object[] stateDepictArguments;

    public MqException() {
        super();
    }

    public MqException(String message) {
        super(message);
    }

    public MqException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqException(Throwable cause) {
        super(cause);
    }

    public MqException(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    public MqException(StateTypeSuper stateTypeSuper, Object... stateDepictArguments) {
        this.stateTypeSuper = stateTypeSuper;
        this.stateDepictArguments = stateDepictArguments;
    }

    public MqException(StateTypeSuper stateTypeSuper, Exception cause, Object... stateDepictArguments) {
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

    /**
     * 替换描述里的变量
     */
    private class StateTypeSuperProxy implements StateTypeSuper {

        @Override
        public String getCode() {
            return MqException.this.getStateCode();
        }

        @Override
        public String getDepict() {
            return MqException.this.getStateDepict();
        }
    }
}
