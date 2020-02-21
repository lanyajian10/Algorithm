package DataStructures.test6.Sort;

/**
 * 插入排序 O(n^2)
 *
 * 把集合看成两个集合，
 * 一个只有第一个元素的有序集合，
 * 一个（除收个元素的）有n-1个元素的无序集合，
 * 每次从无序集合拿出一个元素放入有序集合中去，也就是拿着这个与有序数组的末尾开始向前比较
 *
 * 10W 大概 2秒
 * @author YJ Lan
 * @create 2020-02-13-22:04
 */
public class Sort3Insert {

    //自己写的，大概需要10秒
    public static void mysort(int[] arr) {

        for (int i=1; i<arr.length; i++) {
            int temp = 0;
            for (int j = i; j>0; j--) {
                if (arr[j-1] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    public static void sort(int[] arr) {
        int value = 0;
        int index = 0;
        for (int i = 1; i<arr.length; i++) {
            value = arr[i];
            index = i-1;

            while (index>=0 && arr[index] > value) {
                arr[index + 1] = arr[index];
                index --;
            }
            arr[index + 1] = value;
        }

    }
}
