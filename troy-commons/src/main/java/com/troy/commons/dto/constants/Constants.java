package com.troy.commons.dto.constants;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName Constants
 * @Description 用于定义 service 包下所需常量，如：系统配置参数、默认值
 * @history 版本 作者 时间 注释
 */
public class Constants {

    /**
     * 默认分页查询条件
     */
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 15;

    public static final String IP_ADDRESS_UNKNOWN = "unknown";

    public static final String[] TOSTRINGEXCLUDE = {"apiSecret", "passphrase", "tradePwd", "privateKey"};
}
