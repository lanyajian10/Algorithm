package algorithm.test22.horse;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 骑士周游算法/马踏棋盘算法
 *
 * @author YJ Lan
 * @create 2020-05-10-21:58
 */
public class horseChessBoard {

    private static int X;//几列
    private static int Y;//几行
    private static boolean visited[] ;//是否走过
    private static boolean finished;//是否完成


    public static void main(String[] args) {
        X = 8;
        Y = 8;
        visited = new boolean[X*Y];
        int[][] chessboard = new int[X][Y];
        System.out.println("开始计时。。。。");
        long start = System.currentTimeMillis();
        travelChessboard(chessboard, 7, 7, 1);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("完成耗时："+time);
        for (int i = 0; i < chessboard.length; i++) {
            System.out.println(Arrays.toString(chessboard[i]));
        }

    }

    /**
     *  在棋盘的（colunm,row）坐标放置第step步马
     * @param chessboard  棋盘
     * @param row   行
     * @param colunm 列
     * @param step 要走第几步   从1开始
     */
    public static void travelChessboard(int[][] chessboard, int row, int colunm, int step){
        System.out.println("这是第"+step+"步");
        //在棋盘标记第几步走过的位置
        chessboard[colunm][row] = step;
        //表示该已经访问过
        visited[row*X+colunm] = true;
        ArrayList<Point> next = next(new Point(colunm, row));

        //贪心算法优化（先走回溯步数少的）TODO
        sort(next);

        while (!next.isEmpty()) {
            //取出当前首个可放位置
            Point point = next.remove(0);
            //如果该位置没被放置过
            if (!visited[point.y*X+point.x]) {
                travelChessboard(chessboard, point.y, point.x, step+1);
            }
        }
        //判断是否完成
        //状况1： 未跑完，死路or处于回溯状态
        //状况2： step = X*Y 且 finished=false  棋盘完成
        //状况3： step < X*Y 且 finished=true 棋盘完成，处于回溯状态
        if (step < X*Y && !finished) {
            chessboard[colunm][row] = 0;
            visited[row*X+colunm] = false;
            System.out.println("回溯");
        } else {
            finished = true;
        }

    }


    /**
     * 马儿还能走哪些位置，最多8个
     * @param cur 当前位置
     * @return
     */
    public static ArrayList<Point> next(Point cur){
        ArrayList<Point> list = new ArrayList<>();
        Point point = new Point();
        if ((point.x = cur.x+2)<X && (point.y = cur.y-1)>=0) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x+2)<X && (point.y = cur.y+1)<Y) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x+1)<X && (point.y = cur.y+2)<Y) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x-1)>=0 && (point.y = cur.y+2)<Y) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x-2)>=0 && (point.y = cur.y+1)<Y) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x-2)>=0 && (point.y = cur.y-1)>=0) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x-1)>=0 && (point.y = cur.y-2)>=0) {
            list.add(new Point(point));
        }
        if ((point.x = cur.x+1)<X && (point.y = cur.y-2)>=0) {
            list.add(new Point(point));
        }
        return list;
    }

    /**
     * 排序，根据list元素下一步 可走步数 进行非递减排序
     * 非递减排序：1,2,2,2,3,3,4,4,5
     * 递减排序： 6，5,4,3,2,1
     * @param list
     * @return
     */
    public static void sort(ArrayList<Point> list){
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int n1 = next(o1).size();
                int n2 = next(o2).size();
                if (n1 < n2) {
                    return -1;
                } else if (n1 == n2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
