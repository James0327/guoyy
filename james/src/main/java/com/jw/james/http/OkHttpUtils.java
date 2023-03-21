package com.jw.james.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * atpco-mng-parent com.ly.flight.intl.atpcomng.integration.util
 *
 * @Description: com.ly.flight.intl.atpcomng.integration.util.OkHttpUtils
 * @Author: guoyiyong/james
 * @Date: 2020-09-29 15:08
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class OkHttpUtils {

    private final OkHttpClient CLIENT;

    public OkHttpUtils() {
        CLIENT = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .build();
    }

    public static void main(String[] args) throws IOException {
        OkHttpUtils okHttpUtils = new OkHttpUtils();

        String url = "http://minprice.t.ie.17usoft.com//atpco-min-price/searchFare/getFareRecord";
        String content = "{\n" +
                "\t\"orig\": \"BJS\",\n" +
                "\t\"dest\": \"LAX\",\n" +
                "\t\"cxr\": \"HU\",\n" +
                "\t\"version\": \"0005000000\",\n" +
                "\t\"a\": \"AAA\"\n" +
                "}";

        String resp = okHttpUtils.post(url, content.getBytes());

        System.out.println("resp: " + resp);
        System.out.println("~~~~~~");

        okHttpUtils.post(url, content.getBytes(), new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response);
                if (HttpStatus.OK.value() == response.code()) {
                    String resp = response.body().string();
                    System.out.println("resp: " + resp);
                }
            }
        });

    }

    public String post(String url, byte[] obj) throws IOException {
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        Request request = new Request.Builder()
                .headers(Headers.of("Content-Type", "application/json;charset=utf-8"))
                .url(url)
                .post(RequestBody.create(obj, mediaType))
                .build();

        Response resp = CLIENT.newCall(request).execute();
        if (resp.code() != HttpStatus.OK.value()) {
            throw new RuntimeException("http error: " + resp.code());
        }

        return resp.body().string();
    }

    public void post(String url, byte[] obj, Callback callback) {
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        Request request = new Request.Builder().url(url)
                .headers(Headers.of("Content-Type", "application/json;charset=utf-8"))
                .post(RequestBody.create(mediaType, obj))
                .build();

        CLIENT.newCall(request).enqueue(callback);
    }

}
