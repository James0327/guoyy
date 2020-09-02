package com.jw.etcd;

import com.alibaba.fastjson.JSON;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdKeysResponse;

import java.net.URI;

/**
 * guoyy com.jw.etcd
 *
 * @Description: com.jw.etcd.T2
 * @Author: guoyiyong/james
 * @Date: 2020-08-10 20:17
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T2 {

    public static void main(String[] args) {
        EtcdClient etcdClient = new EtcdClient(URI.create("http://127.0.0.1:2379"));

        EtcdKeysResponse resp = null;
        try {
            etcdClient.getDir("/jw/jw0").consistent().send().get();
            System.out.println("resp0: " + JSON.toJSONString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp = etcdClient.putDir("/jw").send().get();
            System.out.println("resp1: " + JSON.toJSONString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp = etcdClient.put("/jw/t0", "aaa000").send().get();
            System.out.println("resp2: " + JSON.toJSONString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp = etcdClient.put("t1", "aaa").send().get();
            System.out.println("resp3: " + JSON.toJSONString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp = etcdClient.put("t1/t2", "aaa").send().get();
            System.out.println("resp4: " + JSON.toJSONString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            EtcdResponsePromise<EtcdKeysResponse> promise = etcdClient.getAll().consistent().send();
            EtcdKeysResponse response = promise.get();
            System.out.println("response: " + JSON.toJSONString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            etcdClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
