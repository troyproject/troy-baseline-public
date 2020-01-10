package com.troy.commons.dto.out;


import com.troy.commons.exception.enums.StateTypeSuper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ResFactory
 * @Description Res 工厂
 * @date 2017年7月12日 上午11:46:13
 * @history 版本 作者 时间 注释
 */
public class ResFactory {

    private static final ResFactory INSTANCE = new ResFactory();

    private ResFactory() {

    }

    public static ResFactory getInstance() {
        return INSTANCE;
    }

    /**
     * @return
     * @Title createRes
     * @Description Create Res
     * @author Han
     * @date 2017年7月20日 上午10:10:40
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ResData> Res<DT> createRes() {
        return createRes(StateTypeSuper.SUCCESS_DEFAULT);
    }

    /**
     * 成功的返回
     *
     * @param data
     * @param <DT>
     * @return
     */
    public <DT extends ResData> Res<DT> success(DT data) {
        return createRes(StateTypeSuper.SUCCESS_DEFAULT, data);
    }

    /**
     * 成功的返回（列表）
     *
     * @param data
     * @param <DT>
     * @return
     */
    public <DT extends ResData> Res<ResList<DT>> successList(List<DT> data) {
        return createRes(StateTypeSuper.SUCCESS_DEFAULT, new ResList(data));
    }

    /**
     * 快速返回参数错误
     * @param depictDetailed    自定义返回内容
     * @param <DT>
     * @return
     */
    public  <DT extends ResData> Res<DT> failParameter(String depictDetailed){
        return createRes(StateTypeSuper.FAIL_PARAMETER, depictDetailed);
    }

    /**
     * @param stateTypeSuper
     * @return
     * @Title createRes
     * @Description Create Res
     * @author Han
     * @date 2017年7月20日 上午10:10:40
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ResData> Res<DT> createRes(StateTypeSuper stateTypeSuper) {
        return createRes(stateTypeSuper, "");
    }

    /**
     * @param stateTypeSuper
     * @param data
     * @return
     * @Title createRes
     * @Description Create Res
     * @author Han
     * @date 2017年7月20日 上午10:10:40
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ResData> Res<DT> createRes(StateTypeSuper stateTypeSuper, DT data) {
        return createRes(stateTypeSuper, null, data);
    }

    /**
     * @param stateTypeSuper
     * @param depictDetailed
     * @return
     * @Title createRes
     * @Description Create Res
     * @author Han
     * @date 2017年7月20日 上午10:10:40
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ResData> Res<DT> createRes(StateTypeSuper stateTypeSuper, String depictDetailed) {
        return createRes(stateTypeSuper, depictDetailed, null);
    }

    /**
     * @param stateTypeSuper
     * @param depictDetailed
     * @param data
     * @return
     * @Title createRes
     * @Description Create Res
     * @author Han
     * @date 2017年7月20日 上午10:10:40
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ResData> Res<DT> createRes(StateTypeSuper stateTypeSuper, String depictDetailed, DT data) {
        ResHead head = new ResHead();
        head.setCode(stateTypeSuper.getCode() == null ? null : stateTypeSuper.getCode());
        head.setDepict(StringUtils.isBlank(depictDetailed) ? stateTypeSuper.getDepict() : depictDetailed);
        head.setDetailed(depictDetailed);
        return createRes(head, data);
    }

    /**
     * @param head
     * @param data
     * @return
     * @Description Create Res
     * @author Han
     * @date 2017年7月20日 下午7:02:28
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <DT extends ResData> Res<DT> createRes(ResHead head, DT data) {
        Res<DT> resOut = new Res<>();
        resOut.setHead(head);
        resOut.setData(data);
        return resOut;
    }
}
