package com.xiaopengwei.datastructures.recursive;

/**
 * 演示斐波那契数列
 *
 * @author xpwi
 */
public class Fabonacci {
    /**
     * 求指定项的数据
     *
     * @param num
     * @return
     */
    public int getN(int num) {
        if (num == 1) {
            return 0;
        } else if (num == 2) {
            return 1;
        }

        return getN(num - 1) + getN(num - 2);
    }

    /**
     * 求第一项到指定项的和
     *
     * @param num
     * @return
     */
    public int sum(int num) {
        if (num == 1) {
            return 0;
        }

        return getN(num) + sum(num - 1);
    }

    public static void main(String[] args) {
        Fabonacci t = new Fabonacci();
        int ret = t.getN(7);
        System.out.println("ret=" + ret);

        int sum = t.sum(7);
        System.out.println("sum===" + sum);
    }
}
