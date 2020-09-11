package com.jw.jmh.loccache;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * guoyy com.jw.jmh.loccache
 *
 * @Description: com.jw.jmh.loccache.Foo
 * @Author: guoyiyong/james
 * @Date: 2020-09-11 11:24
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Foo implements Serializable, Cloneable {
    private int idx;
    private Double amt;
    private byte sex;
    private char flag;
    private String name;
    private String phone;
    private long serNo;

    @Override
    public Foo clone() {
        try {
            return (Foo)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return new Foo();
        }
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.trimToEmpty(name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = StringUtils.trimToEmpty(phone);
    }

    public long getSerNo() {
        return serNo;
    }

    public void setSerNo(long serNo) {
        this.serNo = serNo;
    }
}
