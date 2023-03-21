package com.jw.james.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * guoyy com.jw.dto
 *
 * @Description: Person
 * @Author: guoyiyong/james
 * @Date: 2020-01-07 11:25
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Person {
    private int id;
    private String name;
    private int age;
    private byte sex;
    private String phone;
    private String addr;

    public Person() {
    }

    public Person(int id, String name, int age, byte sex, String phone, String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(age).append(sex).append(phone).append(addr).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person)o;

        return new EqualsBuilder().append(id, person.id).append(age, person.age).append(sex, person.sex).append(name, person.name).append(phone, person.phone).append(addr, person.addr).isEquals();
    }
}
