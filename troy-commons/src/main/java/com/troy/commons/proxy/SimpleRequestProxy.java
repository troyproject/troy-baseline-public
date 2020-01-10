package com.troy.commons.proxy;

import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.in.ReqHead;


/**
 * description 简单的请求代理（服务方只返回给请求方处理结果）
 *
 * @author Han
 * @date 2018/9/27 15:37
 */
public interface SimpleRequestProxy<RDI extends ReqData> {

    /**
     * description: 请求代理
     *
     * @param reqHead
     * @param reqData
     * @return
     * @throws Exception
     */
    boolean execute(ReqHead reqHead, RDI reqData) throws Exception;
}
