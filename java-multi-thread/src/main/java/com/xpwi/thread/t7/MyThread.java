package com.xpwi.thread.t7;/** * <p> * * @author xpwi * @since 2019-04-24 */public class MyThread extends Thread {    public MyThread() {        System.out.println("MyThread()：" + this.isAlive());    }    @Override    public void run() {        System.out.println("run():" + this.isAlive());    }}