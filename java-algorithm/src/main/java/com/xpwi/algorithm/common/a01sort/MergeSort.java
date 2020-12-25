package com.xpwi.algorithm.common.a01sort;

import java.util.Arrays;

/**
 * <p>
 * 合并排序
 *
 * @author github.com/xpwi
 * @since 2019-08-14
 */
public class MergeSort {

    private static void mergeSort(int[] original) {
        if (original == null) {
            return;
        }

        int length = original.length;
        if (length > 1) {
            int middle = length / 2;
            // 拆分问题规模
            int partitionA[] = Arrays.copyOfRange(original, 0, middle);
            int partitionB[] = Arrays.copyOfRange(original, middle, length);
            // 递归调用
            mergeSort(partitionA);
            mergeSort(partitionB);
            sort(partitionA, partitionB, original);
        }
    }

    private static void sort(int[] partitionA, int[] partitionB, int[] original) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < partitionA.length && j < partitionB.length) {
            if (partitionA[i] <= partitionB[j]) {
                original[k] = partitionA[i];
                i++;
            } else {
                original[k] = partitionB[j];
                j++;
            }
            k++;
        }

        if (i == partitionA.length) {
            while (k < original.length) {
                original[k] = partitionB[j];
                k++;
                j++;
            }
        }
        if (j == partitionB.length) {
            while (k < original.length) {
                original[k] = partitionA[i];
                k++;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 1, 3, 6, 4};
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
