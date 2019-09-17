package com.base;

import java.util.Scanner;

public class T1GetSum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            System.out.println(a+b);
        }

    }
}
