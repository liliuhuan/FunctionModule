package com.liliuhuan.com.mylibrary.net.retrofit;

import com.liliuhuan.com.mylibrary.LibraryApp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器（配置统一请求参数）
 * nanfeifei
 * 2017/2/22 13:56
 *
 * @version 3.7.0
 */
public class CommonInterceptor implements Interceptor {

  public CommonInterceptor() {
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request oldRequest = chain.request();

    // 添加新的参数
    HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
        .newBuilder()
        .scheme(oldRequest.url().scheme())
        .host(oldRequest.url().host())
        .addQueryParameter("token", LibraryApp.getToken());

    // 新的请求
    Request newRequest = oldRequest.newBuilder()
        .method(oldRequest.method(), oldRequest.body())
        .url(authorizedUrlBuilder.build())
        .build();

    return chain.proceed(newRequest);
  }
}
