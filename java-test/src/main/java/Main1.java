import java.util.Scanner;

/**
 * <p>
 *
 * @author xpwi
 * @since 2019-09-19
 */
public class Main1 {

    public static int getMinNum(int[] arr, int m) {
        int minSum = Integer.MAX_VALUE;

        if (m<0){
            m=0;
        }

        for (int i = 0; i <= arr.length; i++) {

            for (int k = i + m - 1; k < arr.length; k++) {
                int thisSum = 0;

                for (int j = i; j <= k; j++) {
                    thisSum += arr[j];
                }

                if (thisSum < minSum) {
                    minSum = thisSum;
                }
            }


        }

        return minSum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            // 有n大小，m长度
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(getMinNum(arr, m));
        }


    }
}
