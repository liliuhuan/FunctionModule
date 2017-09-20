package com.liliuhuan.com.mylibrary.net.retrofit;

import com.liliuhuan.com.mylibrary.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**

 * 网络请求的工具类
 */
public class RetrofitUtil {
    private static RetrofitUtil instance;
    private Retrofit helper;

    private RetrofitUtil(){
        CommonInterceptor commonInterceptor = new CommonInterceptor();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(commonInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

            helper = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(JsonConverterFactory.create())
                    .baseUrl(Config.url)
                    .build();
    }

    public static RetrofitUtil getInstance(){
        if(null==instance){
         synchronized (RetrofitUtil.class){
             if(null==instance)instance = new RetrofitUtil();
         }
        }
        return instance;
    }

    /**
     * 生成服务
     * @param clz
     * @return
     */
    public <T> T createService(Class<T> clz){
        T service = helper.create(clz);

        return service;
    }


}
