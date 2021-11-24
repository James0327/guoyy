package com.jw.james;

import com.jw.james.dto.Foo;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description: guoyy
 * com.jw.Test9
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/9/16 15:20
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class Test9 {

    public static void main(String[] args) {

        List<Foo> fareParameterList = Collections.nCopies(4, null);
        Foo  obj = new Foo();
        obj.setId(1L);
        fareParameterList.set(2, obj );

        Iterator<Foo> iterator = fareParameterList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                iterator.remove();
            }
        }
        //        fareParameterList.listIterator();



        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.remark");
        Foo foo = new Foo();
        foo.setRemark("张三");
        System.out.println("ret: " + expression.getValue(foo));

        // the difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC.
        long millis = System.currentTimeMillis();

        Date date = new Date(millis);

        System.out.println(date);
        System.out.println(org.joda.time.LocalDateTime.fromDateFields(date));

        ZonedDateTime zonedDateTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
        String s = zonedDateTime.toString();
        System.out.println(s);

        long time = Date.from(zonedDateTime.toInstant()).getTime();

        System.out.println(millis);
        System.out.println(time);

        System.out.println(Long.toBinaryString(millis));
        System.out.println(Long.toBinaryString(time));

        long h24 = TimeUnit.DAYS.toMillis(1);
        int offset = (int)(millis % h24);
        long t2 = millis - offset;
        System.out.println(new Date(millis));
        System.out.println(new Date(t2));
        System.out.println(new Date(time));
        System.out.println("~~~~~~~~~");

        for (int i = -999; i < 999; i++) {
            long milliseconds = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
            long ret = milliseconds % h24;
            if (ret != 57600000) {
                System.out.println(millis + "/" + ret);
            }
        }
        System.out.println("end");

        long milli = LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0)
                .toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(milli);
        System.out.println(milli % h24);
        System.out.println(new Date(milli));
    }

}
