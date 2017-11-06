package com.example.lixiaowei.retrofitdemo;

import java.util.List;


public class OrderBean  {

    private int errorCount;
    private String exception;
    private int successCount;
    private int totalNum;
    private List<?> errInfoList;
    private List<OrderListBean> orderList;

    public int getErrorCount() {

        return errorCount;
    }

    public String getException() {
        return exception;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public List<?> getErrInfoList() {
        return errInfoList;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }
}
