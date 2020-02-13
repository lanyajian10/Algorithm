package com.test5.Recursion;

/**
 * 8皇后
 * 程序：92种解法
 * @author YJ Lan
 * @create 2020-02-12-23:15
 */
public class Queen8 {

    //声明放置皇后个数
    private int maxSize = 8;
    //声明 放置皇后数组  数组下标n, 第n+1个皇后，放置的行列：n+1表示行数，arr[n]+1表示列数
    private int[] arr= new int[maxSize];
    //计数器，多少种解法
    private static int count = 0;
    //计数器2 多少次检查放置
    private static int count2 = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        //从第一行第一列开始：
        queen8.go(0);
        System.out.println("解法的个数为："+count);
        System.out.println("执行检查放置的次数："+count2);
    }

    /*
        放置第n个八皇后
     */
    private  void go(int n){
        if ( n == maxSize ) {
            print();
            return ;
        } else {
            for (int i=0; i<maxSize; i++) {
                arr[n] = i;
                //如果可以放置
                if (canGo(n)){
                    //放置下一个皇后
                    go(n+1);
                }
                //如果冲突，则放置在n行的下一列
            }
            //如果本行放完，那么上一行的位置移动到下一个，再次执行放置冲突检查
        }
    }

    /*
        检测第n个皇后是否可以放置
     */
    private  boolean canGo(int n){
        count2++;
        /*
            轮询 n前面的皇后，查看 是否与 n放置在同一列 或者 是否在同一斜线上
            注：这里 8*8  所以亮点如果是斜线，那么横纵坐标差的绝对值相对
         */
        for (int i=0; i<n; i++) {
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])) return false;
        }
        return true;
    }

    /*
        打印 arr数组，显示其放置方式
     */
    private void print(){
        count++;
        for ( int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
