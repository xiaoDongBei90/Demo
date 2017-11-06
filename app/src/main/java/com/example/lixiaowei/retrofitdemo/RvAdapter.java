package com.example.lixiaowei.retrofitdemo;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @ author  LiXiaoWei
 * @ date  2017/11/6.
 * desc:
 */

public class RvAdapter extends BaseQuickAdapter<QuestionBean, BaseViewHolder> {

    public RvAdapter(@LayoutRes int layoutResId, @Nullable List<QuestionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionBean item) {
        helper.setText(R.id.tv_test, "测试" + helper.getLayoutPosition());
        helper.addOnClickListener(R.id.btn_test).addOnLongClickListener(R.id.btn_test);
    }
}
