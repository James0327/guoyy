package com.jw.james.tcly.envcopy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: guoyy
 * com.jw.tcly.envcopy.MainProcesss
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/20 23:25
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MainProcesss {

    @Test
    public void test() {
        JSONObject jObj = JSON.parseObject(getJson());
        JSONArray jObjArr = jObj.getJSONArray("result");
        Iterator<Object> iterator = jObjArr.iterator();

        Map<String, Node> map = Maps.newConcurrentMap();
        while (iterator.hasNext()) {
            JSONObject obj = (JSONObject)iterator.next();
            String from = obj.getString("from");
            String to = obj.getString("to");
            Node fromNode = map.computeIfAbsent(from, k -> new Node(k));
            Node toNode = map.computeIfAbsent(to, k -> new Node(k));
            fromNode.getChriden().add(to);
            toNode.getParent().add(from);
        }

        // 过滤
        filter(map);

        List<Set<String>> retList = Lists.newArrayList();
        while (MapUtils.isNotEmpty(map)) {
            System.out.println("before: " + map);
            Set<String> ret = calLevel(map);
            retList.add(ret);
            System.out.println("after: " + map);
            System.out.println("ret: " + ret);
        }

        System.out.println(retList);
    }

    private void filter(Map<String, Node> map) {
        String apps = "iflight.java.dsf.inter.isav,iflight.java.dsf.inter.isavdownloader,iflight.java.web.inter.isavmanager";
        String[] appArr = StringUtils.split(apps, ",");

        Iterator<Map.Entry<String, Node>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Node> entry = iterator.next();
            String id = entry.getKey();
            if (!ArrayUtils.contains(appArr, id)) {
                iterator.remove();
                Node node = entry.getValue();
                Set<String> parent = node.getParent();
                for (String key : parent) {
                    map.get(key).getChriden().remove(id);
                }
                Set<String> chriden = node.getChriden();
                for (String key : chriden) {
                    map.get(key).getParent().remove(id);
                }
            }
        }
    }

    private Set<String> calLevel(Map<String, Node> map) {
        Set<String> ret = Sets.newHashSet();

        boolean hasLeaf = false;

        Iterator<Map.Entry<String, Node>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Node> entry = iterator.next();
            Node node = entry.getValue();
            if (CollectionUtils.isEmpty(node.getChriden())) {
                iterator.remove();
                if (!hasLeaf) {
                    hasLeaf = true;
                }
                String id = entry.getKey();
                ret.add(id);
                Set<String> parent = node.getParent();
                for (String key : parent) {
                    map.get(key).getChriden().remove(id);
                }
            }
        }

        if (hasLeaf) {
            return ret;
        }
        ret = map.values().stream().map(Node::getId).collect(Collectors.toSet());
        map.clear();
        return ret;
    }

    private String getJson() {
        return "{\n" +
                "    \"code\": 200,\n" +
                "    \"message\": null,\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isav\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isavdownloader\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isav\",\n" +
                "            \"to\": \"tcbase.java.dss.hbase.iflight.search\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isav\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isavtask\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isav\",\n" +
                "            \"to\": \"dsf.iflight.shopping.irouting\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isav\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isavconfig\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"flight.java.dsf.gwsale.core.intl.b2b\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"dsf.flight.gwsale.core.intl\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"dsf.iflight.shopping.irouting\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.grab.ticket\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isfc\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"dsf.flight.inter.icgalileo\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isavdownloader\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.irate.center\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isav\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isavdownloader\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isavconfig\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isavdownloader\",\n" +
                "            \"to\": \"iflight.java.dsf.isgather\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isavdownloader\",\n" +
                "            \"to\": \"flight.etermconfigdispatch\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isavdownloader\",\n" +
                "            \"to\": \"tcbase.skynet.log.web\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.inter.isavdownloader\",\n" +
                "            \"to\": \"tcbase.java.dss.hbase.iflight.search\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.dsf.forbidpool\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isavdownloader\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.irate.center\",\n" +
                "            \"to\": \"iflight.java.dsf.inter.isavdownloader\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"from\": \"iflight.java.web.inter.isavmanager\",\n" +
                "            \"to\": \"tcbase.skynet.log.web\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

}
