package DataStructures.test13.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author YJ Lan
 * @create 2020-04-06-17:13
 */
public class GraphDemo {

    private ArrayList<String> vertexList;  //存储定点的集合

    private int[][] edges; //存储图对应的邻阵矩阵

    private int numberOfEdges; //存储边的数目

    private boolean[] isVisited;//记录顶点是否访问过

    public GraphDemo(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[n];
    }

    public static void main(String[] args) {
        GraphDemo graphDemo = new GraphDemo(5);
        String[] str = {"A","B","C","D","E"};
        for (int i = 0; i < str.length; i++) {
            graphDemo.vertexList.add(str[i]);
        }

        graphDemo.insertEdge(0, 1, 1);
        graphDemo.insertEdge(0, 2, 1);
        graphDemo.insertEdge(1, 0, 1);
        graphDemo.insertEdge(1, 2, 1);
        graphDemo.insertEdge(1, 3, 1);
        graphDemo.insertEdge(1, 4, 1);

        graphDemo.showGraph();

        graphDemo.dfs();

//        graphDemo.bfs();
    }

    /**
     * 广度优先遍历算法
     * 1. 访问初始结点v并标记结点v为已访问
     * 2. 结点v入队
     * 3. 当队列为非空时，继续执行，否则算法结束
     * 4. 出队列，获取头结点u
     * 5. 查找结点u的第一个邻接结点w
     * 6. 若w不存在，跳到步骤3，否则执行以下二个步骤
     *  6.1 若结点w尚未被访问，则访问结点w并标记为已访问，结点w入队
     *  6.2 查找结点u的继w邻接点后的下一个邻接结点w，到步骤6
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i) {
        int w = 0;
        int u = 0;
        System.out.print(vertexList.get(i) + "=>");
        isVisited[i] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);
        while (queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(vertexList.get(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }


    public void bfs(){
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }



    /**
     * 深度优先遍历算法
     * 1. 访问初始结点v，并标记结点v为已访问
     * 2. 查找结点v的第一个邻接结点w
     * 3. 若w存在，则继续执行4，如果不存在，则返回到第一步，将从v的下一个结点继续。
     * 4. 若w未被访问，对w进行深度优先递归遍历，（即把w当做另一个方法的v，从1重新开始）
     * 5. 查找结点v的w临结点的下一个邻接结点赋值给w，转到第3步。
     */
    private void dfs(boolean[] isVisited, int i){

        System.out.print(vertexList.get(i) + "=>");
        isVisited[i] = true;

        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs(){
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 查找 下标为index的结点 的第一个邻接结点下标
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return  -1;
    }

    /**
     * 根据下标为index的结点的  前一个邻接结点的下标   来获取下一个邻接结点
     * @param v1 结点下标
     * @param  v2 结点的前一个邻接结点下标
     * @return
     */
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //返回图中顶点（结点）个数
    public int getNumberOfVertex(){
        return vertexList.size();
    }

    //得到边的数目
    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    //返回结点i（下标）对应的数据
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    //返回V1,V2的权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    //插入节点
    private void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1    第1个顶点对应在vertexList的下标
     * @param v2    第2个顶点对应在vertexList的下标
     * @param weight 权重
     */
    private void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }

    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}


