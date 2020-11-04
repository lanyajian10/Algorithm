package Algorithm.test14.divideAndConquer;

/**
 * 分治算法（问题拆解）-----汉诺塔算法
 * 希尔排序、快排、二分查找、大整数乘法、棋盘覆盖、线性时间选择、最接近点对问题、循环赛日程表、
 * @author YJ Lan
 * @create 2020-05-05-19:37
 */
public class hanoiTower {

    public static void main(String[] args) {
        hanoiTowerAlgorithm(3, 'A', 'B', 'C');

    }

    /**
     * 汉诺塔算法
     * A柱有num个圆盘，从上往下依次由小到大，
     * 需求：现在需要把圆盘按照同样规律放到C柱上，
     * 需求：一次只能挪动一个，只能大的在下，小的在上
     * @param num  一共有多少个圆盘
     * @param a 物品现在位置
     * @param b 空闲位置
     * @param c 物品目标位置
     */
    public static void hanoiTowerAlgorithm(int num, char a, char b, char c){
        if (num == 1) {
            System.out.println("把圆盘从： "+ a +"->" + c);
        } else {
            //拆分成两部分，最底部一层  其他部分。
            //把其他部分 放到b
            hanoiTowerAlgorithm(num-1, a, c, b);

            System.out.println("把圆盘从：" + a + "->"+c);

            //把其他部分 放到c（最底部一层）上
            hanoiTowerAlgorithm(num-1, b, a, c);
        }
    }

}
