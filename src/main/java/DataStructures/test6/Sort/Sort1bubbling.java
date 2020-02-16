package DataStructures.test6.Sort;

/**
 * 冒泡排序
 * 复杂度：O（n^2）
 *  10W大小的数组耗费27秒
 * @author YJ Lan
 * @create 2020-02-13-20:22
 */
public class Sort1bubbling {

    //简单冒泡
    public static int[] easySort(int[] arr){
        int temp = 0;
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    //升序  交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    //优化冒泡
    public static int[] sort(int[] arr){
        boolean flag = false;
        for (int i=0; i<arr.length-1; i++) {
            flag = false;
            for (int j=0; j<arr.length-1-i; j++) {

                if (arr[j] > arr[j+1]) {
                    //升序  交换
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                break;
            }
        }
        return arr;
    }

}
