package com.xiaopengwei.datastructures.recursive;

/**
 * 演示使用递归来实现归并排序
 *
 * @author xpwi
 */
public class MergeSort {
    /**
     * 使用递归来实现归并排序
     *
     * @param theArray 要排序的数组
     */
    public void mergeSort(int[] theArray) {
        int[] temp = new int[theArray.length];
        this.doMergeSort(theArray, temp, 0, theArray.length - 1);
    }

    /**
     * 真正执行归并排序的方法
     *
     * @param theArray   要排序的数组
     * @param temp       临时的归并用的数组
     * @param lowerBound 左边的索引边界，就是从那个索引开始取数据
     * @param highBound  右边的索引边界，就是取数据的最大索引
     */
    private void doMergeSort(int[] theArray, int[] temp, int lowerBound, int highBound) {
        if (lowerBound >= highBound) {
            return;
        }
        //1：计算分界的索引
        int mid = (lowerBound + highBound) / 2;
        //2：对左边进行排序
        doMergeSort(theArray, temp, lowerBound, mid);
        //3：对右边进行排序
        doMergeSort(theArray, temp, mid + 1, highBound);
        //4：把结果合并起来
        this.merge(theArray, temp, lowerBound, mid + 1, highBound);
    }

    /**
     * 做合并操作
     *
     * @param theArray  要排序的数组
     * @param temp      临时的归并用的数组
     * @param lowIndex  左边的索引边界，就是从那个索引开始取数据
     * @param highIndex 右边开始的索引位置
     * @param highBound 右边的索引边界，就是取数据的最大索引
     */
    private void merge(int[] theArray, int[] temp, int lowIndex, int highIndex, int highBound) {
        //最后合并到temp的索引记录
        int count = 0;
        //记录左边的最小索引边界
        int lowerBound = lowIndex;
        //记录左边的最大索引边界
        int mid = highIndex - 1;

        //1：从左边依次取值跟右边的数据依次比较
        while (lowIndex <= mid && highIndex <= highBound) {
            //1.1如果左边的值比右边小，那就把左边这个值加入temp
            if (theArray[lowIndex] < theArray[highIndex]) {
                temp[count++] = theArray[lowIndex++];
            } else {
                //1.2如果左边的值比右边大，把右边这个值加入temp，然后继续向下比较
                temp[count++] = theArray[highIndex++];
            }
        }
        //2：分别处理两边还剩下的数据
        //2.1： 处理左边还剩下的数据
        while (lowIndex <= mid) {
            temp[count++] = theArray[lowIndex++];
        }
        //2.2：处理右边还剩下的数据
        while (highIndex <= highBound) {
            temp[count++] = theArray[highIndex++];
        }


        //3：把排好顺序的数据，重新拷贝回theArray
        for (int i = 0; i < (highBound - lowerBound + 1); i++) {
            theArray[lowerBound + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        MergeSort t = new MergeSort();

        int[] as = new int[]{4, 8, 5, 9, 7};

        t.mergeSort(as);

        for (int a : as) {
            System.out.println(a);
        }
    }
}
