package com.example.lixiaowei.retrofitdemo.http;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/27.
 * desc:
 */

public class ListResult<D> extends BaseResult {
    private List<D> data;

    public List<D> getData() {
        return data;
    }
}
