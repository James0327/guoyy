package com.jw.jdbc;

import com.alibaba.fastjson.JSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Description: guoyy
 * com.jw.jdk.TestJdbc
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/2/27 11:49
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class TestJdbc {

    public static void main(String[] args) throws Exception {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/jw?characterEncoding=UTF-8&useSSL=false";
        String userName = "admin";
        String password = "guoyiyong";

        // 加载MySql驱动 （可选）
        Class.forName(driverName);
        // 获得数据库连接
        Connection conn = DriverManager.getConnection(url, userName, password);
        // 创建Statement\PreparedStatement对象
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select count(*) cnt from t1");
        while (rs.next()) {
            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println("MetaData:" + JSON.toJSONString(metaData));
            System.out.println("ColumnName:" + metaData.getColumnName(1));
            Long cnt = rs.getLong("cnt");
            System.out.println("cnt: " + cnt);
        }
        rs.close();
        stmt.close();
        conn.close();
    }

}
