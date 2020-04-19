package DataStructures.test6.Sort;

import java.util.Arrays;

/**
 * 堆排序
 * 》》大顶堆：父节点都大于等于其子节点
 * 》》大顶堆-->用来升序排序
 * 》》小顶堆-->用来降序排序
 * 1.将数组看成顺序存储的二叉树，找到除了排序后的二叉树的最后一个非叶子节点
 * 2.将其置成大顶堆/或者小顶堆
 * 3.将根节点放在数组末尾，也就是最后一个叶子节点
 * 4.将数组长度减一
 * 5.重复执行1-4，直到数组长度为0为止
 *
 * @author YJ Lan
 * @create 2020-02-21-10:09
 */
public class Sort8Heap {

    public static void main(String[] args) {

//        int[] arr = {4,8,7,5,6,11,-1,-23,65};
        int[] arr = {4,6,8,5,9};
        sort(arr);

//        int[] arr = new int[80000];
//        for (int i= 0; i<80000; i++) {
//            arr[i] = (int)(Math.random() * 80000);
//        }
//        long start = System.currentTimeMillis();
//        sort(arr);
//        long end = System.currentTimeMillis();
//        long time = end - start  ;
//        System.out.println("执行时间："+time);
    }

    public static void sort(int[] arr){
        //把arr 设置成  大顶堆
        // arr.lenth/2-1代表顺序存储二叉树的最后一个非叶子结点的下标
        //从最后一个非叶子结点找其子节点或子节点下的最大值与其交换位置，然后开始倒数第二个非叶子结点，直到根节点
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
        //k=i*2+1 找到当前节点的左子节点
        //找到i的子节点中最大值，与i值交换位置
        for (int k=i*2+1; k<length; k=k*2+1) {
            //k+1<length            代表该节点的父节点有 右子节点
            //arr[k] < arr[k+1]     该k节点的父节点 的 左子节点值 小于 右子节点的值
            // k++;                 那么就将目标节点置成父节点的右子节点
            if (k+1<length && arr[k] < arr[k+1]){
                k++;
            }
            // 如果 i节点的左右子节点的最大值 大于 i节点的值，
            // 那么将i位置值换成 最大值，将最大值的下标赋予i，temp记录的就是新i的值，
            // 如果新i还有子节点，那么下次循环将拿新i的子节点最大值与 新i值比较和交换


            //如果 i节点的左右子节点的最大值 小于 i节点的值，退出
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


