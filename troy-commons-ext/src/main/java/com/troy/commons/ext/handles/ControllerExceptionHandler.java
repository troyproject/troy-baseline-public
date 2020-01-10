package com.troy.commons.ext.handles;

import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.business.BusinessException;
import com.troy.commons.exception.business.CallExchangeRemoteException;
import com.troy.commons.exception.business.TokenException;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.commons.error.I18nController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ydp
 * <p>
 * controller公共处理异常<br/>
 * </p>
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @Value("${spring.profiles.active}")
    String profile;

    @Value("${spring.application.name}")
    String applicationName;

    @Autowired
    private I18nController i18n;

    /**
     * 参数非法异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Res illegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        log.error("IllegalArgumentException ... {}", ex.getMessage(), ex);
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER, i18n.getMessage(request, StateTypeSuper.FAIL_PARAMETER));
    }

    /**
     * @param request
     * @param ex      认证异常处理
     */
    @ExceptionHandler(TokenException.class)
    public Res handleAccessDeniedException(HttpServletRequest request, TokenException ex) {
        log.error("TokenException ... {}", ex.getMessage(), ex);
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER, i18n.getMessage(request, StateTypeSuper.FAIL_AUTH_USER));
    }

    /**
     * 校验异常处理
     *
     * @param ex
     */
    @ExceptionHandler(VerificationException.class)
    public Res handleServiceException(HttpServletRequest request, VerificationException ex) {
        log.error("VerificationException ...", ex.getMessage(), ex);
        StateTypeSuper stateTypeSuper = ex.getState() == null ? StateTypeSuper.FAIL_PARAMETER : ex.getState();
        return ResFactory.getInstance().createRes(stateTypeSuper, i18n.getMessage(request, stateTypeSuper));
    }

    /**
     * 服务异常处理
     *
     * @param request
     * @param ex
     */
    @ExceptionHandler(ServiceException.class)
    public Res handleServiceException(HttpServletRequest request, ServiceException ex) {
        log.error("ServiceException ...", ex.getMessage(), ex);
        StateTypeSuper stateTypeSuper = ex.getStateTypeSuper() == null ? StateTypeSuper.FAIL_UNEXPECTED_RESULTS : ex.getStateTypeSuper();
        return ResFactory.getInstance().createRes(stateTypeSuper, i18n.getMessage(request, stateTypeSuper));
    }

    /**
     * 服务异常处理
     *
     * @param request
     * @param ex
     */
    @ExceptionHandler(BusinessException.class)
    public Res handleBusinessException(HttpServletRequest request, BusinessException ex) {
        log.error("BusinessException ...", ex.getMessage(), ex);
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, ex.getStateDepict());
    }

    /**
     * 远程调用交易所异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(CallExchangeRemoteException.class)
    public Res callExchangeRemoteException(HttpServletRequest request, CallExchangeRemoteException ex) {
        log.error("CallExchangeRemoteException ... {}", ex.getMessage(), ex);
        String stateDepict = ex.getStateDepict();
        StateTypeSuper stateTypeSuper = ex.getState() == null ? StateTypeSuper.FAIL_CALL_REMOTE : ex.getState();
        if(StringUtils.isBlank(stateDepict)){
            stateDepict = i18n.getMessage(request, stateTypeSuper);
        }
        return ResFactory.getInstance().createRes(stateTypeSuper, stateDepict);
    }

    /**
     * 其他异常
     *
     * @param request
     * @param ex      异常处理
     */
    @ExceptionHandler(Exception.class)
    public Res handleUncaughtedException(HttpServletRequest request, Exception ex) {
        log.error("UncaughtedException ...", ex.getMessage(), ex);
        handelException(request, ex);
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, i18n.getMessage(request, StateTypeSuper.FAIL_DEFAULT));
    }

    /**
     * 进行异常记录（异步记录）
     *
     * @param request
     * @param ex
     */
    private void handelException(HttpServletRequest request, Exception ex) {
        //
        // 返回位于主机和端口之后、参数部分之前的那部分内容
//		System.out.println("getRequestURI:" + request.getRequestURI());
        // 返回参数部分，无参数返回null
//		System.out.println("getQueryString:" + request.getQueryString());
        // 调用者IP
//		System.out.println("IP:" + BaseController.getIp(request));

//		System.out.println(request.getAttribute("traceId"));
    }
}