package com.xpwi.algorithm.ali;

/**
 * <p>
 * 已知 sqrt (2)约等于1.414，要求不用数学库，求sqrt (2)精确到小数点后 10 位。
 *
 * @author github.com/xpwi
 * @since 2019-08-28
 */
public class Ali02sqrt {

    static final double EPSINON = 0.0000000001;

    public static double sqrt2() {

        double low = 1.4, high = 1.5;
        double mid = (low + high) / 2;

        while (high - low > EPSINON) {
            if (mid * mid > 2) {
                high = mid;
            } else {
                low = mid;
            }
            mid = (high + low) / 2;
        }

        return mid;
    }

    public static void main(String[] args) {
        System.out.println(sqrt2());
    }

}
