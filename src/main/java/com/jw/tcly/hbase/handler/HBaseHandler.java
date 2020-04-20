package com.jw.tcly.hbase.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jw.tcly.hbase.conf.HBaseConf;
import com.jw.tcly.hbase.dto.HDataDTO;
import com.jw.tcly.hbase.dto.HGetRespDTO;
import com.jw.tcly.hbase.exception.HBaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * tax_complex_entrance com.ly.ic.taxbag.complex.hbase.handler
 *
 * @Description: com.ly.ic.taxbag.complex.hbase.handler.HBaseHandler
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/20 10:52
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
@Component("hBaseHandler")
public class HBaseHandler {
    @Resource(name = "hBaseConf")
    private HBaseConf hBaseConf;

    public HGetRespDTO get(String rowKey) {
        log.info("read data from hbase, rowKey: {}", rowKey);
        Request request = new Request.Builder()
                .headers(Headers.of("token", hBaseConf.getToken(),
                        "Content-Type", hBaseConf.getContentType()))
                .url(String.format("http://%s/client/get?appUk=%s&tableName=%s&rowKey=%s&columnFamily=%s",
                        hBaseConf.getDomain(), hBaseConf.getAppUk(), hBaseConf.getTableName(), rowKey, hBaseConf.getFamilyName()))
                .get().build();
        // HTTP同步请求
        try {
            Response response = HBase.CLIENT.newCall(request).execute();
            byte[] data = response.body().bytes();
            if (data != null && data.length >= 1) {
                return JSON.parseObject(data, HGetRespDTO.class);
            }
        } catch (IOException e) {
            throw new HBaseBusinessException(e.toString(), e);
        }
        return null;
    }

    public void put(String rowKey, List<Pair<String, byte[]>> data, Callback callback) {
        HDataDTO hDataDTO = new HDataDTO();
        hDataDTO.setRowKey(rowKey);
        HDataDTO.ColumnFamilie columnFamilie = new HDataDTO.ColumnFamilie();
        hDataDTO.setColumnFamilies(Lists.newArrayList(columnFamilie));
        columnFamilie.setFamilyName(hBaseConf.getFamilyName());
        List<HDataDTO.ColumnFamilie.Column> columns = Lists.newArrayListWithCapacity(data.size());
        columnFamilie.setColumns(columns);
        for (Pair<String, byte[]> kv : data) {
            HDataDTO.ColumnFamilie.Column column = new HDataDTO.ColumnFamilie.Column();
            column.setQualifier(kv.getKey());
            column.setValue(kv.getValue());
            columns.add(column);
        }
        log.info("write data to hbase, rowKey: {}", rowKey);

        Request request = new Request.Builder()
                .headers(Headers.of("token", hBaseConf.getToken(),
                        "Content-Type", hBaseConf.getContentType()))
                .url(String.format("http://%s/client/put?appUk=%s&tableName=%s",
                        hBaseConf.getDomain(), hBaseConf.getAppUk(), hBaseConf.getTableName()))
                .post(RequestBody.create(MediaType.parse(hBaseConf.getContentType()), JSON.toJSONBytes(hDataDTO)))
                .build();

        // HTTP异步请求
        HBase.CLIENT.newCall(request).enqueue(callback);
    }

    private static final class HBase {
        private static final OkHttpClient CLIENT = new OkHttpClient();
    }
}
