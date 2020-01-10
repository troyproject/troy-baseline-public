package com.troy.commons.dto.out;

import java.util.List;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ResPage
 * @Description TODO（描述类完成的主要功能）
 * @date 2017年9月5日 下午5:23:06
 * @history 版本 作者 时间 注释
 */
public class ResPage<T> extends ResData {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = -4269392993641922348L;
    // 总记录数
    private long total;
    // 结果集
    private List<T> list;

    public ResPage() {

    }

    public ResPage(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    /**
     * @return total TODO（描述 total 所表达的含义）
     * @Title getTotal
     * @Description 获取 total TODO（描述 total 所表达的含义）
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total TODO（描述 total 所表达的含义）
     * @Title setTotal
     * @Description 设置 total TODO（描述 total 所表达的含义）
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return list TODO（描述 list 所表达的含义）
     * @Title getList
     * @Description 获取 list TODO（描述 list 所表达的含义）
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list TODO（描述 list 所表达的含义）
     * @Title setList
     * @Description 设置 list TODO（描述 list 所表达的含义）
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}
