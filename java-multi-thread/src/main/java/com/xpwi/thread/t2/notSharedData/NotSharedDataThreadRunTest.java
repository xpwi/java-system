package com.xpwi.thread.t2.sharedData.notSharedData;import com.xpwi.thread.t2.notSharedData.NotSharedDataThread;/** * <p> * 不共享数据的结果 * * @author github.com/xpwi * @since 2019-04-22 */public class NotSharedDataThreadRunTest {    public static void main(String[] args) {        NotSharedDataThread a = new NotSharedDataThread("A");        NotSharedDataThread b = new NotSharedDataThread("B");        NotSharedDataThread c = new NotSharedDataThread("C");        System.out.println("不共享数据的结果: \n每个线程计算自己的数");        a.start();        b.start();        c.start();    }}