package com.troy.commons.utils;

import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.error.I18nController;
import com.troy.commons.exception.business.BusinessException;
import com.troy.commons.exception.configuration.ConfigurationException;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.http.HttpServletRequest;

/**
 * description 错误翻译器
 *
 * @author Han
 * @date 2018-10-12 17:10
 */
public class ErrorTranslator {

    private static I18nController i18n = ApplicationContextUtil.getBean(I18nController.class);

    /**
     * description 解析异常
     *
     * @author Han
     * @date 2018/10/10 18:45
     */
    public static <RDO extends ResData> Res<RDO> translate(HttpServletRequest request, Throwable throwable) {
        if (throwable == null) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, i18n.getMessage(request, StateTypeSuper.FAIL_DEFAULT));
        }
        if (throwable.getCause() != null) {
            return translate(request, throwable.getCause());
        }
        if (VerificationException.class.isInstance(throwable)) {
            VerificationException exception = (VerificationException) throwable;
            StateTypeSuper stateTypeSuper = exception.getState() == null ? StateTypeSuper.FAIL_PARAMETER : exception.getState();
            return ResFactory.getInstance().createRes(stateTypeSuper, i18n.getMessage(request, stateTypeSuper));
        } else if (ConfigurationException.class.isInstance(throwable)) {
            ConfigurationException exception = (ConfigurationException) throwable;
            StateTypeSuper stateTypeSuper = exception.getState() == null ? StateTypeSuper.FAIL_PARAMETER : exception.getState();
            return ResFactory.getInstance().createRes(stateTypeSuper, i18n.getMessage(request, stateTypeSuper));
        } else if (ServiceException.class.isInstance(throwable)) {
            ServiceException exception = (ServiceException) throwable;
            StateTypeSuper stateTypeSuper = exception.getStateTypeSuper() == null ? StateTypeSuper.FAIL_UNEXPECTED_RESULTS : exception.getStateTypeSuper();
            return ResFactory.getInstance().createRes(stateTypeSuper, i18n.getMessage(request, stateTypeSuper));
        } else if (HttpRequestMethodNotSupportedException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_REQUEST, i18n.getMessage(request, StateTypeSuper.FAIL_REQUEST));
        }else if(BusinessException.class.isInstance(throwable)){
            BusinessException exception = (BusinessException) throwable;
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, exception.getStateDepict());
        }
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, i18n.getMessage(request, StateTypeSuper.FAIL_DEFAULT));
    }
}
