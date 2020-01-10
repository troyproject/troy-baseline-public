package com.troy.commons.enums;

/**
 *
 */
public enum LangEnum implements BaseEnum<String> {

    EN_US("en-US", "英文"),
    KO_KR("ko_KR", "韩文"),
    ZH_CN("zh-CN", "中文");

    private String code;

    private String desc;

    LangEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
