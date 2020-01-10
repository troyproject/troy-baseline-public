package com.troy.commons.ext.support.mapper;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * DAO支持类实现
 * @author ydp
 */
public interface BaseMapper<T> extends Mapper<T> {

	/**
	 * 调用数据库函数 返回指定 对象
	 * @return
	 * @throws Exception
	 */
	T queryForObjectByByFunction() throws Exception;

	/**
	 * 调用数据库函数 查询结果是 List<Entity>
	 * @return
	 * @throws Exception
	 */
	List<T> queryForListByFunction() throws Exception;

	/**
	 * 调用数据库存储过程 返回指定 对象
	 * @return
	 * @throws Exception
	 */
	T queryForObjectByProc() throws Exception;

	/**
	 * 调用数据库存储过程 返回指定 对象
	 * @return
	 * @throws Exception
	 */
	List<T> queryForListByProc() throws Exception;

	/**
	 * 执行 call 操作,执行存储过程,和数据库函数x
	 * @return
	 * @throws Exception
	 */
	Object executeCallBack() throws Exception;
}
