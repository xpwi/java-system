package com.xpwi.datastructures.stack;

/**
 * 演示基本的栈的操作
 *
 * @author github.com/xpwi
 */
public class MyStack {
    private int[] datas;
    private int topIndex = -1;

    public MyStack(int length) {
        datas = new int[length];
    }

    public void push(int d) {
        topIndex++;
        datas[topIndex] = d;
    }

    public int pop() {
        int ret = datas[topIndex];
        topIndex--;
        return ret;
    }

    public int peek() {
        return datas[topIndex];
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public boolean isFull() {
        return topIndex == (datas.length - 1);
    }

    public void printStack() {
        System.out.println("====================>");
        for (int i = 0; i <= topIndex; i++) {
            System.out.println(datas[i]);
        }
    }

    public static void main(String[] args) {
        MyStack t = new MyStack(10);

        t.push(3);
        t.push(5);
        t.push(9);
        t.push(2);

        t.printStack();

        int ret1 = t.peek();
        System.out.println("ret1==" + ret1);

        t.pop();

        t.printStack();
        int ret2 = t.pop();
        System.out.println("ret2==" + ret2);

        t.printStack();
    }
}
