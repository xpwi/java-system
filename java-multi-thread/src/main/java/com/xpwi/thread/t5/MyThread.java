package com.xpwi.thread.t5;/** * <p> * * @author github.com/xpwi * @since 2019-04-24 */public class MyThread extends Thread {    public MyThread() {        System.out.println("MyThread()：" + this.isAlive());    }    @Override    public void run() {        System.out.println("run():" + this.isAlive());    }}