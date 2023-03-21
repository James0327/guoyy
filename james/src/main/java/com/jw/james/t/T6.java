package com.jw.james.t;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description: test T6
 * @Package: com.jw
 * @ClassName: T6
 * @Author: james.guo
 * @Date: 2019/3/20 14:33
 * @Version: 1.0
 */
public class T6 {

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        IntStream.range(0, 10000).forEach(l1::add);
        IntStream.range(0, 10000).parallel().forEach(l2::add);

        System.out.println("串行执行的大小：" + l1.size());
        System.out.println("并行执行的大小：" + l2.size());

        Random r = new Random();

        List<Foo> list = new ArrayList<>();
        List<Foo2> list2 = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Foo foo = new Foo("name" + r.nextInt(i), "addr" + r.nextInt(i));
            Foo2 foo2 = new Foo2("name" + r.nextInt(i), "addr2-" + r.nextInt(i));
            list.add(foo);
            list2.add(foo2);
        }

        Map<String, String> list2Map = list2.stream().collect(Collectors.toMap(Foo2::getName, Foo2::getAddr2, (a, b) -> a));

        Map<String, List<String>> listMap = list.stream().collect(Collectors.groupingBy(Foo::getName,
                Collectors.mapping(e -> list2Map.get(e.getName()), Collectors.toList())));

        System.out.println("listMap:" + listMap);
    }

    private static class Foo {
        private String name;
        private String addr;

        public Foo(String name, String addr) {
            this.name = name;
            this.addr = addr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
    }

    private static class Foo2 {
        private String name;
        private String addr2;

        public Foo2(String name, String addr2) {
            this.name = name;
            this.addr2 = addr2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr2() {
            return addr2;
        }

        public void setAddr2(String addr2) {
            this.addr2 = addr2;
        }
    }

}
