import java.util.Scanner;

/**
 * <p>
 * 跑步的速度为13m/s
 * 法师有闪跳技能，可在1s内移动50m,消耗10点魔法值。
 * 魔法值的恢复速度为4点/s,只有在原地休息状态时才能够恢复。
 * <p>
 * 36 255 10
 * 样例输出
 * Yes
 * 10
 *
 * @author xpwi
 * @since 2019-09-22
 */
public class Main {

    public static void getRes(int m, int s, int t) {
        int resT = 0;
        int resS = 0;

        // 先用完法术
        while (m >= 10 && s >= resS) {
            m -= 10;
            resT++;
            resS += 50;
        }

        if (resS != s) {
            while (s - resS >= 100) {
                resS += 100;
                resT += 7;
            }

            System.out.println("YES");
            System.out.println(resT);

        }

        System.out.println("YES");
        System.out.println(resT);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int M = sc.nextInt();
            int S = sc.nextInt();
            int T = sc.nextInt();
            getRes(M, S, T);
        }
    }


}
