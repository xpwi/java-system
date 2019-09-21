package com.xpwi.datastructures.superiorsort;

/**
 * 演示基数排序
 *
 * @author xpwi
 */
public class RadixSort {
    /**
     * 实现基数排序
     *
     * @param as      要排序的数组
     * @param maxSite 数组中 数据项的 最大位数
     */
    public void sort(int[] as, int maxSite) {

        //1：记录当前正在处理的位数
        int nowSite = 1;
        //记录10个组的值
        int[][] temp = new int[10][as.length];
        //记录 每次计算位数的时候，需要 /的值
        int n = 1;
        //用来记录 0-9 每个桶内的值的个数
        int[] order = new int[10];

        while (nowSite <= maxSite) {
            //处理某一位数字
            //2：按照该位的数值，把数据分散到temp里面去
            for (int i = 0; i < as.length; i++) {
                int siteNum = (as[i] / n) % 10;
                temp[siteNum][order[siteNum]] = as[i];
                order[siteNum] = order[siteNum] + 1;
            }
            //3：按照0-9的顺序，把temp里面的值，设置回到原始的数组中
            int k = 0;
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        as[k] = temp[i][j];
                        k++;
                    }
                }
                //把桶内个数置为0
                order[i] = 0;
            }
            //计算下一位要/的n值
            n *= 10;
            nowSite++;
        }
    }

    public static void main(String[] args) {
        RadixSort t = new RadixSort();
        int[] as = new int[]{3, 5, 4, 8, 7, 90, 180, 88};
        t.sort(as, 3);

        for (int a : as) {
            System.out.println(a);
        }
    }
}
