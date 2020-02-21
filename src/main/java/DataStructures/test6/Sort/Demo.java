package DataStructures.test6.Sort;

import java.util.Arrays;

/**
 * @author YJ Lan
 * @create 2020-02-13-16:45
 */
/*
    排序算法：
        一：内部排序：在内存中排序
            1.插入排序
                1.1.直接插入排序 ·
                1.2.希尔排序
            2.选择排序
                2.1.简单选择排序 ·
                2.2.堆排序
            3.交换排序
                3.1.冒泡排序    ·
                3.2.快速排序
            4.归并排序
            5.基数排序

        二：外部排序：量大，内存装不下，分批排序。 内存外存结合
 */
/*
    度量一个排序的时间复杂度
    1.事后运行统计
    2.事前估算统计
 */
/*
    时间频度 T(n)
    一个算法花费的时间与他执行的次数成正比，一个算法中的语句执行次数成为 语句频度 或 时间频度

 */
public class Demo {
    public static void main(String[] args) {

        int[]  arr = {8,9,1,7,2,3,5,4,6,0};   //希尔
//        int[] arr = {-9,78,0,23,-567,70};    //快排


//        int[] arr = new int[100000];
//        for (int i= 0; i<100000; i++) {
//            arr[i] = (int)(Math.random() * 100000) +1;
//        }
        long start = System.currentTimeMillis();
//        Sort1bubbling.sort(arr);      //冒泡排序          23s
//        Sort2Choose.sort(arr);        //选择排序          9s
//        Sort3Insert.mysort(arr);        //插入自写方法       9s
        Sort3Insert.sort(arr);        //插入排序          2s
//        Sort4Shell.changeSort(arr);   //希尔排序--交换法   16s
//        Sort4Shell.sort(arr);         //希尔排序--移动法   31ms
//        Sort5Quick.sort(arr, 0, arr.length-1);    //快速排序  31ms
//        Sort6Merget.sort(arr, 0, arr.length-1,new int[arr.length]); //归并排序 30ms
//        Sort7Radix.sort(arr); //  基数排序  19ms
        long end = System.currentTimeMillis();
        long time = end - start  ;
        System.out.println("执行时间："+time);
        System.out.println(Arrays.toString(arr));
    }
}
