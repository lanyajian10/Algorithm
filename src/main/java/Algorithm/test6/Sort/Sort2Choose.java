package algorithm.test6.Sort;

/**
 * 选择排序 O(n^2)
 *  1.从0开始，找出最小的与arr[0]交换位置
 *  2.从1开始，找出最小的与arr[1]交换位置
 *  直到倒数第二个执行完毕结束为止
 *
 *  10W大小的数组耗时10秒
 * @author YJ Lan
 * @create 2020-02-13-20:53
 */
public class Sort2Choose {


    public static void sort(int[] arr) {
        int minindex = 0;
        int min = 0;
        //只交换一次
        for (int i=0; i<arr.length; i++) {
            minindex = i;
            min = arr[i];
            for(int j=i+1; j<arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minindex = j;
                }
            }

            if (minindex != i) {
                arr[minindex] = arr[i];
                arr[i] = min;
            }
        }
    }
    public static void mysort(int[] arr) {

        //交换好多次
        for (int i=0; i<arr.length-1; i++) {
            for(int j=i+1; j<arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }
}
