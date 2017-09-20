package com.liliuhuan.com.mylibrary.net.util;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Corporation www.ihardstone.com
 * <p/>
 * Created by lc on 15-10-26.
 * <p/>
 * email: rekirt@163.com
 */
public class ListUtil {

    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    public static String mapToString(Map<String, String> pms) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : pms.entrySet()) {
                if(null!=entry.getKey()){
                    encodedParams.append(URLEncoder.encode(entry.getKey(), "utf-8"));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(null==entry.getValue()?"":entry.getValue(), "utf-8"));
                    encodedParams.append('&');
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedParams.toString();
    }
}
