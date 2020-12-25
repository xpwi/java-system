package com.xpwi.datastructures.stack;

/**
 * 演示使用栈来计算后缀表达式，只计算0-9的数值
 *
 * @author github.com/xpwi
 */
public class Calculate {
    /**
     * 计算一个后缀表达式
     *
     * @param str 后缀表达式
     * @return 返回计算的结果
     */
    public int calculate(String str) {
        MyStack ms = new MyStack(20);
        //1：依次获取字符串中的字符
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];

            //2：如果是操作数，压入栈中
            if (c >= '0' && c <= '9') {
                ms.push((int) (c - '0'));
            } else {
                //3：如果是操作符，从栈中取两个值进行计算，然后把结果压入栈中
                int num2 = ms.pop();
                int num1 = ms.pop();
                int temp = 0;
                if (c == '+') {
                    temp = num1 + num2;
                } else if (c == '-') {
                    temp = num1 - num2;
                } else if (c == '*') {
                    temp = num1 * num2;
                } else if (c == '/') {
                    temp = num1 / num2;
                }
                //压回栈中
                ms.push(temp);
            }
        }

        return ms.pop();
    }

    public static void main(String[] args) {
        Calculate t = new Calculate();

        String str = new PostInfix().doTransfer("((7+9)/3-4)*3-9");

        int ret = t.calculate(str);
        System.out.println("ret==" + ret);
    }
}
