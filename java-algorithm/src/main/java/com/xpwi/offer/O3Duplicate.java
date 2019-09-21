package com.xpwi.offer;

/**
 * <p>
 *
 * @author XiaoPengwei
 * @since 2019-08-23
 */
public class O3Duplicate {


    public int getDuplicate(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {

        int[] a = {2, 3, 1, 0, 2, 5};

        O3Duplicate o3Duplicate = new O3Duplicate();

        int duplicate1 = o3Duplicate.getDuplicate(a);

        System.out.println(duplicate1);
    }
}
