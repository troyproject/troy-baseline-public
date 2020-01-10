package com.troy.commons.dto.in;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ReqHead
 * @Description 请求头信息（客户端标识等基本信息）
 * @date 2017年7月7日 上午9:52:35
 * @history 版本 作者 时间 注释
 */
public class ReqHead implements Serializable {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = 5374690868984645015L;

    /**
     * 平台编号
     */
    private String clientId;
    private long timestamp = System.currentTimeMillis();
    private String logId = UUID.randomUUID().toString();


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
