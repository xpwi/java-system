package com.xiaopengwei.datastructures.superiorsort;

/**
 * 演示快速排序
 *
 * @author XiaoPengwei.com
 */
public class QuickSort {
    /**
     * 对数组进行快速排序
     *
     * @param as
     * @param left
     * @param right
     */
    public void quickSort(int[] as, int left, int right) {
        //退出条件
        //每次要操作的数据项
        int size = right - left + 1;
        if (size <= 3) {
            //就不能用快速排序，手工排序吧
            this.manualSort(as, left, right);

            return;
        }
        //1：获取合理的分区枢纽值
        int mv = this.medianValue(as, left, right);
        //2：按照分区枢纽值对数组进行 分区
        int centerIndex = this.partition(as, left, right, mv);
        //3：对左边数组重复执行quickSort
        this.quickSort(as, left, centerIndex - 1);
        //4：对右边数组重复执行quickSort
        this.quickSort(as, centerIndex + 1, right);
    }

    /**
     * 就是对数组进行分区，小的放前面，大的放后面，中间是分区枢纽值
     *
     * @param as
     * @param left
     * @param right
     * @param mv
     * @return 分区枢纽值的索引位置
     */
    private int partition(int[] as, int left, int right, int mv) {

        //1：定义两个指示位置的索引
        //记录左边的索引位置
        int leftIndex = left;
        //记录右边的索引位置
        int rightIndex = right - 1;

        //2：进行分区，就是把小的值放前头，大的放后头
        while (true) {
            //左边：从左向右找到一个比 mv 大的值就停
            while (as[++leftIndex] < mv) {
                //什么都不用做
            }
            //右边：从右向左找到一个比 mv小 的值就停
            while (as[--rightIndex] > mv) {
                //什么都不用做
            }
            if (leftIndex >= rightIndex) {
                break;
            } else {
                this.swap(as, leftIndex, rightIndex);
            }
        }
        //3：把中值 放到 分区的位置
        this.swap(as, leftIndex, right - 1);

        return leftIndex;
    }

    /**
     * 计算分区枢纽值
     *
     * @param as
     * @param left
     * @param right
     * @return
     */
    private int medianValue(int[] as, int left, int right) {
        int center = (left + right) / 2;
        if (as[left] > as[center]) {
            this.swap(as, left, center);
        }
        if (as[left] > as[right]) {
            this.swap(as, left, right);
        }
        if (as[center] > as[right]) {
            this.swap(as, center, right);
        }
        this.swap(as, center, right - 1);
        return as[right - 1];
    }

    private void swap(int[] as, int index1, int index2) {
        int temp = as[index1];
        as[index1] = as[index2];
        as[index2] = temp;
    }

    /**
     * 手工排序，只有3个值以内
     *
     * @param as
     * @param left
     * @param right
     */
    private void manualSort(int[] as, int left, int right) {
        int size = right - left + 1;

        if (size == 1) {
            //不用排序
        } else if (size == 2) {
            if (as[left] > as[right]) {
                this.swap(as, left, right);
            }
        } else if (size == 3) {
            if (as[left] > as[right - 1]) {
                this.swap(as, left, right - 1);
            }
            if (as[left] > as[right]) {
                this.swap(as, left, right);
            }
            if (as[right - 1] > as[right]) {
                this.swap(as, right - 1, right);
            }
        }
    }

    public static void main(String[] args) {
        QuickSort t = new QuickSort();

        int[] as = new int[]{3, 5, 4, 8, 7, 90, 80, 88};

        t.quickSort(as, 0, as.length - 1);

        for (int a : as) {
            System.out.println(a);
        }
    }
}
