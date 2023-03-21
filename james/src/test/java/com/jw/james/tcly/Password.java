package com.jw.james.tcly;

/**
 * Description: guoyy
 * com.jw.james.tc.Password
 *
 * @author guoyiyong/james
 * Date: 2022/9/13 15:30
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
public class Password {

    public static void main(String[] args) throws Exception {
        /*
         * TEFlyCtripOrder/wYGW66no4No8TVWM2rYd7Ep
         * 10.100.49.26 3500
         */
        String password = "36LP43HA3QJRL2FNTFS7MQE7CVJREMO2ESJEWX3DB6M2OWMLZHEQ";
        String projectId = "bjiflihgt.java.crawler.mng";

        String decrypt = com.ly.dal.util.StringEncrypt.decrypt(password, projectId);
        // decrypt: wYGW66no4No8TVWM2rYd7Ep
        System.out.println("decrypt: " + decrypt);
    }
}
