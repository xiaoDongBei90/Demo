package com.example.lixiaowei.retrofitdemo.http;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.example.lixiaowei.retrofitdemo.MyApplication;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/27.
 * desc:网络请求管理类
 */

public class RequestManager {
    private static final String CACHE_NAME = "group_cache";
    private static HttpService httpService;
    private static final int TIMEOUT = 15;

    public synchronized static HttpService getHttpService() {
        return httpService == null ? create() : httpService;
    }

    private static HttpService create() {
        //打印请求数据/响应数据
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("cardata", "-------" + message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存存储位置:SDcard/Android/data/包名/dianyin
        String path = MyApplication.getMyApplication().getExternalCacheDir().getPath();
        //设置缓存目录
        File cacheFile = new File(path, CACHE_NAME);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        //生成缓存，50M
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(addCommonHeader())
                .addNetworkInterceptor(addCacheInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(HttpService.class);
    }

    public static <T extends BaseResult> void enqueue(Call<T> call, final OnResultListener<T> resultListener) {
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    int code = response.code();
                    T body = response.body();
                    if (body != null) {
                        int result = body.getResult();
                        try {
                            resultListener.onSuccess(call, response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    BaseResult baseResult = null;
                    try {
                        String errorResult = response.errorBody().string();
                        Gson gson = new Gson();
                        baseResult = gson.fromJson(errorResult, new TypeToken<BaseResult>() {
                        }.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (baseResult != null) {
                        resultListener.onError(new ResultException(baseResult.getResult(), baseResult.getDesc()));
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof HttpException) {
                    Toast.makeText(MyApplication.getMyApplication(), "网络错误!", Toast.LENGTH_SHORT).show();
                } else if (t instanceof JsonParseException || t instanceof JSONException || t instanceof ParseException) {
                    Toast.makeText(MyApplication.getMyApplication(), "解析错误!", Toast.LENGTH_SHORT).show();
                } else if (t instanceof ConnectException) {
                    Toast.makeText(MyApplication.getMyApplication(), "连接失败!", Toast.LENGTH_SHORT).show();
                } else if (t instanceof ConnectTimeoutException || t instanceof java.net.SocketTimeoutException) {
                    Toast.makeText(MyApplication.getMyApplication(), "连接超时!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getMyApplication(), "未知错误!", Toast.LENGTH_SHORT).show();
                    resultListener.onError(t);
                }
            }
        });
    }

    public interface OnResultListener<T> {
        void onSuccess(Call<T> call, Response<T> response) throws IOException;

        void onError(Throwable t);
    }

    /**
     * 添加公共头信息
     */
    private static Interceptor addCommonHeader() {
        Interceptor commonHeaderInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("token", "3b6a72fba990f3a0db1dfcf033498c89a97a2b3b");
                return chain.proceed(builder.build());
            }
        };
        return commonHeaderInterceptor;
    }


    /**
     * 添加缓存拦截
     */
    private static Interceptor addCacheInterceptor() {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //网络不可用
                if (!NetworkUtil.isNetworkAvailable(MyApplication.getMyApplication())) {
                    //在请求头中加入：强制使用缓存，不访问网络
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }


                okhttp3.Response response = chain.proceed(request);
                //网络可用
                if (NetworkUtil.isNetworkAvailable(MyApplication.getMyApplication())) {
                    String cacheControl = request.cacheControl().toString();
                    response.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .build();
                } else {
                    // 无网络时
                    response.newBuilder()
                            .header("Cache-Control", CacheControl.FORCE_CACHE.toString())
                            .build();
                }
                return response;
            }
        };
        return cacheInterceptor;
    }
}


