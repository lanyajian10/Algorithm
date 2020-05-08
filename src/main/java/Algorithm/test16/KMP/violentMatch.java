package algorithm.test16.KMP;

/**
 * 匹配算法之暴力匹配
 *
 * @author YJ Lan
 * @create 2020-05-07-21:26
 */
public class violentMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int value = violentMatchSearch(str1,str2);
        System.out.println(value);
    }

    private static int violentMatchSearch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int l1 = s1.length;
        int l2 = s2.length;

        int i=0;
        int j=0;
        while (i<l1 && j<l2) {

            if (s1[i] == s2[j]) {
//                如果相等，则同时后移再判断
                i++;
                j++;
            } else {
//                如果s1[i]!=s2[j]，那么i要回到相等位置的后一个，也就是i-j+1的位置，j置成0重新匹配
                i = i- (j-1);
                j = 0;
            }
        }

        if (j == l2) {
            return i = i-j;
        }
        return -1;
    }
}
