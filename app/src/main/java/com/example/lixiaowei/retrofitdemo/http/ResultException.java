package com.example.lixiaowei.retrofitdemo.http;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/31.
 * desc:
 */

public class ResultException extends RuntimeException {
    private int code;
    private String msg;

    public ResultException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
