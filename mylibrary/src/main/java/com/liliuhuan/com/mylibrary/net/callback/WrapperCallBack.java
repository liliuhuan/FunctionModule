package com.liliuhuan.com.mylibrary.net.callback;


import android.text.TextUtils;

import com.liliuhuan.com.mylibrary.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 7/27/16
 * 在retrofit请求的封装中封装一层
 */
public class WrapperCallBack<T> implements Callback<T> {

    private IRequestCallBack callback;

    public WrapperCallBack(IRequestCallBack callback){
        this.callback = callback;
        if(null!=this.callback)callback.onStartLoading();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            LogUtils.d(call.request().url().toString());
            if(response.isSuccessful() && response.code()==200){
                try {
                    Object obj = response.body();
                    if(null!=obj){
                        JSONObject job = new JSONObject(obj.toString());
                        if(job.optBoolean("IsSuccess")){
                            String result = job.optString("Data");
                            if(!TextUtils.isEmpty(result)) {
                                if (null != callback) callback.onSuccess(job.optString("Data"));
                            }else  if (null != callback) callback.onSuccess("请求成功！");
                        }else{
                            if(null!=callback)
                                callback.onError(job.optString("Message"));
                        }
                    }else{
                        if(null!=callback)callback.onError("接口404");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(null!=callback)callback.onError(e.getMessage());
                }
            }else{
                if(null!=callback)
                    callback.onError(response.message());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        try {
            if(null!=callback)
                callback.onError(t.getMessage());
                //callback.onError("请检查网络");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
