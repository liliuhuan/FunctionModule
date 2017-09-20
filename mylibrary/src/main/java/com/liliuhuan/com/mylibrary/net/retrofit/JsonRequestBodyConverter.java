package com.liliuhuan.com.mylibrary.net.retrofit;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/26/16
 * 请求转换器
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        //value为请求时body里面的数据
       // Logger.e("request_json==="+value);
        return RequestBody.create(MEDIA_TYPE, value.toString());
    }
}
