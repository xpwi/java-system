package com.xpwi.algorithm.common.a03brackets;

import java.util.Stack;

/**
 * <p>
 * 检查表达式中括号是否匹配
 *
 * @author github.com/xpwi
 * @since 2019-05-15
 */
public class ValidCheck {

    public static boolean isMatch(String s) {
        Stack<Character> sc = new Stack<Character>();

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {

            if (c[i] == '(' || c[i] == '[' || c[i] == '{') {
                sc.push(c[i]);
            } else if (c[i] == ')') {
                if (sc.isEmpty()) {
                    return false;
                } else {
                    if (sc.peek() == '(') {
                        sc.pop();
                    }
                }
            } else if (c[i] == ']') {
                if (sc.isEmpty()) {
                    return false;
                } else {
                    if (sc.peek() == '[') {
                        sc.pop();
                    }
                }
            } else if (c[i] == '}') {
                if (sc.isEmpty()) {
                    return false;
                } else {
                    if (sc.peek() == '{') {
                        sc.pop();
                    }
                }
            }
        }
        if (sc.empty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String str = "(((999+666)*5/7)/2*((996+997)*30/7)/2)";
        System.out.println(isMatch(str));

    }
}
