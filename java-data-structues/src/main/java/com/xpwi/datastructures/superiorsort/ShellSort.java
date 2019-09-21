package com.xpwi.datastructures.superiorsort;

/**
 * 演示希尔排序
 *
 * @author xpwi
 */
public class ShellSort {

    public void shellSort(int[] as) {

        int increment = 1;
        //1：计算分组的最大间隔
        while (increment <= as.length / 3) {
            increment = increment * 3 + 1;
        }
        //3：然后逐步缩小分组的间隔，重复第2步
        int j = 0;
        int temp = 0;
        for (; increment > 0; increment = (increment - 1) / 3) {
            //2：对每一组进行插入法排序
            for (int i = increment; i < as.length; i++) {
                temp = as[i];
                for (j = i; j >= increment; j -= increment) {
                    if (temp < as[j - increment]) {
                        as[j] = as[j - increment];
                    } else {
                        break;
                    }
                }
                as[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        ShellSort t = new ShellSort();
        int[] as = new int[]{3, 5, 4, 8, 7, 90, 80, 88};
        t.shellSort(as);

        for (int a : as) {
            System.out.println(a);
        }
    }
}
