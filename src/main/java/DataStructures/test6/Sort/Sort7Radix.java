package DataStructures.test6.Sort;

/**
 * 基数排序算法
 *
 * @author YJ Lan
 * @create 2020-02-15-22:27
 */
public class Sort7Radix {

    public static void sort(int[] arr) {

        //二维数组
        int[][] burket = new int[10][arr.length];
        //存放 二维数组的  每个数组中 元素个数
        int[] burketElementCount = new int[10];

        int maxIndex = arr[0];
        for (int i=0; i<arr.length; i++) {
            if (arr[i] > maxIndex) {
                maxIndex = arr[i];
            }
        }

        for (int p=0, n = 1;  p<(maxIndex+"").length(); p++, n*=10){

            //根据 尾数放入 二维数组中
            for (int i=0; i<arr.length; i++) {
                //取出尾数
                int index =  arr[i] / n % 10;
                //放入二维数组
                burket[index][burketElementCount[index]] = arr[i];
                //对应元素个数+1
                burketElementCount[index] ++;
            }

            int temp = 0;
            //取出  二维数组的元素，放入arr中
            for (int i=0; i<burket.length; i++) {
                if (burketElementCount[i] > 0 ) {
                    for (int j=0; j<burketElementCount[i]; j++) {
                        arr[temp++] = burket[i][j];
                    }
                }
                //计数桶归0，方便下次使用
                burketElementCount[i] = 0;
            }
        }
    }
}
