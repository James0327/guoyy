package com.jw.james.http;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * guoyy com.jw.http
 *
 * @Description: Test1
 * @Author: guoyiyong/james
 * @Date: 2019-08-17 22:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test1 {

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://www.cnblogs.com/begin1949/p/4930896.html").build();

        Response resp = client.newCall(request).execute();

        String ret = resp.body().toString();
        String ret2 = JSON.toJSONString(resp.body());
        String ret3 = ToStringBuilder.reflectionToString(resp.body());

        System.out.println("ret: " + ret);
        System.out.println("ret2: " + ret2);
        System.out.println("ret3: " + ret3);

        resp.close();
    }

}
