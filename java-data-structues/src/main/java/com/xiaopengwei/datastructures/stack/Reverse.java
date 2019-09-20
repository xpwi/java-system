package com.xiaopengwei.datastructures.stack;

/**
 * 演示应用栈来实现字符串倒转
 *
 * @author xpwi
 */
public class Reverse {
    public String doRev(String str) {
        StringBuffer buffer = new StringBuffer();

        MyStack ms = new MyStack(20);
        //1：把字符串按照char一个一个读取出来
        char[] cs = str.toCharArray();
        for (char c : cs) {
            //2：把这些字符依次压入栈中
            ms.push(c);
        }
        //3：依次从栈中弹出char，构成新的字符串
        while (!ms.isEmpty()) {
            char c = (char) ms.pop();
            buffer.append(c);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Reverse t = new Reverse();
        String ret = t.doRev("this is 阿  chreateby3ways");
        System.out.println("ret===" + ret);
    }
}
