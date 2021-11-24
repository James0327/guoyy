package com.jw.james.tcly.mybatis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: test com.tcly.test.mybatis.T1
 * @Package: com.tcly.test.mybatis
 * @ClassName: T1
 * @Author: james.guo
 * @Date: 2019/10/14 22:11
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T1 {

	public static void main(String[] args) {
		Map<String, String> map = Maps.newHashMap();
		map.put("a", "AAA");
		map.put("b", "BBB");
		map.put("c", "CCC");

		System.out.println(JSON.toJSONString(map));

		List<Map<String, Object>> ret = queryForList();
		System.out.println(JSON.toJSONString(ret));
	}

	public static List<Map<String, Object>> queryForList() {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<Map<String, Object>> resultList = new ArrayList<>();

		try {
			//加载JDBC驱动
			// The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
			// Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jw";
			String user = "admin";
			String password = "guoyiyong";

			//获取数据库连接
			connection = DriverManager.getConnection(url, user, password);

			String sql = "select t.*, t.extinfo -> '$.a' ext from test t where id = ?";
			//创建Statement对象（每一个Statement为一次数据库执行请求）
			stmt = connection.prepareStatement(sql);

			//设置传入参数
			stmt.setInt(1, 1);

			//执行SQL语句
			rs = stmt.executeQuery();

			//处理查询结果（将查询结果转换成List<Map>格式）
			ResultSetMetaData rsmd = rs.getMetaData();
			int num = rsmd.getColumnCount();

			while (rs.next()) {
				Map<String, Object> map = new HashMap(16);
				for (int i = 0; i < num; i++) {
					String columnName = rsmd.getColumnName(i + 1);
					map.put(columnName, rs.getString(columnName));
				}
				resultList.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭结果集
				if (rs != null) {
					rs.close();
					rs = null;
				}
				//关闭执行
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultList;
	}

}
