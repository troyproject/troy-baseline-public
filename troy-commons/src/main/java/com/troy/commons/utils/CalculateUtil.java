package com.troy.commons.utils;

import java.math.BigDecimal;

/**
 * 计算工具类
 *
 * @author troy
 */
public class CalculateUtil {

    /**
     * arg1-arg2 结果四舍五入保留两位小数
     *
     * @param arg1
     * @param arg2
     * @return 计算结果
     */
    public static BigDecimal subRoundHalfDown(BigDecimal arg1, BigDecimal arg2) {
        return sub(arg1, arg2).setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * arg1-arg2 结果
     *
     * @param arg1
     * @param arg2
     * @return 计算结果
     */
    public static BigDecimal sub(BigDecimal arg1, BigDecimal arg2) {
        return arg1.subtract(arg2);
    }

    /**
     * arg1-arg2 结果
     *
     * @param arg1
     * @param arg2
     * @return 计算结果
     */
    public static BigDecimal sub(String arg1, String arg2) {
        BigDecimal arg1Big = new BigDecimal(arg1);
        BigDecimal arg2Big = new BigDecimal(arg2);
        return sub(arg1Big, arg2Big);
    }

    /**
     * arg1*arg2 结果
     *
     * @param arg1
     * @param arg2
     * @return 计算结果
     */
    public static BigDecimal multiply(BigDecimal arg1, BigDecimal arg2) {
        return arg1.multiply(arg2);
    }


    /**
     * arg1/arg2 结果四舍五入保留sale位小数
     *
     * @param dividend -- 被除数
     * @param divisor  -- 除数
     * @return 计算结果
     */
    public static BigDecimal divide(String dividend, String divisor, int scale) {
        return divide(new BigDecimal(dividend), new BigDecimal(divisor), scale);
    }

    /**
     * arg1/arg2 结果舍弃保留位后边的数据,原数据不会增加
     *
     * @param dividend -- 被除数
     * @param divisor  -- 除数
     * @return 计算结果
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale) {
        return divide(dividend, divisor, scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * arg1/arg2 在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1),原数据会增加
     *
     * @param dividend -- 被除数
     * @param divisor  -- 除数
     * @return 计算结果
     */
    public static BigDecimal divideRoundUp(BigDecimal dividend, BigDecimal divisor, int scale) {
        return divide(dividend, divisor, scale, BigDecimal.ROUND_UP);
    }

    /**
     * arg1/arg2
     *
     * @param dividend     -- 被除数
     * @param divisor      -- 除数
     * @param scale        -- 精度
     * @param roundingMode -- 舍入模式
     * @return 计算结果
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale, int roundingMode) {
        return dividend.divide(divisor, scale, roundingMode);
    }

    /**
     * 结果舍弃保留位后边的数据,原数据不会增加
     *
     * @param args  -- 需要格式化的数字
     * @param scale -- 保留位
     * @return 计算结果
     */
    public static BigDecimal roundFloor(String args, int scale) {
        return roundFloor(new BigDecimal(args), scale);
    }

    /**
     * 接近零的舍入模式
     * 在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1,即截短),原数据不会增加
     *
     * @param args  -- 需要格式化的数字
     * @param scale -- 保留位
     * @return 计算结果
     */
    public static BigDecimal roundFloor(BigDecimal args, int scale) {
        return args.setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 舍入远离零的舍入模式
     * 舍入-在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1),原数据会增加
     *
     * @param args  -- 需要格式化的数字
     * @param scale -- 保留位
     * @return 计算结果
     */
    public static BigDecimal roundUp(String args, int scale) {
        return roundUp(new BigDecimal(args), scale);
    }

    /**
     * 舍入远离零的舍入模式
     * 舍入-在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1),原数据会增加
     *
     * @param args  -- 需要格式化的数字
     * @param scale -- 保留位
     * @return 计算结果
     */
    public static BigDecimal roundUp(BigDecimal args, int scale) {
        return args.setScale(scale, BigDecimal.ROUND_UP);
    }

    /**
     * 比较两个参数大小
     *
     * @param arg1
     * @param arg2
     * @return 比较结果,-1-arg1小于arg2、0-arg1等于arg2、1-arg1大于arg2
     */
    public static int compareTo(BigDecimal arg1, BigDecimal arg2) {
        return arg1.compareTo(arg2);
    }

    /**
     * （被除数/除数）结果向上取整,除数为0时,返回0
     *
     * @param dividend -- 被除数
     * @param divisor  -- 除数
     * @return
     */
    public static int ceil(int dividend, int divisor) {
        if (divisor == 0) {
            return 0;
        }
        int remainder = dividend % divisor;
        int cycles = dividend / divisor;
        if (remainder > 0) {
            cycles += 1;
        }
        return cycles;
    }

    /**
     * 验证新旧数据是否都为0
     *
     * @param args1
     * @param args2
     * @return
     */
    public static boolean equalToZero(BigDecimal args1, BigDecimal args2) {
        // 比较结果,-1-arg1小于arg2、0-arg1等于arg2、1-arg1大于arg2
        Integer one = args1.compareTo(BigDecimal.ZERO);
        Integer two = args2.compareTo(BigDecimal.ZERO);
        if (one == 0 && two == 0) {
            return true;
        }
        return false;
    }
}
