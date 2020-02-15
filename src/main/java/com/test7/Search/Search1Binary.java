package com.test7.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找法
 * @author YJ Lan
 * @create 2020-02-15-22:59
 */
public class Search1Binary {


    //arr是升序排序
    public static List<Integer> searchAll(int[] arr,int temp,int left, int right){

        if (left>right) {
            return  new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        if (arr[mid] > temp) {
//            左递归
            return searchAll(arr, temp, left, mid-1);
        } else if (arr[mid] < temp) {
//            右递归
            return searchAll(arr, temp, mid+1, right);
        } else {
            List<Integer> list = new ArrayList<Integer>();
            list.add(mid);

            int k = mid -1;
            while (true) {
                //因为是排序好的，所以相同的数都集中在一块，所以如果不同，则结束循环
                if (k<0 || arr[k] != temp) {
                    break;
                }
                list.add(k);
                k --;
            }

            k = mid +1;
            while (true) {
                if (k>arr.length-1 || arr[k] != temp) {
                    break;
                }
                list.add(k);
                k ++;
            }

            return list;
        }

    }

    //arr是升序排序
    public static int search(int[] arr,int temp,int left, int right){

        if (left>right) {
            return  -1;
        }

        int mid = (left + right) / 2;
        if (arr[mid] > temp) {
//            左递归
            return search(arr, temp, left, mid-1);
        } else if (arr[mid] < temp) {
//            右递归
            return search(arr, temp, mid+1, right);
        } else {
            return mid;
        }

    }


}
