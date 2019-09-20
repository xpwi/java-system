import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static int getMaxNum(int n, int[] conditionA, int[] conditionB) {
        // 先假设能拉完
        int res = 0;

        HashSet<Integer> car1 = new HashSet<>();
        HashSet<Integer> car2 = new HashSet<>();

        // 先装 1 车，判断是否存在
        for (int i = 0; i < conditionA.length; i++) {
            int a = conditionA[i];
            int b = conditionB[i];

            car1.add(a);

            car2.add(b);



        }

        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            // 有n堆垃圾，m个约束条件
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] conditionA = new int[m];
            int[] conditionB = new int[m];

            for (int i = 0; i < m; i++) {
                conditionA[i] = sc.nextInt();
                conditionA[i] = sc.nextInt();
            }

            getMaxNum(n, conditionA, conditionB);
        }
    }
}