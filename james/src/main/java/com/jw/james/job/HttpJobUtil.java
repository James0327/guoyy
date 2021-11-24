package com.jw.james.job;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.Semaphore;

/**
 * basedata-job-web com.ly.ic.basedata.job.web.util.http
 *
 * @Description: com.ly.ic.basedata.job.web.util.http.HttpJobUtil
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 14:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
public class HttpJobUtil {

    public static void main(String[] args) throws Exception {
        HttpJobUtil httpJobUtil = new HttpJobUtil();
        httpJobUtil.test();
    }

    public void test() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory(), trustManager)
                .hostnameVerifier(hostnameVerifier)
                .build();

        Request request = new Request.Builder().url("https://localhost:8090/basedata/job?token=abc")
                .headers(Headers.of("Content-Type", "application/json;charset=utf-8"))
                .get().build();

        Semaphore semaphore = new Semaphore(0);

        Response response = client.newCall(request).execute();
        int code = response.code();
        if (code != 200) {
            System.err.println("code: " + code);
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                semaphore.release();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String resp = response.body().string();
                System.out.println("resp: " + resp);
                semaphore.release();
            }
        });

        semaphore.acquire();
        System.out.println("~~~~~~~~~ " + semaphore.availablePermits());

        // client.cache().close();                             // 清除cache
        client.connectionPool().evictAll();                 // 清除并关闭连接池
        client.dispatcher().executorService().shutdown();   // 清除并关闭线程池
    }

    private SSLSocketFactory createSSLSocketFactory() {
        try {
            // 客户端证书类型
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            // 加载客户端证书 私钥
            InputStream keyStream = this.getClass().getClassLoader().getResourceAsStream("basedata.job.client.p12");
            keyStore.load(keyStream, "basedata".toCharArray());
            // 秘钥管理工厂
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            // 初始化客户端秘钥库
            keyManagerFactory.init(keyStore, "basedata".toCharArray());

            // 建立TLS连接
            SSLContext context = SSLContext.getInstance("TLS");
            // 初始化SSLContext
            context.init(keyManagerFactory.getKeyManagers(), new TrustManager[]{trustManager}, new SecureRandom());
            SSLSocketFactory socketFactory = context.getSocketFactory();

            return socketFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HostnameVerifier hostnameVerifier = (String hostname, SSLSession session) -> {
        if (StringUtils.contains(hostname, "17usoft.com")
                || StringUtils.equals(hostname, "localhost")) {
            return true;
        }
        log.info("HostnameVerifier hostname:{}", hostname);
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
        return verifier.verify(hostname, session);
    };

    private X509TrustManager trustManager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

}
