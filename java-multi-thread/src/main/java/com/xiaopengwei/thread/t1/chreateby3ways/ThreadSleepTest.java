package com.xiaopengwei.thread.t1.chreateby3ways;/** * <p> * * </p> * * @author XiaoPengwei.com * @since 2019-04-22 */public class ThreadSleepTest {    public static void main(String[] args) {        try {            for (int i = 0; i < 10; i++) {                //随机等待时间                int time = (int) (Math.random() * 900);                Thread.sleep(time);                System.out.println("main=" + Thread.currentThread().getName());            }        } catch (InterruptedException e) {            e.printStackTrace();        }    }}