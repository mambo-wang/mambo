package com.wb.wbao.suanfa;

public class ZeroOnePackage {

    public static void main(String[] args) {
        int v[] = {60,100,120};//每个物品的价值，每个物品只有一样
        int w[] = {10,20,30};//每个物品的重量
        int n = 3;//物品数量
        int totW = 50;//背包承重

        int dp[] = new int[totW + 1];//背包在每一个重量下的最大价值

        //第i个物品，j:背包能放的重量
        for(int i = 0; i < n; i ++){

            for(int j = totW; j >= w[i]; j --){
                if(dp[j - w[i]] + v[i] > dp[j]){
                    dp[j] = dp[j - w[i]] + v[i];
                }
            }
        }
        System.out.println(dp[totW]);
    }
}

