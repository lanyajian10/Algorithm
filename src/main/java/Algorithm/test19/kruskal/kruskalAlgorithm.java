package algorithm.test19.kruskal;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法（公交站问题）
 * @author YJ Lan
 * @create 2020-05-10-13:33
 */
public class kruskalAlgorithm {
    public static void main(String[] args) {
        //初始化
        kruskal kruskal = init();

        kruskal.kruskal();
    }

    /**
     * 初始化数据
     * @return
     */
    private static kruskal init() {
        char[] vertxs = {'A','B','C','D','E','F','G'};
        final int INF = Integer.MAX_VALUE;
        int[][] matrix = {
                 /* A    B   C  D   E   F   G */
        /*A*/     {  0, 12,INF,INF,INF, 16, 14},
        /*B*/     { 12,  0, 10,INF,INF,  7,INF},
        /*C*/     {INF, 10,  0,  3,  5,  6,INF},
        /*D*/     {INF,INF,  3,  0,  4,INF,INF},
        /*E*/     {INF,INF,  5,  4,  0,  2,  8},
        /*F*/     { 16,  7,  6,INF,  2,  0,  9},
        /*G*/     { 14,INF,INF,INF,  8,  9,  0}
        };
        return new kruskal(vertxs.length, vertxs, matrix);
    }
}

class kruskal{

    private char[] vertxs;
    private int[][] data;
    private int length;
    private int nodeNum;

    public kruskal(int length,char[] vetrxs, int[][] date){
        this.length = length;
        this.vertxs = vetrxs;
        this.data = date;
        for (int i = 0; i < data.length; i++) {
            for (int j = i+1; j < data.length; j++) {
                if (data[i][j] != Integer.MAX_VALUE) {
                    nodeNum++;
                }
            }
        }
    }

    public void kruskal(){
        Node[] nodes = getNodes();
        System.out.println(Arrays.toString(nodes));
//        1. 根据结点weight从小到大排序
        sortNodes(nodes);
        System.out.println(Arrays.toString(nodes));

        int index =0;
        Node[] rets = new Node[length-1];//保存最后的最小生成树  =  节点数-1
        int[] ends = new int[length];   //记录顶点终点
        for (int i = 0; i < nodes.length; i++) {
            int st = getIndex(nodes[i].start);
            int ed = getIndex(nodes[i].end);
            int m = getEnd(ends, st);//获取st的终点,如果他自己是终点，那么返回自己的坐标
            int n = getEnd(ends, ed);//获取ed的终点
            if (m != n) {
                ends[m] = n;//设置start的终点m的终点为end的终点n
                rets[index++] = nodes[i];
            }
        }

        for (int i = 0; i < rets.length; i++) {
            System.out.println(rets[i]);
        }


    }

    /**
     * 获取元素下标
     * @param target
     * @return
     */
    private int getIndex(char target){
        for (int i = 0; i < vertxs.length; i++) {
            if (vertxs[i] == target) {
                return i;
            }
        }
        return  -1;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于判断 终点是否相同
     * @param ends  动态记录各顶点对应的终点
     * @param i     顶点下标
     * @return
     */
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 根据data[][] 获取Node[]
     * @return
     */
    private Node[] getNodes(){
        Node[] arr  = new Node[nodeNum];
        int index =0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i+1; j < data.length; j++) {
                if (data[i][j] != Integer.MAX_VALUE) {
                    arr[index++] = new Node(vertxs[i],vertxs[j],data[i][j]);
                }
            }
        }
        return arr;
    }

    /**
     * Node[] 根据weight从小到大排序
     * @param nodes
     * @return
     */
    private Node[] sortNodes(Node[] nodes){
        Node temp ;
        for (int i = 0; i < nodeNum-1; i++) {
            for (int j = 0; j < nodeNum-i-1; j++) {
                if (nodes[j].weight > nodes[j+1].weight) {
                   temp = nodes[j];
                   nodes[j] = nodes[j+1];
                   nodes[j+1] = temp;
                }
            }
        }
        return nodes;
    }
}

class Node {
    char start;
    char end;
    int weight;
    public Node(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<"+start+"-"+end+">"+"="+weight;
    }
}
