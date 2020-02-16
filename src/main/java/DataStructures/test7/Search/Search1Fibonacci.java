package DataStructures.test7.Search;

import java.util.Arrays;

/**
 * @author YJ Lan
 * @create 2020-02-16-22:44
 */
public class Search1Fibonacci {

    public static int maxSize = 20;
    //获得一个斐波那契数列
    public static int[] getf(){
        int[] arr = new int[maxSize];

        arr[0] = 1;
        arr[1] = 1;
        for (int i=2; i<maxSize; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr;
    }


    public static int sort(int[] arr, int temp){
        int left = 0;
        int right = arr.length-1;
        int mid = 0;
        int k = 0;
        int [] f = getf();
        //创建一个符合斐波那契的长度
        while (right > f[k]-1) {
            k++;
        }
        //把数组扩充到制定长度，不足都补最后一位数
        int[] copy = Arrays.copyOf(arr, f[k]);
        for (int i=right+1; i<f[k]; i++) {
            copy[i] = arr[right];
        }

        while (left <= right) {
            //获取黄金分割点
            mid = left + f[k-1] -1;
            if (arr[mid] > temp) {
                right = mid - 1;
                // f[k] = f[k-1] - f[l-2]
                // right = mid-1 相当于 取 f[k-1]的一段，
                //那么它的分割点就是f[(k-1)] = f[(k-1)-1] - f[(k-1)-2]
                //那么k就需要-1才能是信长度的黄金分割点坐标
                k --;
            } else if(arr[mid] < temp){
                // f[k] = f[k-1] - f[l-2]
                // left = mid+1 相当于 取 f[k-2]的一段，
                //那么它的分割点就是f[(k-2)] = f[(k-2)-1] - f[(k-2)-2]
                //那么k就需要-2才能是信长度的黄金分割点坐标
                left = mid + 1;
                k-=2;
            } else {

                //当一次找到的时候，可能由于扩充，k>right,
                if (right >= k) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return  -1;
    }
}
