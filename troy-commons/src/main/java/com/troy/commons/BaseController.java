package com.troy.commons;

import com.troy.commons.dto.constants.Constants;
import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.proxy.GreedyRequestProxy;
import com.troy.commons.proxy.SimpleRequestProxy;
import com.troy.commons.utils.ErrorTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base Controller
 *
 * @author troy
 */
public class BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * description:
     * 加工处理请求，作用：
     * 1、日志记录
     * 2、异常处理
     * 3、基本参数校验
     *
     * @param
     * @return
     */
    protected <RDI extends ReqData> Res<ResData> process(Req<RDI> reqIn, SimpleRequestProxy<RDI> requestProxy) {
        if (reqIn == null) {
            throw new ServiceException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (reqIn.getHead() == null) {
            throw new ServiceException(StateTypeSuper.FAIL_PARAMETER);
        }
        try {
            boolean result = requestProxy.execute(reqIn.getHead(), reqIn.getData());
            if (result) {
                return ResFactory.getInstance().createRes();
            }
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
        } catch (Exception e) {
            LOGGER.error("调用服务异常", e);
            return ErrorTranslator.translate(this.currentRequest(), e);
        }
    }

    /**
     * description:
     * 加工处理请求，作用：
     * 1、日志记录
     * 2、异常处理
     * 3、基本参数校验
     *
     * @param
     * @return
     */
    protected <RDI extends ReqData, RDO extends ResData> Res<RDO> process(Req<RDI> reqIn, GreedyRequestProxy<RDI, RDO> requestProxy) {
        if (reqIn == null) {
            throw new ServiceException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (reqIn.getHead() == null) {
            throw new ServiceException(StateTypeSuper.FAIL_PARAMETER);
        }
        try {
            RDO resDataOut = requestProxy.execute(reqIn.getHead(), reqIn.getData());
            return ResFactory.getInstance().createRes(StateTypeSuper.SUCCESS_DEFAULT, resDataOut);
        } catch (Exception e) {
            LOGGER.error("调用服务异常", e);
            return ErrorTranslator.translate(this.currentRequest(), e);
        }
    }

    /**
     * description: 返回当前 HttpServletRequest
     *
     * @param
     * @return
     */
    protected HttpServletRequest currentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * description: 返回当前 HttpServletResponse
     *
     * @param
     * @return
     */
    protected HttpServletResponse currentResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * 成功返回
     *
     * @param data
     * @param <DT>
     * @return
     */
    protected static <DT extends ResData> Res<DT> success(DT data) {
        return ResFactory.getInstance().createRes(StateTypeSuper.SUCCESS_DEFAULT, data);
    }

    /**
     * 失败返回
     *
     * @param stateTypeSuper
     * @param depictDetailed
     * @return
     */
    protected static Res fail(StateTypeSuper stateTypeSuper, String depictDetailed) {
        return ResFactory.getInstance().createRes(stateTypeSuper, depictDetailed);
    }

    /**
     * 获取用户真实ip
     *
     * @return
     */
    protected String getIpAddress() {
        HttpServletRequest request = this.currentRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || Constants.IP_ADDRESS_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || Constants.IP_ADDRESS_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || Constants.IP_ADDRESS_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || Constants.IP_ADDRESS_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || Constants.IP_ADDRESS_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }
}
