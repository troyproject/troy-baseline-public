package com.troy.commons.ext.support.service;

import com.troy.commons.ext.support.mapper.BaseMapper;
import com.troy.commons.persistence.DataEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseService
 * @author: ydp
 * @since: 2015年10月31日  上午10:06:32
 * @param <D>
 * @param <T>
 */
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public abstract class BaseService<D extends BaseMapper<T>, T extends DataEntity<T>> implements IBaseService<T> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public int insert(T entity) {
		entity.preInsert();
		return dao.insertSelective(entity);
	}

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public int delete(T entity) {
		return dao.delete(entity);
	}

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * @param key
	 * @return
	 */
	@Override
	@Transactional
	public int deleteById(Object key) {
		return dao.deleteByPrimaryKey(key);
	}

	/**
	 * 根据主键更新属性不为null的值
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public int update(T entity) {
		entity.preUpdate();
		return dao.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * @param key
	 * @return
	 */
	@Override
	public T findById(Object key) {
		return dao.selectByPrimaryKey(key);
	}

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
	 * @param entity
	 * @return
	 */
	@Override
	public T queryForObject(T entity) {
		return dao.selectOne(entity);
	}

	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * @param entity
	 * @return
	 */
	@Override
	public List<T> queryForList(T entity) {
		return dao.select(entity);
	}

	/**
	 * 查询全部结果，select(null)方法能达到同样的效果
	 * @return
	 */
	@Override
	public List<T> queryForList() {
		return dao.selectAll();
	}

	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 * @param entity
	 * @return
	 */
	@Override
	public int getCount(T entity) {
		return dao.selectCount(entity);
	}

	/**
	 * 根据Example条件进行查询
	 * @param example
	 * @return
	 */
	@Override
	public List<T> queryForListExample(Object example) {
		return dao.selectByExample(example);
	}

	/**
	 * 根据Example条件进行查询总数
	 * @param example
	 * @return
	 */
	@Override
	public int getCountByExample(Object example) {
		return dao.selectCountByExample(example);
	}

	/**
	 * 根据Example条件更新实体entity包含的不是null的属性值
	 * @param entity
	 * @param example
	 * @return
	 */
	@Override
	@Transactional
	public int updateByExample(@Param("entity") T entity, @Param("example") Object example) {
		return dao.updateByExampleSelective(entity, example);
	}

	/**
	 * 根据Example条件删除数据
	 * @param example
	 * @return
	 */
	@Override
	@Transactional
	public int deleteByExample(Object example) {
		return dao.deleteByExample(example);
	}
}
