package com.jw.james.t;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jw.james.dto.Person2;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: test T9
 * @Package: com.jw
 * @ClassName: T9
 * @Author: james.guo
 * @Date: 2019/4/24 11:04
 * @Version: 1.0
 */
public class T9 {

    @Test
    public void test() {
        int i = 1;
        int j = ~i;

        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(j));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

        boolean flag = (i & j) == 0;
        System.out.println(flag);
    }

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();

        String val = map.put("key1", null);
        System.out.println("val:" + val);

        String val2 = map.putIfAbsent("key1", "val1");
        System.out.println("val2:" + val2);

        String val3 = map.putIfAbsent("key1", "val3");
        System.out.println("val3:" + val3);

        String val4 = map.put("key1", "key4");
        System.out.println("val4:" + val4);

        Person2 p1 = new Person2();
        p1.setAddr("addr");
        p1.setAge(1);
        p1.setName("name1");

        Object p2 = p1.clone();

        System.out.println(p1 == p2);

        Date date = new Date(1556180053156L);
        System.out.println(date);

        Boolean flag = Boolean.valueOf(null);
        System.out.println("flag: " + flag);

        boolean flag2 = Boolean.valueOf("");
        System.out.println("flag: " + flag2);

        List<Person2> list1 = new ArrayList<>();
        list1.add(new Person2("name1", 1, "addr1"));
        list1.add(new Person2("name11", 11, "addr11"));

        List<Person2> list2 = new ArrayList<>();
        list2.add(new Person2("name2", 10, "addr2"));

        System.out.println("list2: " + JSON.toJSONString(list2));

        list2.addAll(list1);

        System.out.println("list1: " + JSON.toJSONString(list1));
        System.out.println("list2: " + JSON.toJSONString(list2));

        double ceil = Math.ceil(3.4);
        System.out.println("ceil: " + ceil);

        String[] strs = {
                "baggage_dec"
                , "Max Length Up to 59 in/150 cm"
                , "Max Length Up To 79 in/200 cm"
                , "Over 79 in/200 cm in length"
                , "Up to 29 li/74 lcm"
                , "Over 29 li/74 lcm"
                , "Up to 130 li /330 lcm"
                , "Up to 118 li/300 lcm"
                , "Over 118 li/ 300 lcm"
                , "Up to 126 li/320 lcm"
                , "Over 115 li/292 lcm"
                , "Up to 34 li/85 lcm"
                , "Up to 36 li/92 lcm"
                , "Up to 39 inches"
                , "Up to 39 li/100 lcm"
                , "Up to 41 li/105 lcm"
                , "Up to 40 li/101 lcm"
                , "Up to 42 in/107 cm"
                , "Up to 45 li/115 lcm"
                , "Up to 43 li/110 lcm"
                , "Up to 46 li/118 lcm"
                , "Up to 51 li/130 lcm"
                , "Up To 150 li"
                , "Up to 59 in/150 cm"
                , "Over 59 in/150 cm"
                , "Up to 50 li/127 lcm"
                , "Up to 55 li/140 lcm"
                , "Over 55 li/140 lcm"
                , "Up to 80 li/203 lcm"
                , "Up to 81 li /208 lcm"
                , "Up to 62 li/158 lcm"
                , "Over 62 li/158 lcm"
                , "Up to 65 li/165 lcm"
                , "Over 80 li /203 lcm"
                , "Up to 85 li/215 lcm"
                , "Up to 115 li/292 lcm"
                , "Up to 90 li/230 lcm"
                , "Up to 98 in/250 cm"
                , "Up to 107 li/272 lcm"
                , "Up to 177 in/450 cm in length"
                , "Up to 109 li/277 lcm"
                , "Up to 120 in"
                , "Up to 109 in/277 cm"
                , "Over 98 in/250 cm"
                , "Up to 160 li/406 cm"
                , "Over 109 in/277 cm"
                , "Musical Instrument"
                , "Pet in Cabin"
        };

        Pattern pattern = Pattern.compile("([\\d]+) ((?:cm)|(?:lcm)|(?:in)|(?:li))");

        for (int i = 0, len = strs.length; i < len; i++) {
            String str = strs[i];
            Matcher matcher = pattern.matcher(str);
            String ret = null;
            String type = null;
            while (matcher.find()) {
                //				System.out.println("groupCount:" + matcher.groupCount());
                //				System.out.println("group:" + matcher.group());
                //				for (int j = 1; j <= matcher.groupCount(); j++) {
                //					System.out.print("group-" + j + ":" + matcher.group(j) + "\t");
                //				}
                //				System.out.println();
                ret = matcher.group(1);
                type = matcher.group(2);
            }

            System.out.println(String.format("str:%s, ret:%s:%s", str, ret, type));
        }

        String str = "1__20190703_KKE00RCB_K";

        String[] s = StringUtils.splitPreserveAllTokens(str, "_");
        System.out.println("0:" + JSON.toJSONString(s));

        s = StringUtils.split(str, "__");
        System.out.println("1:" + JSON.toJSONString(s));

        s = StringUtils.splitByWholeSeparator(str, "__");
        System.out.println("2:" + JSON.toJSONString(s));

    }
}
