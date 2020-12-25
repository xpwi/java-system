package com.xpwi.algorithm.learn;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 *
 * @author github.com/xpwi
 * @since 2019-09-11
 */
public class MinimumCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        solve(scanner.nextInt());
    }

    public static void solve(int n){
        int[] ways = {0};
        int[] options = {1,2,5,10};

        ArrayList<Integer> al = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        dfs(n, options, 0, ways, al, ret);
        System.out.println(ways[0]);
    }

    public static void dfs(int n, int[] options, int start, int[] ways, ArrayList<Integer> al, ArrayList<ArrayList<Integer>> ret){
        if(n < 0){
            return;
        }
        if(n == 0){
            ways[0]++;
            ret.add(new ArrayList<>(al));
            return;
        }

        for(int i=start; i<options.length; i++){
            al.add(options[i]);			// 选择动作
            dfs(n-options[i], options, i, ways, al, ret);
            al.remove(al.size()-1);		// 撤销动作
        }
    }


}
