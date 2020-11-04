package Algorithm.test21.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 * 把所有顶点当做出发点
 * @author YJ Lan
 * @create 2020-05-09-22:53
 */
public class floydAlgorithm {


    public static void main(String[] args) {

        char[] vertex = {'A','B','C','D','E','F','G'};
        int length = vertex.length;
        int[][] matrix = new int[length][length];
        final int N = 65535;
        matrix[0] = new int[] {0,5,7,N,N,N,2};
        matrix[1] = new int[] {5,0,N,9,N,N,3};
        matrix[2] = new int[] {7,N,0,N,8,N,N};
        matrix[3] = new int[] {N,9,N,0,N,4,N};
        matrix[4] = new int[] {N,N,8,N,0,5,4};
        matrix[5] = new int[] {N,N,N,4,5,0,6};
        matrix[6] = new int[] {2,3,N,N,4,6,0};

        Graph graph = new Graph(length, vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    char[] vertxs;//顶点
    int[][] dis;//距离
    int[][] pre;//前驱

    /**
     * 初始化
     * @param length
     * @param vertxs
     * @param matrix
     */
    public Graph(int length, char[] vertxs, int[][] matrix){
        this.vertxs = vertxs;
        this.dis = matrix;
        pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    /**
     * 弗洛伊德三层for循环
     * 第一层k中间结点
     * 第二层i开始结点
     * 第三层j末尾结点
     */
    public void floyd() {
        int len = 0;
        for (int k = 0; k < vertxs.length; k++) {
            for (int i = 0; i < vertxs.length; i++) {
                for (int j = 0; j < vertxs.length; j++) {
                    /*
                    路径长度为
                    顶点vertxs[i]到中间结点vertxs[k]的路径   +
                    中间结点vertxs[j]到末尾结点vertxs[j]的路径
                     */
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;//更新i-j的路径
                        pre[i][j] = pre[k][j];  //i-j的前驱结点=k-j的的前驱结点
                    }
                }
            }
        }
    }


    public void show(){
        for (int i = 0; i < vertxs.length; i++) {

            //遍历 前驱
            for (int j = 0; j < vertxs.length; j++) {
                System.out.print(vertxs[pre[i][j]]+" ");
            }

            System.out.print("   ");

            //遍历 距离
            for (int j = 0; j < vertxs.length; j++) {
                System.out.print(vertxs[i]+"->"+vertxs[j]+"的距离:"+dis[i][j]+" \t");
            }

            System.out.println();
        }
    }

}
