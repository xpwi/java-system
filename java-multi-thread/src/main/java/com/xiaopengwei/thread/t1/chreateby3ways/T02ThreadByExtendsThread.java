package com.xiaopengwei.thread.t1.chreateby3ways;/** * <p> * 定义线程中执行内容 * </p> * * @author XiaoPengwei.com * @since 2019-04-18 */public class T02ThreadByExtendsThread extends Thread {    private int i;    //构造方法, 无参数构造, 线程名称    public T02ThreadByExtendsThread() {    }    //构造方法, 带参数构造, 线程名称    T02ThreadByExtendsThread(String name) {        super();        this.setName(name);    }//    效果同上//    T02ThreadByExtendsThread(String name) {//        super(name);//    }    @Override    public void run() {        for (; i < 10; i++) {            System.out.println("第" + i + "个 T02ThreadByExtendsThread：" + getName());        }    }}