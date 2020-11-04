package Algorithm.test7.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找法单个  search    适合不均匀
 * 二分查找法所有  searchAll
 * 插值查找所有   searchInsert    适合差值均匀
 * 插值查找所有   searchAllInsert
 *
 * @author YJ Lan
 * @create 2020-02-15-22:59
 */
public class Search1Binary {

    //arr是升序排序  插值查找一个
    public static int searchInsert(int[] arr,int temp,int left, int right){

        if (left>right || temp<arr[0] || temp>arr[arr.length-1]) {
            return  -1;
        }

        int mid = left + (right - left)*(temp-arr[left])/(arr[right] - arr[left]);
        if (arr[mid] > temp) {
//            左递归
            return searchInsert(arr, temp, left, mid-1);
        } else if (arr[mid] < temp) {
//            右递归
            return searchInsert(arr, temp, mid+1, right);
        } else {
            return mid;
        }

    }
    //arr是升序排序  插值查找法所有
    public static List<Integer> searchAllInsert(int[] arr,int temp,int left, int right){

        if (left>right || temp<arr[0] || temp>arr[arr.length-1]) {
            return  new ArrayList<Integer>();
        }

        int mid = left + (right - left)*(temp-arr[left])/(arr[right] - arr[left]);
        if (arr[mid] > temp) {
//            左递归
            return searchAllInsert(arr, temp, left, mid-1);
        } else if (arr[mid] < temp) {
//            右递归
            return searchAllInsert(arr, temp, mid+1, right);
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


    //arr是升序排序  二分查找法所有
    public static List<Integer> searchAll(int[] arr,int temp,int left, int right){

        if (left>right || temp<arr[0] || temp>arr[arr.length-1]) {
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

    //arr是升序排序  二分查找一个
    public static int search(int[] arr,int temp,int left, int right){

        if (left>right || temp<arr[0] || temp>arr[arr.length-1]) {
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

    /**
     * 非递归方式二分查找
     * @param arr
     * @param target
     * @return
     */
    public static int searchNoRecursion(int arr[], int target){
        int left = 0;
        int right = arr.length-1;
        int min = 0;
        while (left<=right) {
            min = (left+right)/2;
            if (arr[min] == target) {
                return min;
            } else if (arr[min] > target) {
                right = min-1;
            } else {
                left = min+1;
            }
        }
        return -1;
    }


}
