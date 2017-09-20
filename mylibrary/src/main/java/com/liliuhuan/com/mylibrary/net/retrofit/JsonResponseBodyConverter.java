package com.liliuhuan.com.mylibrary.net.retrofit;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/26/16
 * 相应转转器
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        //解密
        //转换
        JSONObject bean= null;
        if(null!=responseBody){
            String response=responseBody.string();
           // Logger.e("response_json==="+response.toString());
            try {
                bean = new JSONObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) bean;
    }
}
