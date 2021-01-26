package com.jw.pattern.proxy;

import com.jw.pattern.proxy.exception.ExceptionHandleAnno;
import com.jw.pattern.proxy.log.MethodLog;
import com.jw.pattern.proxy.record.InvokeRecordAnno;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * guoyy com.jw.pattern.proxy.record
 *
 * @Description: com.jw.pattern.proxy.ProxyTest
 * @Author: guoyiyong/james
 * @Date: 2021-01-23 19:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Component
public class ProxyCaller {

    @ExceptionHandleAnno
    @MethodLog
    @InvokeRecordAnno("测试代理模式")
    public Map<String, Object> call(String biz, String param) {
        if (biz.equals("abc")) {
            throw new IllegalArgumentException("非法的 biz=" + biz);
        }

        Map<String, Object> result = new HashMap<>(4);
        result.put("id", 123);
        result.put("nick", "之叶");
        result.put("biz", biz);
        result.put("param", param);

        return result;
    }

}
