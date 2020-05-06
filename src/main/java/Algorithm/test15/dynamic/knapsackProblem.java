package algorithm.test15.dynamic;

import java.util.Arrays;

/**
 * 动态规划算法之----背包问题 （4kg）
 * 1.吉他  1kg  1500
 * 2.音响  4kg  3000
 * 3.电脑  3kg  2000
 * 01背包： 不能重复，价值最大值的装法
 * 全背包： 可重复，价值最大的装法
 *   这里是01背包
 *   i: 第几个物品， j 背包承重  w[i] 第i个物品的重量 h[i]:第i个物品的价值   v[i][j]： i种物品，j承重的背包的最大价值
 *   1. v[i][0] = v[0][j] = 0
 *   2. 如果w[i] > j   v[i][j] = v[i-1][j]   => 如果第i个物品背包装不下，那么选取i-1（除第i个物品外）种物品的最优解
 *   3. 如果w[i] <=j  v[i][j] = max{v[i-1][j],h[i] + v[i-1][j-w[i]]}   n = j-w[i] 装了第i个物品的背包剩余承重n
 *      如果第i个物品背包能装下，那么取： 除第i个物品的最优解   和  第i个物品重量+装下第i个物品后，新背包承重n下剩余全部物品的最优解
 * @author YJ Lan
 * @create 2020-05-06-20:56
 */
public class knapsackProblem {


    public static void main(String[] args) {
        int w[] = {3,4,1};
        int h[] = {2000,3000,1500};
        int wight = 4;
        int num = w.length;
        int v[][] = new int[num+1][wight+1];
//        记录放置情况
        int path[][] = new int[num+1][wight+1];
//        v[i][0] = v[0][j] = 0 由于数组初始化为0的特性，这里就不做处理
//        所以横纵坐标直接从1开始
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
//                由于从1开始，但是我们第i个物品的重量需要做 w[i-1] 处理
//                w[i] > j   v[i][j] = v[i-1][j]
                if (w[i-1] > j) {
                    v[i][j] = v[i-1][j];
                } else {
//                    如果w[i] <=j  v[i][j] = max{v[i-1][j],h[i] + v[i-1][j-w[i]]}
//                    v[i][j] = Math.max(v[i-1][j], h[i-1] + v[i-1][j-w[i-1]]);
                    if (v[i-1][j] < h[i-1] + v[i-1][j-w[i-1]]) {
                        v[i][j] = h[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }

        int i = v.length-1;
        int j = v[0].length-1;
        while (i>0 && j>0) {
            if (path[i][j]==1) {
                System.out.printf("第%d个物品放入到背包\n",i);
                j -= w[i-1];//减去第i个物品重量后的背包重量,由于第一个物品索引0，故减1
            }
            i--;//不能重复放
        }
    }
}
