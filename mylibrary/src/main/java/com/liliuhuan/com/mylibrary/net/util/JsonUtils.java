package com.liliuhuan.com.mylibrary.net.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ghh
 * Email:
 * Date: 2016/7/15.
 * 此工具意义不大
 */
//@Deprecated
public class JsonUtils {

    /**
     * 获取本地存储服务方式列表的json数据
     * @return
     */
    public static List<JSONObject> getServerModelList(String json){
        List<JSONObject> list = new ArrayList<>();
        if (!TextUtils.isEmpty(json)){
            try {
                JSONArray array = new JSONArray(json);
                if (array.length()>0){
                    for (int i = 0; i <array.length() ; i++) {
                        list.add(array.optJSONObject(i));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 通过jsonarray 获取 jsonobject的集合
     * @param array
     * @return
     */
    public static List<JSONObject> getListJsonObject(JSONArray array){
        List<JSONObject> list = new ArrayList<>();
        if (array.length()>0){
            for (int i = 0; i <array.length() ; i++) {
                JSONObject jsonObject = array.optJSONObject(i);
                if (jsonObject!=null)
                    list.add(jsonObject);
            }
        }
        return list;
    }

}
