package com.jw.zk;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * guoyy com.jw.zk
 *
 * @Description: com.jw.zk.ZkClient
 * @Author: guoyiyong/james
 * @Date: 2020-08-04 12:50
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class ZkClient {

    public static void main(String[] args) throws Exception {
        Semaphore lock = new Semaphore(0);

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 6000, (watchedEvent) -> {
            System.out.println("watchedEvent: " + JSON.toJSONString(watchedEvent));
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                lock.release();
            }
        });
        System.out.println("zooKeeper: " + zooKeeper);

        lock.acquire();

        System.out.println("zooKeeper: " + zooKeeper);

        Stat path_zk = zooKeeper.exists("/zk", false);
        System.out.println("path_zk: " + path_zk);
        if (path_zk == null) {
            String r = zooKeeper.create("/zk/node1", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("r: " + r);
        }

        List<String> children = zooKeeper.getChildren("/", false);
        System.out.println("children: " + JSON.toJSONString(children));

        children = zooKeeper.getChildren("/dubbo", false);
        System.out.println("children: " + JSON.toJSONString(children));

        zooKeeper.delete("/zk", -1);
        zooKeeper.delete("/dubbo", -1);

        zooKeeper.close();
    }
}
