package com.xpwi.thread.t2.notSharedData;/** * <p> * 线程 * 内容：线程间通信 共享数据 * * @author github.com/xpwi * @since 2019-04-22 */public class NotSharedDataThread extends Thread {    private int count = 5;    public NotSharedDataThread(String name) {        super(name);    }    @Override    public void run() {        super.run();        //当等于 0 时停止        //请不要使用 for 循环        while (count > 0) {            count--;            System.out.println("由" + this.currentThread().getName() + "计算, count="+count);        }    }}