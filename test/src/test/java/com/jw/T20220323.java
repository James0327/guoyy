package com.jw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: guoyy
 * com.jw.T20220323
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/3/23 11:17
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class T20220323 {

    @Test
    public void test1() throws IOException {
        List<String> lines = FileUtils.readLines(new File(T20220323.class.getClassLoader().getResource("20220323.txt").getFile()), "utf-8");

        Map<String, Node> cache = new ConcurrentHashMap<>();
        String[] arr1 = {";", " "}, arr2 = {"", ""};

        for (String line : lines) {
            line = StringUtils.replaceEach(line, arr1, arr2);
            String[] kv = StringUtils.split(line, "->");
            String k1 = kv[0], k2 = kv[1];

            Node k2Node = cache.computeIfAbsent(k2, Node::new);
            cache.computeIfAbsent(k1, Node::new).children.add(k2Node);
        }

        System.out.println(JSON.toJSONString(cache, SerializerFeature.DisableCircularReferenceDetect));
    }

    private class Node {
        private final String method;
        private final Set<Node> children;

        public Node(String method) {
            this.method = method;
            children = new LinkedHashSet<>();
        }

        public String getMethod() {
            return method;
        }

        public Set<Node> getChildren() {
            return children;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

}
