package com.troy.commons.ext.support.service;

import java.util.List;

/**
 * base service
 * @param <T>
 */
public interface IBaseService<T> {

	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * @param entity
	 * @return
	 */
	int insert(T entity);

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 * @param entity
	 * @return
	 */
	int delete(T entity);

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * @param key
	 * @return
	 */
	int deleteById(Object key);

	/**
	 * 根据主键更新属性不为null的值
	 * @param entity
	 * @return
	 */
	int update(T entity);

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * @param key
	 * @return
	 */
	T findById(Object key);

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
	 * @param entity
	 * @return
	 */
	T queryForObject(T entity);

	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * @param entity
	 * @return
	 */
	List<T> queryForList(T entity);

	/**
	 * 查询全部结果，select(null)方法能达到同样的效果
	 * @return
	 */
	List<T> queryForList();

	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 * @param entity
	 * @return
	 */
	int getCount(T entity);

	/**
	 * 根据Example条件进行查询
	 * @param example
	 * @return
	 */
	List<T> queryForListExample(Object example);

	/**
	 * 根据Example条件进行查询总数
	 * @param example
	 * @return
	 */
	int getCountByExample(Object example);

	/**
	 * 根据Example条件更新实体entity包含的不是null的属性值
	 * @param entity
	 * @param example
	 * @return
	 */
	int updateByExample(T entity, Object example);

	/**
	 * 根据Example条件删除数据
	 * @param example
	 * @return
	 */
	int deleteByExample(Object example);
}
