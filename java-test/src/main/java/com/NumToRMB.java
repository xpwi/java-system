package com; /**
 * <p>
 * 人民币拾伍万壹仟壹佰贰拾壹元壹角伍分
 */

import java.util.Scanner;

public class NumToRMB {

    public static StringBuilder getChineseName(String m) {

        String[] mArr = m.split("\\.");
        // 小数点左右两部分
        char[] lArr = mArr[0].toCharArray();


        StringBuilder res = new StringBuilder("人民币");
        String[] numNameArr = new String[]{"壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] lNameArr = new String[]{"元", "拾", "佰", "仟", "万", "亿"};
        String[] rNameArr = new String[]{"角", "分"};

        // 保存单个数字
        Integer v;
        // 左侧临时数组
        String[] tmpArr = new String[lArr.length * 2];
        int tmpIndex = 0;

        // p 左侧从右数位数
        int p = 0;
        for (int i = lArr.length - 1; i >= 0; i--) {
            v = Integer.parseInt(lArr[i] + "");
            if (v == 0 && p != 0) {
                tmpArr[tmpIndex] = "零";
                tmpIndex++;
                p++;
                continue;
            }
            if (p == 0) {
                tmpArr[tmpIndex] = lNameArr[0];
                tmpIndex++;
            }
            if (p == 1) {
                tmpArr[tmpIndex] = lNameArr[1];
                tmpIndex++;
            }
            if (p == 2) {
                tmpArr[tmpIndex] = lNameArr[2];
                tmpIndex++;
            }
            if (p == 3) {
                tmpArr[tmpIndex] = lNameArr[3];
                tmpIndex++;
            }
            if (p == 4) {
                tmpArr[tmpIndex] = lNameArr[4];
                tmpIndex++;
            }
            if (p == 8) {
                tmpArr[tmpIndex] = lNameArr[5];
                tmpIndex++;
            }

            if ((v - 1)>-1) {
                tmpArr[tmpIndex] = numNameArr[v - 1];
                tmpIndex++;
            }

            p++;
        }

        for (int x = tmpArr.length - 1; x >= 0; x--) {
            if (tmpArr[x] != null) {
                res.append(tmpArr[x]);
            }
        }

        if (mArr.length > 1) {
            char[] rArr = mArr[1].toCharArray();

            for (int j = 0; j < rArr.length; j++) {
                v = Integer.parseInt(rArr[j] + "");
                if (v == 0) {
                    res.append("零");
                    continue;
                }
                res.append(numNameArr[v - 1] + rNameArr[j]);
            }
        } else {
            res.append("整");
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String money = in.nextLine();
            StringBuilder chineseName = getChineseName(money);
            System.out.println(chineseName);
        }
    }
}