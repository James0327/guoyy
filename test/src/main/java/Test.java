import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Description: guoyy
 * PACKAGE_NAME.Test
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/19 15:19
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class Test {

    public static void main(String[] args) {
        Foo[] foos = new Foo[30];

        List<Foo> fooList = Arrays.asList(foos);

        System.out.println( CollectionUtils.isEmpty(fooList)  + "][" +fooList);
        System.out.println(fooList.stream().filter(Objects::nonNull).count() + "][" + fooList);

        foos[3] = new Foo();

        System.out.println(CollectionUtils.isEmpty(fooList) + "][" + fooList);
        System.out.println(fooList.stream().filter(Objects::nonNull).count() + "][" + fooList);

        long t = (86400000 - LocalTime.parse("23:59:00", DateTimeFormat.forPattern("HH:mm:ss")).getMillisOfDay()) / 1000L;
        System.out.println("t: " + t);
        System.out.println("t: " + t / 60D / 60);

        LocalDateTime now = LocalDateTime.now();

        int seconds = Seconds.secondsBetween(now, now.plusDays(1)).getSeconds();
        System.out.println("seconds: " + seconds);
        int millisOfDay = LocalTime.now().getMillisOfDay();
        System.out.println((24 * 60 * 60D - millisOfDay / 1000) / 60 / 60);
        int millisOfDay1 = LocalDateTime.now().getMillisOfDay();
        System.out.println((24 * 60 * 60D - millisOfDay1 / 1000) / 60 / 60);

        long standardSeconds = Duration.standardSeconds(System.currentTimeMillis() / 1000).getStandardSeconds();
        System.out.println("standardSeconds: " + standardSeconds);

        List<Foo> fareParameterList = new ArrayList<>(Collections.nCopies(4, null));
        Foo obj = new Foo();
        obj.setId(1L);
        fareParameterList.set(2, obj);

        Iterator<Foo> iterator = fareParameterList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                iterator.remove();
            }
        }

        System.out.println(fareParameterList);
    }

}
