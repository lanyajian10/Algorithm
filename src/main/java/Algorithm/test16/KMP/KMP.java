package algorithm.test16.KMP;

import java.util.Arrays;

/**
 * KMP算法
 * @author YJ Lan
 * @create 2020-05-07-21:44
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));

        int value = KMPSearch(str1, str2, next);
        System.out.println(value);
    }

    public static int KMPSearch(String str1, String str2, int[] next){

        for (int i = 0, j=0; i < str1.length(); i++) {
            while (j>0 && str1.charAt(i) != str2.charAt(j)) {
                // ABCDAB ,匹配到最后一个' '，直接匹配到 ABCDABD的C
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i-j+1;
            }
        }
        return -1;

    }

    /**
     * 获取子串的部分匹配值
     * 举例说明：
     * A    的前缀后缀都是 ''                   A部分匹配值[0]
     * AB   的前缀 A    后缀 B    前后缀交集0个  AB部分匹配值[0,0]
     * ABC  的前缀 A AB 后缀 C BC 前后缀交集0个  ABC部分匹配值[0,0,0]
     * ABCA 的前缀 A AB ABC 后缀 A CA BCA 前后交集A 长度1  ABCA部分匹配[0,0,0,1]
     * ABCAB的前缀 A AB ABC ABCA 后缀 B AB CAB BCAB 共有元素AB 长度2 ABCAB部分匹配[0,0,0,1,2]
     * ABCABD的前缀 A AB ABC ABCA ABCAB 后缀 D BD ABD CABD BCABD 共有元素0  ABCABD部分匹配[0,0,0,1,2,0]
     */
    public static int[] kmpNext(String str){
        int[] arr = new int[str.length()];
        arr[0] = 0; //如果字符串长度为1， 则部分匹配值为{0}
        for (int i = 1, j=0; i < str.length(); i++) {
            while (j>0 && str.charAt(i) != str.charAt(j)) {
                j = arr[j-1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            arr[i] = j;
        }
        return arr;
    }
}
