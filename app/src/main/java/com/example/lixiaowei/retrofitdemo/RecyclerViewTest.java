package com.example.lixiaowei.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lixiaowei.retrofitdemo.http.ObjectResult;
import com.example.lixiaowei.retrofitdemo.http.RequestManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class RecyclerViewTest extends AppCompatActivity {

    private RecyclerView rvTest;
    private RvAdapter adapter;
    private int pageNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        rvTest = (RecyclerView) findViewById(R.id.rv_test);
        rvTest.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<QuestionBean> qList = new ArrayList<>();
        adapter = new RvAdapter(R.layout.item_rv, qList);
        rvTest.setAdapter(adapter);
        getQuestion(pageNum, 1);
        final View inflate = View.inflate(this, R.layout.item_rv, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //adapter.addHeaderView(inflate);
                Toast.makeText(RecyclerViewTest.this, "短按", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(RecyclerViewTest.this, "长按。。。。", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                // adapter.addFooterView(inflate);
                Toast.makeText(RecyclerViewTest.this, "短按。内部。。。", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(RecyclerViewTest.this, "长按。内部。。。", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
       //adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                getQuestion(pageNum, 1);
            }
        });
    }

    public void getQuestion(int pageNum, int tag) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("tag", tag);
        Gson gson = new Gson();
        String jsonParams = gson.toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Call<ObjectResult<PageBean>> question = RequestManager.getHttpService().question(pageNum, 10, body);
        RequestManager.enqueue(question, new RequestManager.OnResultListener<ObjectResult<PageBean>>() {
            @Override
            public void onSuccess(Call<ObjectResult<PageBean>> call, Response<ObjectResult<PageBean>> response) throws IOException {
                ObjectResult<PageBean> body1 = response.body();
                PageBean data = body1.getData();
                List<QuestionBean> page = data.getPage();
                adapter.addData(page);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("testkk", t.toString());
            }
        });
    }
}
