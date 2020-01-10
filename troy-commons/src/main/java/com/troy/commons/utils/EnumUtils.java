package com.troy.commons.utils;

import com.troy.commons.enums.BaseEnum;

/**
 * 枚举工具类
 *
 * @author dp
 */
public class EnumUtils {

    /**
     * 判断枚举值是否存在于指定枚举数组中
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @return
     */
    public static boolean isExist(BaseEnum[] enums, Integer value) {
        if (value == null) {
            return false;
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.code())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据枚举值获取其对应的名字
     *
     * @param enums
     * @param value
     * @return
     */
    public static String getNameByValue(BaseEnum[] enums, Integer value) {
        if (value == null) {
            return "";
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.code())) {
                return e.desc();
            }
        }
        return "";
    }

    /**
     * 通过反射取出Enum所有常量的属性值
     *
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends BaseEnum<B>, B extends Object> T getEnumByCode(B code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            // 利用code进行循环比较，获取对应的枚举
            if (code.equals(each.code())) {
                return each;
            }
        }
        return null;
    }
}
