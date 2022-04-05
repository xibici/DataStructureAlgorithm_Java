package again.dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class dfsbfsAg {

    public static void main(String[] args) {
        String[] vertexs={"A","B","C","D","E"};
        int vlen=vertexs.length;

        Graph graph=new Graph(vlen);
        for (int i = 0; i < vertexs.length; i++) {
            graph.insertVertex(vertexs[i]);
        }

        graph.insertEdget(0,1,1);
        graph.insertEdget(1,0,1);
        graph.insertEdget(0,2,1);
        graph.insertEdget(1,2,1);
        graph.insertEdget(1,3,1);
        graph.insertEdget(1,4,1);
        graph.insertEdget(2,0,1);
        graph.insertEdget(2,1,1);
        graph.insertEdget(3,1,1);
        graph.insertEdget(4,1,1);

        graph.showGraph();

        //设从A开始
        //graph.dfs();

        graph.bfs();

    }



}


class Graph {

    ArrayList<String> vertexList;
    int[][] edges;
    int numOfEdges;

    boolean[] isVisited;

    LinkedList queque;

    /**
     * @param n 为边数
     */
    public Graph(int n) {
        this.vertexList = new ArrayList<>();
        edges = new int[n][n];
        isVisited = new boolean[n];
        queque=new LinkedList();
    }

    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    public void insertEdget(int i, int j, int weight) {
        this.edges[i][j] = weight;
    }

    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //region  dfs内容
    public void dfs(){
        /*int index=0;
        dfs(index);*/
        //需要重置防止bfs
        Arrays.fill(isVisited,false);

        //代替上面的写法
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                dfs(i);
            }
        }
    }


    public void dfs(int index) {
        isVisited[index] = true;
        System.out.print(vertexList.get(index)+"->");
        //找到下一个邻接节点w的下标
        int w = getNextW(index);

        while (w!=-1){//不存在

            if(!isVisited[w]){
                dfs(w);
            }

            //w = getNextNextW(index);
            //w = getNextW(index);
            w = getNextNextW(index,w);

        }



    }


    //
    public int getNextW(int index) {
        //找index行的 也就是index下标的vertex点的下一个邻接节点
        for (int j = 0; j < edges[index].length; j++) {
            //之后再判断是否访问
            if (edges[index][j]==1) {
                //找到连通而且该邻接节点没有被访问过的时候
                return j;
            }
        }

        return -1;
    }

    //

    /**  跳过当前j点的index的邻接节点
     *
     * @param index
     * @param j 需要被跳过的点
     * @return
     */
    public int getNextNextW(int index,int j) {
        //找index行的 也就是index下标的vertex点的下一个邻接节点
        for (int k = j+1; k < edges[index].length; k++) {
            //之后再判断是否访问
            if (edges[index][k]==1) {
                //找到连通而且该邻接节点没有被访问过的时候
                return k;
            }
        }

        return -1;
    }

    //endregion

    //region bfs内容

    public void bfs(){
        //需要重置 防止dfs
        Arrays.fill(isVisited,false);
        bfsAgW(0);
    }

    public void bfs(int index) {

        System.out.print(vertexList.get(index)+"->");
        isVisited[index]=true;
        queque.addLast(index);

        while (!queque.isEmpty()){
            int u =(int) queque.removeFirst();
            int w = getNextW(u);
            while (w!=-1){
                if(!isVisited[w]){
                    isVisited[w]=true;
                    System.out.print(vertexList.get(w)+"->");
                    queque.addLast(w);
                }
                w=getNextNextW(u,w);

            }


        }


    }


    public void bfsAgW(int index){

        //该点设置已访问
        isVisited[index]=true;

        queque.addLast(index);

        while (!queque.isEmpty()){
            //从u找邻接节点  以u开始广度遍历 增加整层的邻接节点
            int u =(int) queque.removeFirst();
            //而且还要先输出她
            System.out.print(vertexList.get(u)+"->");

            int w = getNextW(u);
            //看看能不能找到存在的w邻接节点 不存在就下一次
            while (w!=-1){
                //再看看有没有访问过 访问过证明加入过queue了就不管了
                if(!isVisited[w]){
                    isVisited[w]=true;
                    queque.addLast(w);

                }
                //如果访问过这个w  就以u为起点寻找w后面的邻接节点 并覆盖为u
                w=getNextNextW(u,w);
            }
            //找到邻接节点了


        }



    }
        //endregion

}