package com.jw.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.options.DeleteOption;
import io.etcd.jetcd.options.GetOption;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

/**
 * guoyy com.jw.etcd
 * <p>
 * etcd 3.x 版本
 *
 * @Description: com.jw.etcd.T1
 * @Author: guoyiyong/james
 * @Date: 2020-08-07 17:17
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T1 {

    public static void main(String[] args) throws Exception {
        Client client = Client.builder().endpoints("http://127.0.0.1:2379").build();

        KV kvClient = client.getKVClient();

        ByteSequence key1 = ByteSequence.from("/jw1", Charset.defaultCharset());
        ByteSequence val1 = ByteSequence.from("test1", Charset.defaultCharset());

        PutResponse response1 = kvClient.put(key1, val1).get();
        System.out.println("response: " + response1);

        ByteSequence key2 = ByteSequence.from("/jw0/jw2", Charset.defaultCharset());
        ByteSequence val2 = ByteSequence.from("test2", Charset.defaultCharset());

        PutResponse response2 = kvClient.put(key2, val2).get();
        System.out.println("response: " + response2);

        ByteSequence key3 = ByteSequence.from("/jw0/jw1/jw2", Charset.defaultCharset());
        ByteSequence val3 = ByteSequence.from("test12", Charset.defaultCharset());

        PutResponse response3 = kvClient.put(key3, val3).get();
        System.out.println("response: " + response3);

        CompletableFuture<GetResponse> future = kvClient.get(key1);
        GetResponse response = future.get();
        response.getKvs().forEach(obj -> {
            System.out.println(String.format("k:[%s], v:[%s].", obj.getKey().toString(), obj.getValue().toString()));
            System.out.println(String.format("version:[%s], lease:[%s].", obj.getVersion(), obj.getLease()));
        });

        GetOption prefix = GetOption.newBuilder().withPrefix(ByteSequence.from("/jw".getBytes())).build();
        kvClient.get(key2, prefix).get().getKvs().forEach(obj -> {
            System.out.println(String.format("k:[%s], v:[%s].", obj.getKey().toString(), obj.getValue().toString()));
            System.out.println(String.format("version:[%s], lease:[%s].", obj.getVersion(), obj.getLease()));
        });

        kvClient.delete(key3).get();

        // 根据key前缀匹配，批量删除
        DeleteOption delPrefix = DeleteOption.newBuilder().withPrefix(ByteSequence.from("/jw".getBytes())).build();
        kvClient.delete(key2, delPrefix).get();

        client.close();
    }

}
