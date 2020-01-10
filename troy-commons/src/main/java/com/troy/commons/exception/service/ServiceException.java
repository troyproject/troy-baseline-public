package com.troy.commons.exception.service;

import com.troy.commons.exception.enums.StateTypeSuper;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ServiceException
 * @Description 业务异常
 * @date 2017年7月7日 下午1:20:47
 * @history 版本 作者 时间 注释
 */
public class ServiceException extends RuntimeException {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = -720988274355078390L;

    /**
     * 状态
     */
    private StateTypeSuper stateTypeSuper;
    /**
     * 详细的响应状态描述
     */
    private String depictDetailed;

    public ServiceException(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    public ServiceException(StateTypeSuper stateTypeSuper, String stateDepictDetailed) {
        this(stateTypeSuper);
        this.depictDetailed = stateDepictDetailed;
    }

    public ServiceException(StateTypeSuper stateTypeSuper, Exception cause) {
        super(cause);
        this.stateTypeSuper = stateTypeSuper;
    }

    public ServiceException(StateTypeSuper stateTypeSuper, String stateDepictDetailed, Exception cause) {
        this(stateTypeSuper, cause);
        this.depictDetailed = stateDepictDetailed;
    }

    /**
     * @return stateTypeSuper 错误状态
     * @Title getStateTypeSuper
     * @Description 获取 stateTypeSuper 错误状态
     */
    public StateTypeSuper getStateTypeSuper() {
        return stateTypeSuper;
    }

    /**
     * @param stateTypeSuper 错误状态
     * @Title setStateTypeSuper
     * @Description 设置 stateTypeSuper 错误状态
     */
    public void setStateTypeSuper(StateTypeSuper stateTypeSuper) {
        this.stateTypeSuper = stateTypeSuper;
    }

    /**
     * @return depictDetailed 详细的响应状态描述
     * @Title getDepictDetailed
     * @Description 获取 depictDetailed 详细的响应状态描述
     */
    public String getDepictDetailed() {
        return depictDetailed;
    }

    /**
     * @param depictDetailed 详细的响应状态描述
     * @Title setDepictDetailed
     * @Description 设置 depictDetailed 详细的响应状态描述
     */
    public void setDepictDetailed(String depictDetailed) {
        this.depictDetailed = depictDetailed;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "ServiceException [code=" + stateTypeSuper.getCode() + ", depict="
                + stateTypeSuper.getDepict() + ", detailed=" + depictDetailed + "]";
    }

    @Override
    public String getLocalizedMessage() {
        return this.getMessage();
    }
}
