package com.troy.commons.proxy;

import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.in.ReqHead;
import com.troy.commons.dto.out.ResData;

/**
 * description 贪婪的请求代理（服务方需要返回给请求方除了处理结果以外更多的数据）
 *
 * @author Han
 * @date 2018/9/27 15:36
 */
public interface GreedyRequestProxy<RDI extends ReqData, RDO extends ResData> {

    /**
     * description: 请求代理
     *
     * @param reqHead
     * @param reqData
     * @return
     * @throws Exception
     */
    RDO execute(ReqHead reqHead, RDI reqData) throws Exception;
}
