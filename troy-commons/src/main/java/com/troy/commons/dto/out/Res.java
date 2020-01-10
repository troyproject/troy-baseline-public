package com.troy.commons.dto.out;

import com.troy.commons.dto.constants.Constants;
import com.troy.commons.exception.enums.StateTypeSuper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName Res
 * @Description 响应抽象
 * @date 2017年7月7日 上午10:08:36
 * @history 版本 作者 时间 注释
 */
public class Res<DT extends ResData> implements Serializable {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = -8354736232307541568L;

    /**
     * 响应头信息
     */
    private ResHead head;
    /**
     * 响应主体信息
     */
    private DT data;

    /**
     * @Title Res
     * @Description 只能通过 ResFactory 实例化
     * @author Han
     * @date 2017年7月20日 下午12:03:40
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    Res() {

    }

    /**
     * 判断
     * @return
     */
    public boolean isSuccess(){
        try {
            return StringUtils.equals(StateTypeSuper.SUCCESS_DEFAULT.getCode(), this.getHead().getCode());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return head 响应头信息
     * @Title getHead
     * @Description 获取 head 响应头信息
     */
    public ResHead getHead() {
        return head;
    }

    /**
     * @param head 响应头信息
     * @Title setHead
     * @Description 设置 head 响应头信息
     */
    public void setHead(ResHead head) {
        this.head = head;
    }

    /**
     * @return data 响应主体信息
     * @Title getData
     * @Description 获取 data 响应主体信息
     */
    public DT getData() {
        return data;
    }

    /**
     * @param data 响应主体信息
     * @Title setData
     * @Description 设置 data 响应主体信息
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
