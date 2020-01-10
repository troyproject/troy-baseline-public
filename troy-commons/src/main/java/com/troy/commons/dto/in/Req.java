package com.troy.commons.dto.in;

import com.troy.commons.dto.constants.Constants;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName Req
 * @Description 请求抽象
 * @date 2017年7月6日 下午8:12:59
 * @history 版本 作者 时间 注释
 */
public class Req<DT extends ReqData> implements Serializable {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = 8876509725575825107L;

    /**
     * 请求头信息
     */
    private ReqHead head;

    /**
     * 请求主体信息
     */
    private DT data;

    /**
     * @Title Req
     * @Description 只能通过 ReqFactory 实例化
     * @author Han
     * @date 2017年7月20日 下午5:14:24
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    protected Req() {

    }

    /**
     * @return head 请求头信息
     * @Title getHead
     * @Description 获取 head 请求头信息
     */
    public ReqHead getHead() {
        return head;
    }

    /**
     * @param head 请求头信息
     * @Title setHead
     * @Description 设置 head 请求头信息
     */
    public void setHead(ReqHead head) {
        this.head = head;
    }

    /**
     * @return data 请求主体信息
     * @Title getData
     * @Description 获取 data 请求主体信息
     */
    public DT getData() {
        return data;
    }

    /**
     * @param data 请求主体信息
     * @Title setData
     * @Description 设置 data 请求主体信息
     */
    public void setData(DT data) {
        this.data = data;
    }

    /**
     * @return
     * @Title toString
     * @Description TODO（描述方法完成的主要功能）
     * @author Han
     * @date 2017年5月6日 下午3:19:52
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, Constants.TOSTRINGEXCLUDE);
    }
}
