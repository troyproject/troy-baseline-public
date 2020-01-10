package com.troy.commons.dto.out;

import com.troy.commons.exception.enums.StateTypeSuper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ResHead
 * @Description 响应头信息（包含响应状态、描述等基本信息）
 * @date 2017年7月7日 上午10:10:43
 * @history 版本 作者 时间 注释
 */
public class ResHead implements Serializable {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = -6133889024650835532L;

    /**
     * 响应状态编码
     */
    private String code;
    /**
     * 响应状态描述
     */
    private String depict;
    /**
     * 详细的描述信息
     */
    private String detailed;
    private long timestamp = System.currentTimeMillis();
    private String logId = UUID.randomUUID().toString();

    public void setState(StateTypeSuper stateTypeSuper) {
        this.code = stateTypeSuper.getCode();
        this.depict = stateTypeSuper.getDepict();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
