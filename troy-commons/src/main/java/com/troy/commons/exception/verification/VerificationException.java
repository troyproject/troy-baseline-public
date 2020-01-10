package com.troy.commons.exception.verification;

import com.troy.commons.exception.enums.StateTypeSuper;

import java.text.MessageFormat;

/**
 * 校验未通过异常
 *
 * @author Han
 */
public class VerificationException extends RuntimeException {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = -720988274355435295L;

    /**
     * 状态
     */
    private StateTypeSuper stateTypeSuper;
    /**
     * 状态描述参数（替换描述字符里的占位符）
     */
    private Object[] stateDepictArguments = {};

    public VerificationException(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    public VerificationException(StateTypeSuper stateTypeSuper, Object... stateDepictArguments) {
        this.stateTypeSuper = stateTypeSuper;
        this.stateDepictArguments = stateDepictArguments;
    }

    public VerificationException(StateTypeSuper stateTypeSuper, Exception cause, Object... stateDepictArguments) {
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
        return this.stateTypeSuper.getCode();
    }

    /**
     * 状态描述
     *
     * @return
     */
    public String getStateDepict() {
        return MessageFormat.format(this.stateTypeSuper.getDepict(), this.stateDepictArguments);
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
        return "VerificationException [code=" + this.getStateCode() + ", depict="
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
            return VerificationException.this.getStateCode();
        }

        @Override
        public String getDepict() {
            return VerificationException.this.getStateDepict();
        }
    }
}
