package com.xiaopengwei.thread.t3.synchronizeSafe;/** * <p> * A登录 * * @author XiaoPengwei * @since 2019-04-24 */public class UserALoginThread extends Thread {    @Override    public void run() {        SynchronizedLoginServlet.doPost("userA","passwordA");    }}