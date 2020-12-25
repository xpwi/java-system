package com.xpwi.algorithm.guazi;
import java.util.Scanner;

/**
 * <p>
 * https://www.nowcoder.com/profile/3909462/test/27548379/587676
 * 降雨某地n块区域的海拔高度。
 *
 * @author github.com/xpwi
 * @since 2019-09-15
 */
public class Q5WaterStored {

    /**
     * @author github.com/xpwiShuaiyu Yao
     * @create 2019-09-13 21:57
     **/
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int ns[] = new int[n];

        int top = 0;
        int firstTopIndex = 0;
        int lastTopIndex = 0;
        for (int i = 0; i < n; i++) {
            int next = scanner.nextInt();
            ns[i] = next;

            if (next > top) {
                top = next;
                firstTopIndex = i;
                lastTopIndex = i;
            } else if (next == top) {
                lastTopIndex = i;
            }
        }

        int result = n * top;

        //left
        int current;
        int height = 0;
        for (int i = 0; i < firstTopIndex; i++) {
            current = ns[i];
            height = height > current ? height : current;
            result -= (top - height) + current;
        }

        //middle
        for (int i = firstTopIndex; i <= lastTopIndex; i++) {
            result -= ns[i];
        }

        //right
        height = 0;
        for (int i = n - 1; i > lastTopIndex; i--) {
            current = ns[i];
            height = height > current ? height : current;
            result -= (top - height) + current;
        }

        System.out.println(result);
    }
}
