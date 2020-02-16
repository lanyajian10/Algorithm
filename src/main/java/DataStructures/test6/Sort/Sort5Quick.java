package DataStructures.test6.Sort;

/**
 * 快速排序
 * @author YJ Lan
 * @create 2020-02-14-20:51
 */
public class Sort5Quick {

    public static void sort(int[] arr, int left, int right){
        int l = left;
        int r = right;

        int curr = arr[(left + right)/2];
        int temp = 0;
        while (r > l) {

            while (arr[l] < curr) {
                l++;
            }
            while (arr[r] > curr) {
                r--;
            }
            if (l>=r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换后l位置值  与  中值相等，  那么跳过这个值
            if (arr[l] == curr) {
                r--;
            }
            if (arr[r] == curr) {
                l++;
            }
        }


        //必须错开相等，否则不会执行while操作l,r，会无限递归
        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            sort(arr, left, r);
        }
        if (right > l) {
            sort(arr, l, right);
        }
    }
}
