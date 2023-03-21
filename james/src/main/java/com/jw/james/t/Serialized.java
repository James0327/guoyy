package com.jw.james.t;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jw.james.dto.Clazz;
import com.jw.james.dto.SubClass;
import com.jw.james.dto.SuperClass;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeEnv;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;

/**
 * @Description: test Serialized
 * @Package: com.jw.t
 * @ClassName: Serialized
 * @Author: james.guo
 * @Date: 2019/9/26 10:48
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Serialized {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.setStr("aaa");
        subClass.setIdx(1);

        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        IdStrategy idStrategy = RuntimeEnv.ID_STRATEGY;

        RuntimeSchema<SuperClass> schema = RuntimeSchema.createFrom(SuperClass.class, idStrategy);
        byte[] data = ProtostuffIOUtil.toByteArray(subClass, schema, buffer);
        SuperClass superClass = new SuperClass();
        ProtostuffIOUtil.mergeFrom(data, superClass, schema);
        System.out.println(superClass);

        Clazz clazz = new Clazz();
        Map<Integer, Map<String, SuperClass>> map = Maps.newHashMap();
        clazz.setMap(map);
        Map<String, SuperClass> subMap = Maps.newHashMap();
        subMap.put("SubClass", subClass);
        map.put(1, subMap);

        LinkedBuffer buffer1 = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        DefaultIdStrategy strategy = new DefaultIdStrategy(0, null, 0);
        RuntimeSchema<Clazz> schema1 = RuntimeSchema.createFrom(Clazz.class, strategy);
        byte[] data0 = ProtostuffIOUtil.toByteArray(clazz, schema1, buffer1);

        RuntimeSchema<Clazz> schema2 = RuntimeSchema.createFrom(Clazz.class);
        Clazz clazz1 = new Clazz();
        ProtostuffIOUtil.mergeFrom(data0, clazz1, schema2);
        System.out.println(clazz1);

        System.out.println(JSON.toJSONString(clazz));

        String json = "{\"map\":{1:{\"SubClass\":{\"idx\":1,\"str\":\"aaa\"}}}}";
        Clazz clazz2 = JSON.parseObject(json, Clazz.class);
        System.out.println(clazz2);
    }

}
