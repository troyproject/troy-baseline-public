package com.troy.commons.enums;

/**
 * 状态开关枚举
 *
 * @author dp
 */
public enum StatusEnum implements BaseEnum<Integer> {
    ON(1, "启用"),
    OFF(0, "禁用");

    private Integer code;

    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    /**
     * 校验状态是否为开启
     *
     * @param status
     * @return
     */
    public static boolean isOn(Integer status) {
        return status.equals(StatusEnum.ON.code());
    }
}
