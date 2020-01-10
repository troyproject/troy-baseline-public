package com.troy.commons.dto.in;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ReqFactory
 * @Description Req 工厂
 * @date 2017年7月20日 下午5:14:46
 * @history 版本 作者 时间 注释
 */
public class ReqFactory {

    private static final ReqFactory INSTANCE = new ReqFactory();

    private ReqFactory() {

    }

    public static ReqFactory getInstance() {
        return INSTANCE;
    }

    /**
     * @param data
     * @return
     * @Title createReq
     * @Description Create Req
     * @author Han
     * @date 2017年7月20日 下午7:04:02
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ReqData> Req<DT> createReq(DT data) {
        return createReq(null, data);
    }

    /**
     * @param head
     * @param data
     * @return
     * @Title createReqIn
     * @Description Create Req
     * @author Han
     * @date 2017年7月20日 下午7:04:02
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ReqData> Req<DT> createReq(ReqHead head, DT data) {
        Req<DT> reqIn = new Req<DT>();
        reqIn.setHead(head);
        reqIn.setData(data);
        return reqIn;
    }
}
