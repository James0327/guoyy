package com.jw.james.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * guoyy com.jw.controller
 *
 * @Description: com.jw.controller.JobController
 * @Author: guoyiyong/james
 * @Date: 2020-07-28 10:58
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@RestController
@RequestMapping("/basedata/job")
public class JobController {

    @RequestMapping(value = {"/index", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    private String fire(@RequestParam(value = "token", required = false) String token,
                        @RequestBody(required = false) Map<String, Object> context) {
        System.out.println("token: " + token);
        System.out.println("context: " + JSON.toJSONString(context));

        return "ok";
    }

}
