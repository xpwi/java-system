package com.xiaopengwei.datastructures.stack;

/**
 * 演示用栈来实现 把中缀表达式转换成为后缀表达式
 *
 * @author XiaoPengwei.com
 */
public class PostInfix {
    /**
     * 把中缀表达式转换成为后缀表达式
     *
     * @param str 要转换的中缀表达式
     * @return 转换成的后缀表达式
     */
    public String doTransfer(String str) {
        StringBuffer buffer = new StringBuffer();
        MyStack ms = new MyStack(20);

        //1：把字符串转换成为字符数组
        char[] cs = str.toCharArray();
        //2：对每个字符进行判读并执行相应的操作
        for (int i = 0; i < cs.length; i++) {
            char c = (char) cs[i];
            //2.1：如果是操作符，就要分级别操作
            if (c == '+' || c == '-') {
                doOperation(ms, buffer, c, 1);
            } else if (c == '*' || c == '/') {
                doOperation(ms, buffer, c, 2);
            }
            //2.2：如果是左括号，压栈
            else if (c == '(') {
                ms.push(c);
            }
            //2.3：如果是右括号，弹栈到输出中，直到遇到（未知
            else if (c == ')') {
                doRightBracket(ms, buffer);
            }
            //2.4：如果是操作数，直接加入到输出
            else {
                buffer.append(c);
            }
        }
        //3：把栈中的操作符依次弹出到输出中
        while (!ms.isEmpty()) {
            buffer.append((char) ms.pop());
        }

        return buffer.toString();
    }

    /**
     * 按级别处理操作符
     *
     * @param ms
     * @param buffer
     * @param c
     * @param level
     */
    private void doOperation(MyStack ms, StringBuffer buffer, char c, int level) {
        //1：依次从栈顶获取一个值
        while (!ms.isEmpty()) {
            char topC = (char) ms.pop();
            //2：用这个值跟传入的数据进行比较
            //2.1：如果栈顶的数据是（，不动，就是把它压回来
            if (topC == '(') {
                ms.push(topC);
                break;
            } else {
                //首先获取到栈顶元素所对应的优先级别
                int topLevel = 0;
                if (topC == '+' || topC == '-') {
                    topLevel = 1;
                } else {
                    topLevel = 2;
                }

                if (topLevel >= level) {
                    //2.2：如果栈顶的数据的优先级别大于等于传入的数据级别，那么就要输出
                    buffer.append(topC);
                } else {
                    //2.3：如果栈顶的数据优先级小于传入的数据级别，不动
                    ms.push(topC);
                    break;
                }
            }
        }
        //3：找到位置后，把传入的操作符压入
        ms.push(c);
    }

    /**
     * 处理右括号的情况
     *
     * @param c
     */
    private void doRightBracket(MyStack ms, StringBuffer buffer) {
        //1：从栈中弹出数据，输出到后缀表达式中
        while (!ms.isEmpty()) {
            char topC = (char) ms.pop();
            //2：直到遇到"("为止
            if (topC == '(') {
                break;
            } else {
                buffer.append(topC);
            }
        }
    }

    public static void main(String[] args) {
        PostInfix t = new PostInfix();
        String ret = t.doTransfer("(3+2)/5-((7+8)*4-5)");
        System.out.println("ret===" + ret);
    }
}
