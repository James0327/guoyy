package com.jw.james.etcd;

import com.alibaba.fastjson.JSON;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Lease;
import io.etcd.jetcd.Maintenance;
import io.etcd.jetcd.Response;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.kv.DeleteResponse;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.lease.LeaseGrantResponse;
import io.etcd.jetcd.lease.LeaseKeepAliveResponse;
import io.etcd.jetcd.lease.LeaseTimeToLiveResponse;
import io.etcd.jetcd.maintenance.AlarmResponse;
import io.etcd.jetcd.options.DeleteOption;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.LeaseOption;
import io.etcd.jetcd.options.PutOption;
import io.etcd.jetcd.support.CloseableClient;
import io.etcd.jetcd.watch.WatchEvent;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
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
@Slf4j
public class T_v3 {

    public static void main(String[] args) throws Exception {
        Client client = Client.builder().endpoints("http://127.0.0.1:2379").build();

        Maintenance maintenanceClient = client.getMaintenanceClient();
        AlarmResponse alarmResponse = maintenanceClient.listAlarms().get();
        System.out.println(alarmResponse.toString());
        System.out.println(alarmResponse.getAlarms());

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

        System.out.println("精确匹配 key1 ... ...");
        CompletableFuture<GetResponse> future = kvClient.get(key1);
        GetResponse response = future.get();
        response.getKvs().forEach(obj -> {
            System.out.println(String.format("k:[%s], v:[%s].", new String(obj.getKey().getBytes()), new String(obj.getValue().getBytes())));
            System.out.println(String.format("version:[%s], lease:[%s].", obj.getVersion(), obj.getLease()));
        });

        System.out.println("前缀匹配 jw ... ...");
        GetOption prefix = GetOption.newBuilder().withPrefix(ByteSequence.from("/jw".getBytes())).build();
        kvClient.get(key2, prefix).get().getKvs().forEach(obj -> {
            System.out.println(String.format("k:[%s], v:[%s].", obj.getKey().toString(Charset.defaultCharset()), obj.getValue().toString(Charset.defaultCharset())));
            System.out.println(String.format("version:[%s], lease:[%s].", obj.getVersion(), obj.getLease()));
        });

        DeleteResponse deleteResponse = kvClient.delete(key3).get();
        System.out.println(deleteResponse);

        // 根据key前缀匹配，批量删除
        DeleteOption delPrefix = DeleteOption.newBuilder().withPrefix(ByteSequence.from("/jw".getBytes())).build();
        DeleteResponse deleteResponse1 = kvClient.delete(key2, delPrefix).get();
        System.out.println(deleteResponse1);

        DeleteResponse deleteResponse2 = kvClient.delete(ByteSequence.from("/xx", Charset.defaultCharset())).get();
        System.out.println(deleteResponse2);

        Lease leaseClient = client.getLeaseClient();
        LeaseTimeToLiveResponse leaseTimeToLiveResponse1 = leaseClient.timeToLive(0, LeaseOption.newBuilder().build()).get();
        System.out.println("leaseTimeToLiveResponse1: " + leaseTimeToLiveResponse1 + "][" + leaseTimeToLiveResponse1.getTTl());
        LeaseKeepAliveResponse leaseKeepAliveResponse = leaseClient.keepAliveOnce(0).get();
        System.out.println("leaseKeepAliveResponse: " + leaseKeepAliveResponse + "][" + leaseKeepAliveResponse.getTTL());

        LeaseGrantResponse leaseGrantResponse = leaseClient.grant(15).get();
        long id = leaseGrantResponse.getID();
        System.out.println("leaseGrantResponse: " + leaseGrantResponse);

        LeaseTimeToLiveResponse leaseTimeToLiveResponse2 = leaseClient.timeToLive(id, LeaseOption.newBuilder().build()).get();
        System.out.println("leaseTimeToLiveResponse2: " + leaseTimeToLiveResponse2 + "][" + leaseTimeToLiveResponse2.getTTl());

/*
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        executor.scheduleAtFixedRate(() -> {
            try {
                int i = ThreadLocalRandom.current().nextInt(10, 20);
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(i));
                LeaseKeepAliveResponse aliveResponse = leaseClient.keepAliveOnce(id).get();
                System.out.println(LocalDateTime.now() + " aliveResponse: " + i + "][" + aliveResponse.getID() + "][" + aliveResponse.getTTL());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS);
*/

        CloseableClient client1 = leaseClient.keepAlive(id, new StreamObserver<LeaseKeepAliveResponse>() {
            @Override
            public void onNext(LeaseKeepAliveResponse resp) {
                System.out.println("onNext: " + resp.getID() + "][" + resp.getTTL());
            }

            @Override
            public void onError(Throwable t) {
                log.error("onError: ", t);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });

        kvClient.put(ByteSequence.from("/A", Charset.defaultCharset()),
                ByteSequence.from("AAAAA", Charset.defaultCharset()),
                PutOption.newBuilder().withLeaseId(leaseGrantResponse.getID()).build())
                .thenAccept((putResponse) -> {
                    System.out.println(putResponse);
                    Response.Header header = putResponse.getHeader();
                    System.out.println("header: " + JSON.toJSONString(header));
                }).get();

        GetResponse getResponse = kvClient.get(ByteSequence.from("/A", Charset.defaultCharset())).get();
        Response.Header header = getResponse.getHeader();
        System.out.println(header);
        List<KeyValue> kvs = getResponse.getKvs();
        System.out.println(kvs.get(0).getKey().toString(Charset.defaultCharset()));

        Watch watchClient = client.getWatchClient();
        watchClient.watch(ByteSequence.from("/A", Charset.defaultCharset()), watchResponse -> {
            List<WatchEvent> events = watchResponse.getEvents();
            for (int i = 0; i < events.size(); i++) {
                WatchEvent watchEvent = events.get(i);
                System.out.println(LocalDateTime.now() + "][" + watchEvent.getKeyValue() + "][" + watchEvent.getEventType());
            }
        });
/*
        watchClient.watch(ByteSequence.from("/A", Charset.defaultCharset()), new Watch.Listener() {
            @Override
            public void onNext(WatchResponse response) {
                List<WatchEvent> events = response.getEvents();
                System.out.println(events);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("onError", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }
        });
*/

        client1.close();
        watchClient.close();
        leaseClient.close();
        client.close();
    }

}
