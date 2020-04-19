package DataStructures.test5.Recursion;

import java.util.Arrays;

/**
 * 递归的运用之----迷宫
 * @author YJ Lan
 * @create 2020-02-12-22:44
 */
public class MiGong {

    public static void main(String[] args) {

        int[][] migong = new int[8][7];

        for (int i=0; i<7; i++) {
            migong[0][i] = 1;
            migong[7][i] = 1;
        }
        for (int i=0; i<8; i++) {
            migong[i][0] = 1;
            migong[i][6] = 1;
        }
        migong[3][1] = 1;
        migong[3][2] = 1;
        canGo(migong, 1, 1);
        printt(migong);
    }

    /**
     *  1 表示墙   2 表示路径 3表示不可走
     * @param arr  地图
     * @param i 探测的横坐标
     * @param j 探测的纵坐标
     * @return
     */
    private static boolean canGo(int[][] arr, int i, int j) {
        if (arr[6][5] == 2){
            //找到终点
            return true;
        } else {
            if ( arr[i][j] == 0) {
                //没走过,假设可以走
                arr[i][j] = 2;
                //行走逻辑：下->右->上->左
                if (canGo(arr, i+1, j)) {
                    return true;
                } else if(canGo(arr, i, j+1)) {
                    return true;
                } else if (canGo(arr, i-1, j))  {
                    return true;
                } else if (canGo(arr, i, j-1)) {
                    return true;
                } else {
                    //上下左右都不能走，则改点为死路，返回false
                    arr[i][j] = 3;
                    return false;
                }
            } else {
                // 1  3   改点为死路或者墙，不能走，返回false
                return false;
            }
        }
    }

    //打印二维数组
    private static void printt(int[][] arry) {
        for (int[] ints : arry) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
