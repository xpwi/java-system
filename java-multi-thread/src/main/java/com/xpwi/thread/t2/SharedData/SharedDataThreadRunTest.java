package com.xpwi.thread.t2.SharedData;/** * <p> * 共享数据 * 使用父类 Thread 的构造方法去构造 * * @author xpwi * @since 2019-04-22 */public class SharedDataThreadRunTest {    public static void main(String[] args) {        //共享数据的创建线程方式//        SharedDataThread sharedDataThread = new SharedDataThread();        SynchronizedSharedDataThread synchronizedSharedDataThread = new SynchronizedSharedDataThread();        Thread threadA = new Thread(synchronizedSharedDataThread, "A");        Thread threadB = new Thread(synchronizedSharedDataThread, "B");        Thread threadC = new Thread(synchronizedSharedDataThread, "C");        Thread threadD = new Thread(synchronizedSharedDataThread, "D");        Thread threadE = new Thread(synchronizedSharedDataThread, "E");        System.out.println("共享数据的结果: \n所有线程共同计算");        threadA.start();        threadB.start();        threadC.start();        threadD.start();        threadE.start();    }}