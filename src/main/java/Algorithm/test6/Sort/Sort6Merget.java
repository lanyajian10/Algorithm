package algorithm.test6.Sort;

/**
 * 归并排序
 * @author YJ Lan
 * @create 2020-02-14-21:52
 */
public class Sort6Merget {


    //分+合
    public static void sort(int[] arr, int left, int right, int[] temp){
        if (left<right) {
            int min = (left + right) /2;
            //左边分
            sort(arr, left, min, temp);
            //右边分
            sort(arr, min+1, right, temp);
            //排序合并
            merge(arr, left, min, right, temp);
        }
    }


    //合并
    public static void merge(int[] arr, int left, int min, int right, int[] temp){

        int i = left;
        int j = min+1;
        int t = 0;

        //0-3 4-7进行比较放入 temp中，直到一个片段放完位置
        while ( i <= min && j <= right) {

            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //将剩下的那个指针未到末尾的数组  剩余元素依次放入 temp中
        while (i<=min) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j<=right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        //把temp指针归0
        //把temp的元素拷贝到arr的从left到right,
        int leftnow = left;
        t = 0;
        while (leftnow <= right) {
            arr[leftnow] = temp[t];
            t++;
            leftnow++;
        }
    }
}
