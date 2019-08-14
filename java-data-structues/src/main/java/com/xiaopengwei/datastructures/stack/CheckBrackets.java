package com.xiaopengwei.datastructures.stack;

/**
 * 演示用栈来检查表达式中括号(小、中、大)是否成对出现
 *
 * @author XiaoPengwei.com
 */
public class CheckBrackets {
    public void check(String str) {
        MyStack ms = new MyStack(20);

        //1：把字符串转换成字符数组
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            //2：依次读取字符，如果是  括号的 前半部分，压入栈中
            if (c == '{' || c == '[' || c == '(') {
                ms.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                //3：如果是 括号的结束部分，就从栈当中弹出一个值，进行匹配
                char msc = (char) ms.pop();
                if ((msc == '{' && c != '}')
                        || (msc == '[' && c != ']')
                        || (msc == '(' && c != ')')
                ) {
                    System.out.println("sorry，匹配不成功，出错的位置==" + (i + 1) + " 个字符处");
                } else {
                    System.out.println("匹配成功");
                }
            }
        }
    }

    public static void main(String[] args) {
        CheckBrackets t = new CheckBrackets();
        t.check("(3+2)/5-[(7+8)*4-5]");
    }
}

//输出：32+5/78+4*5--

//栈：-54







