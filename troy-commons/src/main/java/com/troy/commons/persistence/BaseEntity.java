package com.troy.commons.persistence;

import com.troy.commons.dto.constants.Constants;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Entity支持类
 * 
 * @author ydp
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();

	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, Constants.TOSTRINGEXCLUDE);
	}
}
