package com.xiaopengwei.thread.t5;/** * <p> * * @author XiaoPengwei * @since 2019-04-24 */public class ThreadIsAliveTest {    public static void main(String[] args) {        MyThread myThread = new MyThread();        System.out.println("before start: "+myThread.isAlive());        myThread.start();        System.out.println("!after start: "+myThread.isAlive());    }}