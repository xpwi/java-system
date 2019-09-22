package com.test;

/**
 * <p>
 *     判断一个数为2的n次方
 *
 * @author xpwi
 * @since 2019-09-10
 */
public class Judge2N {

    public static void judge2N(int x){

        if ((x & (x-1)) == 0){
            System.out.println("y");
        }else {
            System.out.println("n");
        }
    }

    public static void main(String[] args) {

        int a = 10;
        int b = 8;
        int c = 16;

        judge2N(a);
        judge2N(b);
        judge2N(c);
    }


}
