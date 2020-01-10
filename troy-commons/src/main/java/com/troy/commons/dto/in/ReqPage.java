package com.troy.commons.dto.in;

import com.troy.commons.dto.constants.Constants;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ReqPage
 * @Description TODO（描述类完成的主要功能）
 * @date 2017年9月5日 下午5:17:43
 * @history 版本 作者 时间 注释
 */
public class ReqPage extends ReqData {

    /**
     * @Fields serialVersionUID TODO（描述变量的含义）
     */
    private static final long serialVersionUID = 5316266346456036681L;

    private int pageNum = Constants.DEFAULT_PAGE_NUM;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    /**
     * @return pageNum TODO（描述 pageNum 所表达的含义）
     * @Title getPageNum
     * @Description 获取 pageNum TODO（描述 pageNum 所表达的含义）
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum TODO（描述 pageNum 所表达的含义）
     * @Title setPageNum
     * @Description 设置 pageNum TODO（描述 pageNum 所表达的含义）
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return pageSize TODO（描述 pageSize 所表达的含义）
     * @Title getPageSize
     * @Description 获取 pageSize TODO（描述 pageSize 所表达的含义）
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize TODO（描述 pageSize 所表达的含义）
     * @Title setPageSize
     * @Description 设置 pageSize TODO（描述 pageSize 所表达的含义）
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
