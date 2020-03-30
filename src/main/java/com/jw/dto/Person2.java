package com.jw.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Description: test com.jw.Person
 * @Package: com.jw
 * @ClassName: Person
 * @Author: james.guo
 * @Date: 2019/3/25 12:08
 * @Version: 1.0
 */
public class Person2 implements Serializable, Cloneable {
    private String name;
    private Integer age;
    private String addr;

    public Person2() {
    }

    public Person2(String name, Integer age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public Person2 clone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream bos = new ObjectOutputStream(baos);
            bos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream bis = new ObjectInputStream(bais);

            Object o = bis.readObject();

            System.out.println(o);

            return (Person2) o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
