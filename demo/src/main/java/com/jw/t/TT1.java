package com.jw.t;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.jw.dto.Person;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: test TT1
 * @Package: com.jw.t
 * @ClassName: TT1
 * @Author: james.guo
 * @Date: 2019/5/7 22:09
 * @Version: 1.0
 */
public class TT1 {

    private ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    private enum PassengerTypeEnum {
        ADT("ADT", "成人"),
        CHD("CHD", "儿童"),
        CNN("CNN", "儿童"),
        STU("STU", "学生"),
        INF("INF", "婴儿");

        private String code;

        private String description;

        PassengerTypeEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static PassengerTypeEnum getByCode(String code) {
            for (PassengerTypeEnum passengerTypeEnum : PassengerTypeEnum.values()) {
                if (passengerTypeEnum.getCode().equals(code)) {
                    return passengerTypeEnum;
                }
            }
            return null;
        }

    }

    private static final Pattern TIME_PATTERN = Pattern.compile("^T\\d{8}$");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'T'MMddHHmm");

    @Test
    public void test() {
        threadLocal.set(new Object());
        threadLocal.get();

        threadLocal.remove();

        Pattern COMPILE = Pattern.compile("(?<=[\\-,])[\\w]{2}(?=[\\-,])");

        String str = "hgh-br,ca-tpe|tpe-hu,cz-xiy/xiy-hu,aa-hgh";

        Matcher matcher = COMPILE.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        COMPILE = Pattern.compile("(?<=,)[\\w]{2}(?=\\-)");

        str = "hgh-br,ca-tpe|tpe-hu,cz-xiy/xiy-hu,aa-hgh";

        matcher = COMPILE.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        int i = Integer.parseInt("1010", 2);
        System.out.println(i);
    }

    public static void main(String[] args) {

        for (PassengerTypeEnum type : PassengerTypeEnum.values()) {
            System.out.println(type + "/" + type.ordinal());
        }

        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        list0.add(2);

        Integer integer = list0.get(list0.size() - 1);
        System.out.println("integer:" + integer);

        int[] arr = IntStream.range(1, 10).toArray();
        System.out.println(ToStringBuilder.reflectionToString(arr));
        int len = arr.length, idx = len - 2;
        for (int i = 0; i < len; i++) {
            if (i >= idx) {
                System.out.println("i2:" + arr[i]);
                continue;
            }
            System.out.println("i:" + arr[i]);
        }

        String time = LocalDateTime.now().format(formatter);
        System.out.println("time: " + time);

        boolean flag = TIME_PATTERN.matcher(time).matches();

        System.out.println("flag: " + flag);

        Map<String, String> map = Stream.of(
                ImmutablePair.of("Amadeus", "1A"),
                ImmutablePair.of("Sabre", "1S"),
                ImmutablePair.of("Galileo", "1G"),
                ImmutablePair.of("SpaceX", "")
        ).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        System.out.println("map: " + JSON.toJSONString(map));

        String str0 = "{\"SpaceX\":\"\",\"Sabre\":\"1S\",\"Galileo\":\"1G\",\"Amadeus\":\"1A\"}";
        Map<String, String> map1 = JSON.parseObject(str0, HashMap.class);
        System.out.println(map1);

        J2CacheConfig config = new J2CacheConfig();

        J2CacheBuilder.init(config);

        String str = "MF0837";
        System.out.println(str.substring(0, 2));
        System.out.println(str.substring(2));

        Person p = new Person();
        p.setName("name1");
        p.setAge(1);
        p.setAddr("addr");

        List<Person> list = Lists.newArrayList();
        list.add(p);
        list.add(p);
        list.add(p);

        String json0 = JSON.toJSONString(list);
        String json1 = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);

        System.out.println(json0);
        System.out.println(json1);

        Type type = new TypeReference<List<Person>>() {}.getType();

        List<Person> list1 = JSON.parseObject(json0, type);
        System.out.println(list1);

        int i1 = 0xFFFFFFFF;
        System.out.println(i1 + "][" + Integer.toBinaryString(i1));

        System.out.println(new Date(1557915201118L));

        // 获取截断日期（当天）
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();

        System.out.println("date: " + date);

    }

}
