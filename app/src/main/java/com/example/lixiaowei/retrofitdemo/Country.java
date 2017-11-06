package com.example.lixiaowei.retrofitdemo;

import com.example.lixiaowei.retrofitdemo.http.BaseResult;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/1.
 * desc:
 */

public class Country  {
    /**
     * errInfoList : []
     * errorCount : 0
     * exception :
     * result : [{"id":1,"name":"离合器","type":0},{"id":2,"name":"离合器（商用车）","type":0},{"id":3,"name":"百希特润滑油","type":0},{"id":4,"name":"GS润滑油","type":0},{"id":5,"name":"百希特蓄电池","type":0},{"id":6,"name":"百希特刹车片","type":0},{"id":7,"name":"百希特雨刮片","type":0},{"id":8,"name":"PHC水泵","type":0},{"id":9,"name":"百希特滤清器","type":0},{"id":10,"name":"百希特润滑油/滤清器组合","type":0}]
     * successCount : 1
     * totalNum : 0
     */

    private int errorCount;
    private String exception;
    private int successCount;
    private int totalNum;
    private List<?> errInfoList;
    private List<ResultBean> result;

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<?> getErrInfoList() {
        return errInfoList;
    }

    public void setErrInfoList(List<?> errInfoList) {
        this.errInfoList = errInfoList;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * name : 离合器
         * type : 0
         */

        private int id;
        private String name;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
