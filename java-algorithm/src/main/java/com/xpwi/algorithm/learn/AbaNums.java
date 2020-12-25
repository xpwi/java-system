package com.xpwi.algorithm.learn;

import java.util.Scanner;

/**
 * <p>
 * 任何一个数除以10的余数就是该数最后一位
 * 任何一个数除以10的商就是排除掉最后一位后的数
 * 所以 ，一个数1234 就可以通过这种方法得到 123 和 4
 * 接下来对 123 进行同样的操作，就得到 12 和 3
 * 接下来得到 1 和 2
 * 接下来得到 0 和 1
 * 整个过程是个循环，当商不是 0 的时候就一直这么干
 * 每次拿到一个余数，都用来构造新数，新数=新数*10+余数
 * 所以经过四次循环后，我们得到新数 4321 ，如果是回文，那么新数应该等于原数，否则，说明不是回文。
 *
 * @author github.com/xpwi
 * @since 2019-09-13
 */
public class AbaNums {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个整数:");
        int hw = sc.nextInt();
        int y = 0;
        int h = hw;
        while (hw > 0) {
            y = y * 10 + hw % 10;
            hw = hw / 10;
        }
        if (y == h) {
            System.out.println("是回文数");
        } else {
            System.out.println("不是回文数");
        }
    }
}


