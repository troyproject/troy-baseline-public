package com.troy.commons.constraints;

/**
 * description 定义常量（名称）
 *
 * @author Han
 * @date 2018/9/27 10:04
 */
public interface KeyConstants {

    /**
     * request 上下文中的请求状态代码
     */
    String REQUEST_ATTRIBUTES_STATUS_CODE = "javax.servlet.error.status_code";
    /**
     * request 上下文中的媒体类型
     */
    String REQUEST_ATTRIBUTES_MEDIA_TYPES = "org.springframework.web.servlet.HandlerMapping.producibleMediaTypes";
}