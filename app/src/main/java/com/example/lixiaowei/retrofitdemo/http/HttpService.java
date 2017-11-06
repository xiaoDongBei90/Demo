package com.example.lixiaowei.retrofitdemo.http;

import com.example.lixiaowei.retrofitdemo.CarBean;
import com.example.lixiaowei.retrofitdemo.Country;
import com.example.lixiaowei.retrofitdemo.PageBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/27.
 * desc:接口管理类
 */

public interface HttpService {
    /**
     * 首页- 问答列表
     */
    @Headers("Content-Type: application/json")
    @POST(RequestUrl.QUESTION_LIST)
    Call<ObjectResult<PageBean>> question(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Body RequestBody body);


    @FormUrlEncoded
    @POST(RequestUrl.GET_MYCAR_DATA)
    Call<ListResult<CarBean>> getMyCarData(@Field("tag") String tag);

    @FormUrlEncoded
    @POST
    Call<Country> getCountry(@Field("appKey") String appKey,
                             @Field("ver") String ver,
                             @Field("sessionKey") String sessionKey,
                             @Field("sign") String sign,
                             @Field("format") String format,
                             @Field("method") String method);

}
