package com.xpwi.datastructures.recursive;

/**
 * 演示用递归来解决汉诺塔问题
 *
 * @author github.com/xpwi
 */
public class Towers {
    /**
     * 用递归来解决汉诺塔问题
     *
     * @param topN 就是汉诺塔的块数
     * @param src  就是要移动的汉诺塔的块所在的塔
     * @param temp 用来辅助移动
     * @param dest 就是汉诺塔的块要移入的塔
     */
    public void transfer(int topN, String src, String temp, String dest) {
        if (topN == 1) {
            System.out.println(topN + " 从 " + src + " 移动到 " + dest);
        } else {
            //1：把topN-1当作一个整体，移动topN-1到temp
            transfer(topN - 1, src, dest, temp);
            //2：把topN移动到dest
            System.out.println(topN + " 从 " + src + " 移动到 " + dest);
            //3：把topN-1移动到dest
            transfer(topN - 1, temp, src, dest);
        }
    }

    public static void main(String[] args) {
        Towers t = new Towers();
        t.transfer(4, "A", "B", "C");
    }
}
