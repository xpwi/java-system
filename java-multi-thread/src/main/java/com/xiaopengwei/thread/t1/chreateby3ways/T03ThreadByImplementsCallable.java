package com.xiaopengwei.thread.t1.chreateby3ways;import java.util.concurrent.Callable;/** * <p> * 通过实现 callable * </p> * * @author xpwi * @since 2019-04-18 */public class T03ThreadByImplementsCallable implements Callable<Integer> {    private int i = 0;    //jdk 1.5 不支持接口覆盖, 请使用 1.8+    @Override    public Integer call() {        for (;i<10;i++){            System.out.println("第"+i+"个 Thread："+Thread.currentThread().getName());        }        //结果是 return        return i;    }}