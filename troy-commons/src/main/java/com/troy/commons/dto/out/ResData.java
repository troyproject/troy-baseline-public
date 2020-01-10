package com.troy.commons.dto.out;

import com.troy.commons.dto.constants.Constants;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ResData
 * @Description 响应业务数据抽象
 * @date 2017年7月7日 上午10:52:58
 * @history 版本 作者 时间 注释
 */
public class ResData implements Serializable {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = 1219870442710833570L;

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
