package com.example.sunny.gogleplay.util;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * ============================
 *
 * @Created by sunny
 * @project com.example.sunny.gogleplay.util
 * @file GoglePlay
 * @CreatedTime 2018/6/7 16:25
 * @Desc 浅谈宣泄 深水无波
 */

public class HttpHelper {
    private OkHttpClient okHttpClient = new OkHttpClient();
    public static final String BASEURL = "http://127.0.0.1:8090";
    private String url;

    public HttpHelper(String url) {
        this.url = url;
    }

    public String getSycn(){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();//同步任务
            if (response.isSuccessful()){
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
