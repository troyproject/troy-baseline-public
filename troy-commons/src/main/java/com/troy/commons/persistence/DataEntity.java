package com.troy.commons.persistence;

import java.util.Date;

/**
 * 数据Entity类
 * 
 * @author yudongpo
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建者
	 */
	protected String createBy;

	/**
	 * 创建日期
	 */
	protected Date createTime;

	/**
	 * 更新者
	 */
	protected String updateBy;

	/**
	 * 更新日期
	 */
	protected Date updateTime;

	/**
	 * 最后修改人IP
	 */
	protected String lastUpdateFromIp;

	/**
	 * Construction
	 */
	public DataEntity() {
		super();
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		this.createTime = new Date();
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
		this.updateTime = new Date();
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy
	 *            the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the lastUpdateFromIp
	 */
	public String getLastUpdateFromIp() {
		return lastUpdateFromIp;
	}

	/**
	 * @param lastUpdateFromIp
	 *            the lastUpdateFromIp to set
	 */
	public void setLastUpdateFromIp(String lastUpdateFromIp) {
		this.lastUpdateFromIp = lastUpdateFromIp;
	}
}
