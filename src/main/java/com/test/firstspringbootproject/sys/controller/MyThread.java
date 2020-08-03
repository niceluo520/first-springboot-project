package com.test.firstspringbootproject.sys.controller;


import java.util.Arrays;
import java.util.List;

public class MyThread implements Runnable{

    //剩余票数
    public int num = 10;
    //出票数
    public int count = 0;

    @Override
    public void run() {
        while (true){
            synchronized (this){
                if (num <= 0){
                    break;
                }
                num--;
                count++;
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"抢到第"+ count+"张，剩余"+ num);
            }
        }
    }

    public static void main(String[] args) {
        MyThread ticket = new MyThread();
        Thread t1 = new Thread(ticket,"a1");
        Thread t2 = new Thread(ticket,"a2");
        t1.start();
        t2.start();
        String[] aa = {"ss","zz"};
        List list = Arrays.asList(aa);
    }
}
