package dataStructures.test9.Tree;

/**
 * 顺序存储二叉树 的  前中后序遍历
 * @author YJ Lan
 * @create 2020-02-19-15:19
 */
public class Tree2ArrBinaryTree {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        System.out.println("前序");
        font(arr, 0);
        System.out.println();
        System.out.println("中序");
        mid(arr, 0);
        System.out.println();
        System.out.println("后续");
        later(arr, 0);


    }

    //前序
    public static void font(int[] arr,int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.print(arr[index]+",");
        if (index*2+1<arr.length) {
            font(arr, index*2+1);
        }
        if (index*2+2<arr.length) {
            font(arr, index*2+2);
        }
    }
    //中序
    public static void mid(int[] arr, int index){
        if (arr == null || arr.length == 0) {
            return;
        }
        if (index*2+1<arr.length) {
            mid(arr, index*2+1);
        }
        System.out.print(arr[index]+",");
        if (index*2+2<arr.length) {
            mid(arr, index*2+2);
        }
    }
    //后序
    public static void later(int[] arr, int index){
        if (arr == null || arr.length == 0) {
            return;
        }
        if (index*2+1<arr.length) {
            later(arr, index*2+1);
        }

        if (index*2+2<arr.length) {
            later(arr, index*2+2);
        }
        System.out.print(arr[index]+",");
    }
}
