package com.test6.Sort;

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

//                int[] arr = {1,9,6,3,2,8};

        int[] arr = new int[100000];
        for (int i= 0; i<100000; i++) {
            arr[i] = (int)(Math.random() * 100000);
        }
        long start = System.currentTimeMillis();


//        Sort1bubbling.sort(arr);    //冒泡排序  23s
//        Sort1Choose.sort(arr);    //选择排序  9s
//        Sort1Insert.sort(arr);    //插入排序  2s
        Sort1Insert.mysort(arr);    //插入自写方法 9s


        long end = System.currentTimeMillis();
        long time = end - start  ;
        System.out.println("执行时间："+time);
//        System.out.println(Arrays.toString(arr));
    }
}
