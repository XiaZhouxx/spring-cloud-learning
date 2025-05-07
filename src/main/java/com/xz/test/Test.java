package com.xz.test;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.Map;

/**
 * @author xz
 * @since 2025/2/19 15:44
 */
public class Test {
    public static void main(String[] args) {
        String params = args[0];
        JSONObject obj = new JSONObject(params);
        
        HttpRequest url = HttpUtil.createGet(obj.getStr("url"));
        JSONArray headers = obj.getJSONArray("headers");
        if (!headers.isEmpty()) {
            for (int i = 0; i < headers.size(); i++) {
                JSONObject cur = headers.getJSONObject(i);
                for (Map.Entry<String, Object> stringObjectEntry : cur) {
                    url.header(stringObjectEntry.getKey(), stringObjectEntry.getValue().toString());
                }
            }
        }
//        if (obj.containsKey("proxy")) {
//            JSONObject proxy = obj.getJSONObject("proxy");
//            url.setHttpProxy(proxy.getStr("host"), proxy.getInt("port"));
//        }
        
        
        HttpResponse execute = url.execute();
        System.out.println(execute.body());
    }
}
