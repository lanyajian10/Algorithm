package algorithm.test18.prim;

import java.util.Arrays;

/**
 * 普利姆算法
 * data  顶点
 * verxs 定点数
 * weight[i][j]= ?   data[i] 到 data[j] 的距离，10000表示不连通
 * 顶点间连线，使得任意两点都可直接或者间接连通，需要修的最短距离
 * @author YJ Lan
 * @create 2020-05-09-20:50
 */
public class primAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        int[][] weight = new int[][]
                {
                    {10000,5,7,10000,10000,10000,2},
                    {5,10000,10000,9,10000,10000,3},
                    {7,10000,10000,10000,8,10000,10000},
                    {10000,9,10000,10000,10000,4,10000},
                    {10000,10000,8,10000,10000,5,4},
                    {10000,10000,10000,4,5,10000,6},
                    {2,3,10000,10000,4,6,10000}
                };
        Graph graph = new Graph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, data, verxs, weight);
        minTree.show(graph);
        minTree.prim(graph);
    }
}

/**
 * 最小树
 */
class MinTree {

    /**
     * 初始化图
     * @param graph
     * @param data
     * @param verxs
     * @param weight
     */
    public void createGraph(Graph graph, char[] data, int verxs, int[][] weight){
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void prim(Graph graph){
        int[] visited = new int[graph.verxs];
        visited[0] = 1;    //从A开始
        //最短距离
        int maxSize;
        //记录最短距离的坐标
        int h1,h2;
        h1 = h2 = -1;
        //顶点间连线，使得任意两点都可直接或者间接连通  最少需要定点数-1条线
        for (int k = 1; k < graph.verxs; k++) {
            maxSize = 10000;  //还原maxSize

            for (int i = 0; i < graph.verxs; i++) {
                //找到已经访问过的顶点，从他们开始连线可达的未访问的顶点
                if (visited[i] == 1) {
                    for (int j = 0; j < graph.verxs; j++) {
                        //找出data[i]顶点可达的未访问的顶点中，距离最近的连线
                        if ( visited[j] !=1 && graph.weight[i][j] < maxSize){
                            maxSize = graph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
            }
            visited[h2] = 1;//记录最短距离的访问  这里i是已经访问过的
            System.out.println("边<"+graph.data[h1]+"-"+graph.data[h2]+">的距离最短为:"+maxSize);
        }
    }

    public void show(Graph graph){
        for (int i = 0; i < graph.weight.length; i++) {
            System.out.println(Arrays.toString(graph.weight[i]));
        }
    }
}

/**
 *  图
 */
class Graph{
    int verxs;//表示顶点个数
    char[] data;//顶点集合
    int[][] weight;//权重
    public Graph(int verxs){
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}