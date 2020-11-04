package Algorithm.test7.Search;


/**
 * 查找算法
 * 1.顺序(线性)查找
 * 2.二分查找/折半查找
 * 3.插值查找
 * 4.斐波那契查找
 *
 * @author YJ Lan
 * @create 2020-02-15-22:17
 */
public class Demo {

    public static void main(String[] args) {
        int[]  arr = {0,1,2,3,4,5,5,7,8,9};

//        int[] arr = new int[100000];
//        for (int i= 0; i<100000; i++) {
//            arr[i] = (int)(Math.random() * 100000) ;
//        }
//        Sort7Radix.sort(arr); //  基数排序  19ms

        long start = System.currentTimeMillis();


//        int t = Search1Binary.search(arr, 0, 0, arr.length);  //二分查找一个
//        List<Integer> integers = Search1Binary.searchAll(arr, 5, 0, arr.length); //二分查找所有
        int t = Search1Binary.searchNoRecursion(arr, 10);
//        int t = Search1Fibonacci.sort(arr, 3);

        long end = System.currentTimeMillis();
        long time = end - start  ;
        System.out.println("执行时间："+time);
        System.out.println("查找结果："+t);
//        System.out.println("查找结果："+integers.toString());
    }
}
