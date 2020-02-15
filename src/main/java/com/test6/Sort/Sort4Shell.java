package com.test6.Sort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author YJ Lan
 * @create 2020-02-14-19:07
 */
public class Sort4Shell {

    //希尔算法---交换式
    public static void changeSort(int[] arr){

        for ( int gap = arr.length/2; gap>0; gap/=2) {
            int temp = 0;
            for (int j = gap; j<arr.length; j++) {

                for (int i = j-gap; i>=0; i-=gap){

                    if (arr[i] > arr[i+gap]){
                        temp = arr[i+gap];
                        arr[i+gap] = arr[i];
                        arr[i] = temp;
                    }

                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }

    //希尔排序----移动法
    public static void sort(int[] arr){

        for (int gap = arr.length/2; gap>0; gap/=2) {

            for (int j=gap; j<arr.length; j++) {
                int temp = arr[j];
                int index = j;
                while (index-gap>=0 && temp < arr[index-gap]) {
                    arr[index] = arr[index-gap];
                    index-=gap;
                }
                arr[index] = temp;
            }

//            System.out.println(Arrays.toString(arr));
        }
    }


}
