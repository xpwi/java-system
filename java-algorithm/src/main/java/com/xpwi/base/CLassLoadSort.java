package com.xpwi.base;

/**
 * <p>
 * 1.父类 static 变量和 static 代码块（同类内它俩按顺序）
 * 2.子类的 static 变量和 static 代码块（同类内它俩按顺序）
 * 3.父类的成员变量，非静态代码块（同类内它俩按顺序）
 * 4.父类的构造函数
 * 3.子类的成员变量，非静态代码块（同类内它俩按顺序）
 * 4.子类的构造函数
 *
 * @author xpwi
 * @since 2019-08-17
 */
public class CLassLoadSort {
    public static void main(String[] args) {

        System.out.println("beg");

        B b = new B();
    }
}

class A {
    public static String s2 = prtString("A的static变量...");

    static {
        System.out.println("A的static代码块...");
    }

    protected A() {
        System.out.println("A的构造函数...");
    }

    public String s1 = prtString("A的成员变量...");

    {
        System.out.println("A的代码块");
    }

    public static String prtString(String str) {
        System.out.println(str);
        return null;
    }
}

class B extends A {
    public String ss1 = prtString("B的成员变量...");

    public static String ss2 = prtString("B的static变量...");

    public B() {
        System.out.println("B的构造函数...");
    }

    static {
        System.out.println("B的static代码块...");
    }

    {
        System.out.println("B的代码块...");
    }
}
