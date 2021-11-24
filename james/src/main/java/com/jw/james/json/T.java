package com.jw.james.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Description: guoyy
 * com.jw.json.T
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/16 11:01
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class T {

    public static void main(String[] args) {
        Foo<BaseFoo, BaseInfo> foo = new Foo<>();
        BaseFoo baseFoo = new BaseFoo();
        baseFoo.setId(1);
        baseFoo.setRemark("baseFoo");

        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setTraceId("XXX");
        baseInfo.setRemark("baseInfo");

        foo.setT1(baseFoo);
        foo.setT2(baseInfo);

        System.out.println(JSON.toJSONString(foo));

        JSONObject o = (JSONObject)JSON.toJSON(foo);
        System.out.println(o);

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(o.getJSONObject("t1").getInnerMap());
        jsonObject.putAll(o.getJSONObject("t2").getInnerMap());

        System.out.println(jsonObject.toJSONString());
    }

}
