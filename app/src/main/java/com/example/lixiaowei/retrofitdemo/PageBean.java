package com.example.lixiaowei.retrofitdemo;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/30.
 * desc:
 */

public class PageBean {
    private int totalCount;
    private int pageNum;
    private int pageTotal;
    private List<QuestionBean> page;

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public List<QuestionBean> getPage() {
        return page;
    }
}
