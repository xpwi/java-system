package com.xpwi.algorithm.a01sort;

/**
 * <p>
 * 堆排序
 *
 * @author xpwi
 * @since 2019-09-20
 */
public class HeapSort {
    public static void heapAdjust(int[] array, int parent, int length) {
        // temp保存当前父节点
        int temp = array[parent];
        // 先获得左孩子
        int child = 2 * parent + 1;

        while (child < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= array[child]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            array[parent] = array[child];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        array[parent] = temp;
    }

    public static void heapSort(int[] list) {
        // 循环建立初始堆
        for (int i = list.length / 2; i >= 0; i--) {
            heapAdjust(list, i, list.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            heapAdjust(list, 0, i);
//            System.out.format("第 %d 趟: \t", list.length - i);
//            for (int m = 0; m < list.length - 1; m++) {
//                System.out.println(list[m]);
//            }

        }
    }

    public static void main(String[] args) {
        // 10
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        heapSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
