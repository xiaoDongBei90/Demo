package com.example.lixiaowei.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lixiaowei.retrofitdemo.http.ListResult;
import com.example.lixiaowei.retrofitdemo.http.ObjectResult;
import com.example.lixiaowei.retrofitdemo.http.RequestManager;
import com.example.lixiaowei.retrofitdemo.http.ResultException;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        findViewById(R.id.btn_request_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewTest.class));
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
                String title = data.getPage().get(0).getTitle();
            }

            @Override
            public void onError(Throwable t) {
                Log.d("testkk", t.toString());
            }
        });
    }

    public void getMyCar() {
        Call<ListResult<CarBean>> myCarData = RequestManager.getHttpService().getMyCarData("");

        RequestManager.enqueue(myCarData, new RequestManager.OnResultListener<ListResult<CarBean>>() {
            @Override
            public void onSuccess(Call<ListResult<CarBean>> call, Response<ListResult<CarBean>> response) throws IOException {
                ListResult<CarBean> body = response.body();
                String brandname = body.getData().get(0).getBrandname();
            }

            @Override
            public void onError(Throwable t) {
                if (t instanceof ResultException) {
                    ResultException resultException = (ResultException) t;
                    String msg = resultException.getMsg();
                    int code = resultException.getCode();
                } else {
                    Log.d("testkk", t.toString());
                }
            }
        });
    }
    /*public void getCountry(){
        Call<Country> country = RequestManager.getHttpService().getCountry("DOW_6f5292d92dsfksdjfk3u42378978", "1.0", "a83f462636c946549c6055067e5c6ef8",
                "65b7f8d4bf61d046f1562c56c8f71d14", "json", "bakheet.api.queryPartGroup");
        country.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                Country body = response.body();
                List<Country.ResultBean> result = body.getResult();

            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.d("testkk", t.toString());
            }
        });
    }*/
}
