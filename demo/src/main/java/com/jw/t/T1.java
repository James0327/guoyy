package com.jw.t;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T1 {
    private static final Pattern PATTERN = Pattern.compile("^[\\d]+$");

    private static final Pattern PATTERN2 = Pattern.compile("(\\d+)(?:[PpCc]{2}|[Pp]|[Kk])$");

    @Test
    public void test() {
        int a = 1;
        int b = 2;
        String c = "3";

        System.out.println(a + b + c);
    }

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024),
                new BasicThreadFactory.Builder().namingPattern("xxx-%d").build(), (Runnable r, ThreadPoolExecutor e) -> {
            String msg = String.format("Thread pool is EXHAUSTED! Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d), Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                    "", e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(), e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
            throw new RejectedExecutionException(msg);
        });
        pool.submit(() -> {
            System.out.println();
            System.out.println();
        });

        Map<String, Object> map = new HashMap<>(11);
        for (int i = 0; i < 12; i++) {
            map.put("k" + i, new Object());
        }
        map.put("key", new Object());
        map.remove("key");

        int a = 123;
        System.out.println(a * 4);
        System.out.println(a << 2);

        System.out.println("$$$$$$$$$$$$$$$$$$$$");

        List<String> ret1 = Stream.of(StringUtils.split("2,4,20,3,4,6", ","))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        List<String> ret2 = Stream.of(StringUtils.split("3,2,4,20,4,6", ","))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        List<String> ret3 = Stream.of(StringUtils.split("2,4,20,4,6", ","))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);

        System.out.println(ret1.equals(ret2));
        System.out.println(ret1.equals(ret3));

        String[] split = StringUtils.split(" adfa   * dd*f* aa", "* ");
        String[] split1 = StringUtils.split(" adfa    dd ", " *");

        System.out.println(split);
        System.out.println(split1);

        LocalDateTime date = LocalDateTime.now();
        int mon = date.getMonth().getValue();
        int day = date.getDayOfMonth();

        System.out.println("mon:" + mon);
        System.out.println("day:" + day);

        System.out.println(String.format("[  %02d%02d]", mon, day));
        System.out.println(String.format("[  %02d%02d]", 1, 1));

        String[] src = {"2kg", "2lb", "3pc", "2", "4", "3lg", "4P"};

        for (int i = 0, len = src.length; i < len; i++) {
            Matcher matcher = PATTERN2.matcher(src[i]);
            boolean matches = matcher.matches();
            System.out.println(src[i] + "//" + matches);
            if (matches) {
                System.out.println(matcher.groupCount() + "][" + matcher.group() + "][" + matcher.group(1));
            }
            System.out.println("*********");
        }
        System.out.println();

        boolean flag = PATTERN.matcher("009").matches();
        System.out.println("009:" + flag);
        flag = PATTERN.matcher("a09").matches();
        System.out.println("a09:" + flag);
        flag = PATTERN.matcher("0a9").matches();
        System.out.println("0a9:" + flag);

        String str0 = "b_pvg-ho,ho-vvo";

        String s = "b_pvg-ho,ho-vvo|b_pvg-ho,ho-vvo".replaceAll(",[a-zA-Z]{2}(?=\\-)", "");
        System.out.println(s);

        System.out.println(str0.substring(str0.indexOf("_") + 1));
        System.out.println();

        String[] strs = new String[]{};
        ImmutableList iList = ImmutableList.copyOf(strs);

        System.out.println(iList);

        String str = "eJy9VVuT0jAUflVm+A+dPLOaprTQzvggRZRFgaV1dMfZh1ACm7G2bJrq7jD8WP+J5zTlsiuulwcfKMl3bl++JCfRk+8bkijBtejDjwSEUds/o96Z3bVsP6DdwPFIi4hsJTMR363RZfq+93YYArpM5epaR2L1RWS6IMGnDeFSJfkCvXoj8OBKya88hWkcjQ/zUOq70Ljdw+9x8M9sFlM3aLsBpeAz5ysSQIU0zb+JxVSKREBNgtEG+iCQThVPti2S8LnMsAKpx2HKC4y4BGAh1lzpUmG5N+P+MXJM7nJybDlBzwFuhp64KeW6VqjjdPfyjMsvc6EwzPfRTxbvuMxq0QDWqhQA52uhuJbZanA/qjeq43K1kn1RgNw3wz4J7BYpTI4dsG09Ir9Z4wn5qxWelp95MWX79f2r/JPfyW9OwEn571se0nNjBvTcP5a/6/+b/FXcA/nZQ/nZ9gqSZ/O8zBYDrsRwAdG2QxmzPeqxtt91OvBhFQV0CK95thKLAwVZjHM0ALLkaWGgi4PbAYz57U/RealP12Zd2+l2vI7dscFtrWQihtkyN9d1zgthakIbKJUSWbITPxzjRonbKUZgPtdFnbP82wHxqp1eQoIeL2SBkZiXxG9n8fkUvCdmcGWcZmVq7OdTimSqv9qGW1fZXs0sMOEXLGs4MtB7hKp39mUfb/PNI3QvYFzzNEMgqLmSy2WVnVI8Gvi9Qvz2kUwxWolfJzOzDhDe4kX7G+Gc9kPhEPm/woVv+v9ZOJf5x8rhdIt3RImiTHWkuS6xG7TBScvks9AhdqH60iGoeGJOcjXZX+4ZnoAyk3ok7owROpdcQG94vcCEw94rsm02mo0dPBACKAQW7N1TnexmPn1GnyaY98gOEEbqpC80l2lgNRsb6D84RhH27EkA0Vs07iTYL//gDZEy0f28nKfAG+u1KiSCotkKJaZ2OCbbo6y+SdtsVMSOSfy6zoZcXgxJ4HqY/3I2IIHDMM0hq9Pxfst2g2wmkKeDaWAcj0jgtesJnMSA1mOgXC8GJtEHeHjQ7bgcc+tVbBKeXAtY74sqDqOgbMtCGtiDXsAssGvnj5E1iIbPe6NmI7J6IwufvdhmkVhb8HhRh1KLum0X3gQaWdDh925df8JcdAMTY9S1KKMUQ2q3Hy0Ln0s=";

        byte[] decode = Base64.getDecoder().decode(str);
        System.out.println(new String(decode));
    }
}
