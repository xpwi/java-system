package com.xpwi.thread.t8;/** * <p> * * @author XiaoPengwei * @since 2019-04-24 */public class MyThread extends Thread {    public MyThread() {        System.out.println("MyThread()：" + this.isAlive());    }    @Override    public void run() {        super.run();        System.out.println("run():" + this.isAlive());    }}