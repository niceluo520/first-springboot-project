package com.test.firstspringbootproject.sys.controller;

import com.test.firstspringbootproject.sys.domain.UserDO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestController {

    public static void main(String[] args) {

        int[] a ={11,32,94,23,75,26};
        IntStream.of(a).forEach(System.out::println);
        //list集合
        List<UserDO> list = new ArrayList<>();
        for(int i=1;i<100;i++){
            UserDO userDO = new UserDO();
            userDO.setName("name"+i);
            list.add(userDO);
        }
        List<String> list1 = list.stream()
                .filter(s -> s.getName().endsWith("1"))
                .map(UserDO::getName)
                .limit(10)
                .skip(1)
                .collect(Collectors.toList());
        System.out.println(list1);
        //set集合
        Set<UserDO> set = new HashSet<>();
        for(int i=1;i<100;i++){
            UserDO userDO = new UserDO();
            userDO.setName("name"+i);
            set.add(userDO);
        }
        Set<String> s = set.stream()
                .filter(z -> z.getName().endsWith("1"))
                .map(UserDO::getName)
                .sorted()
                .limit(5)
                .skip(1)
                .collect(Collectors.toSet());
        System.out.println(s);
        //map 集合
        List<UserDO> l = new ArrayList<>();
        for(int i=1;i<100;i++){
            UserDO userDO = new UserDO();
            userDO.setId((long) i);
            userDO.setName("name"+i);
            l.add(userDO);
        }
        Map<Long,String> map = l.stream()
                .collect(Collectors.toMap(UserDO::getId,UserDO::getName));
        System.out.println(map);

        Map<Integer,String> map1 = new HashMap<>();
        map1.put(1,"aa");
        map1.put(2,"bb");
        map1.put(3,"cc");
        map1.forEach((k,v) -> System.out.println("key:value=" + k +":" + v));
    }


}
