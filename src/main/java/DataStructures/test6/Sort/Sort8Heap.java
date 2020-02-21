package DataStructures.test6.Sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author YJ Lan
 * @create 2020-02-21-10:09
 */
public class Sort8Heap {

    public static void main(String[] args) {

//        int[] arr = {4,8,7,5,6,11,-1,-23,65};
//        sort(arr);

        int[] arr = new int[80000];
        for (int i= 0; i<80000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        long end = System.currentTimeMillis();
        long time = end - start  ;
        System.out.println("执行时间："+time);
    }

    public static void sort(int[] arr){
        //把arr 设置成  大顶堆
        for (int i=arr.length/2-1; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }
//        System.out.println(Arrays.toString(arr));

        //0和arr.length-1交换位置，再置成大顶堆
        int temp = 0;
        for (int i=arr.length-1; i>0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
//        System.out.println(Arrays.toString(arr));

    }

    /**
     *
     * @param arr       目标数组
     * @param i         从第几个节点开始
     * @param length    数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];

        for (int k=i*2+1; k<length; k=k*2+1) {
            if (k+1<length && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}


