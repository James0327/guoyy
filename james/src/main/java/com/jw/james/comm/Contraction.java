package com.jw.james.comm;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Description: guoyy
 * com.jw.comm.Contraction
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/8 19:56
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class Contraction {

    public static void main(String[] args) {

        String s = "呃㈰㈱〴〸ㄹ㈸〵䑎匷㠸䩕㍊噖奊䘱剂啇䴷呏䩅〴䱎奓䑅㍈㡉剙䩌䩐啖啚簹〶㌲㈱㜶㨱吀";
        System.out.println(ToStringBuilder.reflectionToString(s.getBytes()));

        int i = 255;
        System.out.println((char)i);

        System.out.println((int)'ÿ');

        String tempString = "TC20210408192805DNS788JU3JVVYJF1RBUGM7TOJE04LNYSDE3H8IRYJLJPUVUZ|906322176:1T";

        System.out.println("~~~~~~~~~");
        System.out.println(s.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(tempString.getBytes(StandardCharsets.UTF_8).length);
        System.out.println("~~~~~~~~~");

        System.out.println("压缩前字符串内容：" + tempString);
        System.out.println("压缩前字符串大小:" + tempString.length());
        System.out.println("压缩后字符串：" + string2HexString(tempString));

        String resultString = compactString(tempString);
        System.out.println("压缩后字符串内容：" + resultString);
        System.out.println("压缩后字符串内容：" + ToStringBuilder.reflectionToString(resultString.getBytes()));
        System.out.println("压缩后字符串大小：" + resultString.length());
        System.out.println("压缩后字符串：" + string2HexString(resultString));

        String ret1 = Base64.getEncoder().encodeToString(tempString.getBytes());
        System.out.println("压缩后字符串内容1：" + ret1);
        System.out.println("压缩后字符串大小1：" + ret1.length());

        String ret2 = Base64.getEncoder().encodeToString(resultString.getBytes());
        System.out.println("压缩后字符串内容2：" + ret2);
        System.out.println("压缩后字符串大小2：" + ret2.length());

        String convertString = decompressionString(resultString);
        System.out.println("解压后字符串内容：" + convertString);
        System.out.println("解压后字符串大小：" + convertString.length());
    }

    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int)strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * 十六进制转字节数组
     *
     * @param src
     * @return
     */
    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte)Integer
                    .valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    public static String decompressionString(String str) {
        char[] bytes = str.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, len = bytes.length; i < len; i++) {
            char c = bytes[i];
            char firstCharacter = (char)(c >>> 8);
            char secondCharacter = (char)((byte)c);
            buffer.append(firstCharacter);
            if (i == len - 1 && secondCharacter == 0) {
                break;
            }
            buffer.append(secondCharacter);
        }
        return buffer.toString();
    }

    public static String compactString(String str) {
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = str.getBytes();
        for (int i = 0, len = bytes.length; i < len; i += 2) {
            char firstCharacter = (char)bytes[i];
            char secondCharacter = 0;
            if (i + 1 < len) {
                secondCharacter = (char)bytes[i + 1];
            }
            firstCharacter <<= 8;
            buffer.append((char)(firstCharacter + secondCharacter));
        }
        return buffer.toString();
    }

}
