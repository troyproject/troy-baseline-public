package com.troy.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author yudongpo
 * @version 2017-07-21
 */
public class IdGen {

	private static Random random = new Random();

	/** 用于随机选的数字 */
	private static final String BASE_NUMBER = "0123456789";
	/** 用于随机选的字符 */
	private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
	/** 用于随机选的字符和数字 */
	private static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

	/**
	 * 获得指定范围内的随机数
	 * 
	 * @param min
	 *            最小数
	 * @param max
	 *            最大数
	 * @return 随机数
	 */
	public static int randomInt(int min, int max) {
		if (min == max){
			return min;
		}
		return random.nextInt(max - min) + min;
	}

	/**
	 * 获得随机数
	 * 
	 * @return 随机数
	 */
	public static int randomInt() {
		return random.nextInt();
	}

	/**
	 * 获得指定范围内的随机数 [0,limit)
	 * 
	 * @param limit
	 *            限制随机数的范围，不包括这个数
	 * @return 随机数
	 */
	public static int randomInt(int limit) {
		return random.nextInt(limit);
	}

	/**
	 * 随机获得列表中的元素
	 * 
	 * @param <T>
	 *            元素类型
	 * @param list
	 *            列表
	 * @return 随机元素
	 */
	public static <T> T randomEle(List<T> list) {
		return randomEle(list, list.size());
	}

	/**
	 * 随机获得列表中的元素
	 * 
	 * @param <T>
	 *            元素类型
	 * @param list
	 *            列表
	 * @param limit
	 *            限制列表的前N项
	 * @return 随机元素
	 */
	public static <T> T randomEle(List<T> list, int limit) {
		return list.get(randomInt(limit));
	}

	/**
	 * 随机获得列表中的一定量元素
	 * 
	 * @param <T>
	 *            元素类型
	 * @param list
	 *            列表
	 * @param count
	 *            随机取出的个数
	 * @return 随机元素
	 */
	public static <T> List<T> randomEles(List<T> list, int count) {
		final List<T> result = new ArrayList<T>(count);
		int limit = list.size();
		while (--count > 0) {
			result.add(randomEle(list, limit));
		}

		return result;
	}

	/**
	 * 获得一个随机的字符串（只包含数字和字符）
	 * 
	 * @param length
	 *            字符串的长度
	 * @return 随机字符串
	 */
	public static String randomString(int length) {
		return randomString(BASE_CHAR_NUMBER, length);
	}

	/**
	 * 获得一个只包含数字的字符串
	 * 
	 * @param length
	 *            字符串的长度
	 * @return 随机字符串
	 */
	public static String randomNumbers(int length) {
		return randomString(BASE_NUMBER, length);
	}

	/**
	 * 获得一个随机的字符串
	 * 
	 * @param baseString
	 *            随机字符选取的样本
	 * @param length
	 *            字符串的长度
	 * @return 随机字符串
	 */
	public static String randomString(String baseString, int length) {
		StringBuffer sb = new StringBuffer();

		if (length < 1) {
			length = 1;
		}
		int baseLength = baseString.length();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseLength);
			sb.append(baseString.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * @return 随机UUID
	 */
	public static String randomUuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
