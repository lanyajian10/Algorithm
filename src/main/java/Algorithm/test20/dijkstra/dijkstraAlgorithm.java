package Algorithm.test20.dijkstra;


import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 * 每个点到其他结点的最短路径（广度优先）
 * @author YJ Lan
 * @create 2020-05-10-16:53
 */
public class dijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int length = vertex.length;
        int[][] matrix = new int[length][length];
        final int N = 65535;
        matrix[0] = new int[] {N,5,7,N,N,N,2};
        matrix[1] = new int[] {5,N,N,9,N,N,3};
        matrix[2] = new int[] {7,N,N,N,8,N,N};
        matrix[3] = new int[] {N,9,N,N,N,4,N};
        matrix[4] = new int[] {N,N,8,N,N,5,4};
        matrix[5] = new int[] {N,N,N,4,5,N,6};
        matrix[6] = new int[] {2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.toshow();
        graph.djs(6);
    }
}

class Graph{
    char[] vertxs;
    int[][] matrix;
    visitVertxs vv;

    public Graph(char[] vertxs, int[][] matrix){
        this.vertxs = vertxs;
        this.matrix = matrix;
    }

    public void toshow(){
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    /**
     * 出发顶点对应坐标
     * @param index
     */
    public void djs(int index){
        vv = new visitVertxs(vertxs, index);
        updateDjs(index);
//        更新除了出发顶点外，到其他顶点的距离 和 前驱
        for (int i = 1; i < vertxs.length; i++) {
            index = vv.updateArr();
            updateDjs(index);
        }


        for (int i = 0; i < vv.dis.length; i++) {
            System.out.print(vv.dis[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < vv.dis.length; i++) {
            if (vv.getDis(i) != 65535) {
                System.out.print(vertxs[i]+"("+vv.getDis(i)+")");
            } else {
                System.out.print("N");
            }
        }
    }

    /**
     * 更新index 到 周围顶点的距离和前驱顶点
     * @param index
     */
    private void updateDjs(int index){
        int len ;
        for (int i = 0; i < matrix[index].length; i++) {
            //  vv.getDis(index)  初始化visitVertxs的出发坐标 到 index 的距离
            //  index 到  j 的距离
            // len   出发顶点到 j 的距离
            len = vv.getDis(index) + matrix[index][i];
            //如果 i 未被访问  且  出发顶点到i的距离 大于  出发顶点->index->j的距离
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updateDis(i, len);
                vv.updatePre(i, index);
            }
        }
    }


}

/**
 * 已访问顶点集合
 */
class visitVertxs{
    int[] visited ;//下标index已访问，则visited[index] = 1;
    int[] pre;// 本类初始化index到达vertxs[i]的前驱结点为pre[i]
    int[] dis;// 本类初始化index到达vertxs[i]的距离

    /**
     * 构造函数
     * @param vertxs
     * @param index
     */
    public visitVertxs(char[] vertxs, int index){
        visited = new int[vertxs.length];
        visited[index] = 1;
        pre = new int[vertxs.length];
        dis = new int[vertxs.length];
        Arrays.fill(dis, 65535);
        dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return visited[index] == 1;
    }

    /**
     * 更新到达index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    /**
     * 更新到达p顶点的前驱结点为index
     * @param p
     * @param index
     */
    public void updatePre(int p, int index){
        pre[p] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * updateDjs(index)后，找出新的结点作为访问结点
     * @return
     */
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < visited.length; i++) {
            //找出可达的 没有被访问的顶点 中最小的，准备访问它
            if (visited[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //标记访问
        visited[index] = 1;
        return index;
    }



}
