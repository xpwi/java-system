package com.xpwi.thread.t4;/** * <p> * * @author github.com/xpwi * @since 2019-04-24 */public class MyThread extends Thread {    public MyThread() {        System.out.println("构造方法的打印：" + Thread.currentThread().getName());    }    @Override    public void run() {        System.out.println("run方法的打印：" + Thread.currentThread().getName());    }}