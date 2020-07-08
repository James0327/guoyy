package com.jw.t;

import com.alibaba.fastjson.JSON;
import com.ly.ic.taxbag.complex.bag.request.BaggageRequestDTO;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: test Test01
 * @Package: com.jw.t
 * @ClassName: Test01
 * @Author: james.guo
 * @Date: 2019/9/20 19:14
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test01 {

	@Test
	public void test() {
		_2();
	}

	public void _2() {
		String json = "{\n" +
				"    \"passengers\": [\n" +
				"        {\n" +
				"            \"passengerType\": \"ADT\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"passengerType\": \"CHD\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"policies\": [\n" +
				"        {\n" +
				"            \"airlineIndex\": 1,\n" +
				"            \"policyId\": \"0\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"resourceType\": \"1T\",\n" +
				"    \"segments\": [\n" +
				"        {\n" +
				"            \"aircraft\": \"320\",\n" +
				"            \"airlineIndex\": 1,\n" +
				"            \"arrAirportCode\": \"HKG\",\n" +
				"            \"arrAirportTerm\": \"1\",\n" +
				"            \"arrCityCode\": \"HKG\",\n" +
				"            \"arrDateTime\": \"201910242345\",\n" +
				"            \"cabinClass\": \"Y\",\n" +
				"            \"cabinCode\": \"B\",\n" +
				"            \"depAirportCode\": \"PVG\",\n" +
				"            \"depAirportTerm\": \"T1\",\n" +
				"            \"depCityCode\": \"SHA\",\n" +
				"            \"depDateTime\": \"201910242120\",\n" +
				"            \"duration\": 145,\n" +
				"            \"flightShare\": false,\n" +
				"            \"index\": 1,\n" +
				"            \"marketingFlightNo\": \"MU0725\",\n" +
				"            \"mileage\": 773,\n" +
				"            \"operatingFlightNo\": \"MU0725\",\n" +
				"            \"stopTime\": 0\n" +
				"        }\n" +
				"    ],\n" +
				"    \"traceId\": \"CN201910231431414IF0WBO9P83BG3F8EK7U1FJ0FAMAJDK2HOJP5G68J7WI4KS9\",\n" +
				"    \"unitKey\": \"1T/IBE/SHASELOW20191217/CFSY/ADT|1/SZV122/NORMAL/$//222234/5261026524\"\n" +
				"}";

		BaggageRequestDTO baggageRequestDTO = JSON.parseObject(json, BaggageRequestDTO.class);

		System.out.println(JSON.toJSONString(baggageRequestDTO));
	}

	public void _1() {
		Pattern compile = Pattern.compile("(?<![\\w])[\\w]{2}(?!=[\\w])");

		String str = "sha-ca,ca-lax/lax-aa,aa-bjs";

		Matcher matcher = compile.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static void main(String[] args) {
		int t = -1;
		System.out.println("t:" + Integer.toBinaryString(t));
		int i = 1;
		System.out.println("i:" + Integer.toBinaryString(i));
		int i2 = 1 << 1;
		System.out.println("i2:" + Integer.toBinaryString(i2));
		int i4 = 1 << 2;
		System.out.println("i4:" + Integer.toBinaryString(i4));

		t = t ^ i2;

		System.out.println("t[" + Integer.toBinaryString(t));

	}

}
