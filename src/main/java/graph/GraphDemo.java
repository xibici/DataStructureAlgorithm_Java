package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphDemo {

    public static void main(String[] args) {
        //A- 0 B- 1 C- 2 D- 3 E- 4
        String[] vertexValue={"A","B","C","D","E"};

        Graph graph=new Graph(vertexValue.length);
        for (int i = 0; i < vertexValue.length; i++) {
            graph.insertVertex(vertexValue[i]);
        }

        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,0,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);
        graph.insertEdges(2,0,1);
        graph.insertEdges(2,1,1);
        graph.insertEdges(3,1,1);
        graph.insertEdges(4,1,1);

        //graph.showGraph();
        //graph.bfs();
        graph.dfs();
    }

}


class Graph{

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges;
    private int numOfEdges; //表示边的数组
    private boolean[] isVisited;


    //n个顶点
    public Graph(int n) {
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        isVisited=new boolean[n];
        numOfEdges=0;
    }

    /**
     *
     * @param i 源节点
     * @return 得到第一个邻接节点的下标
     */
    public int getFirstNeighbor(int i){
        for (int j = 0; j < vertexList.size(); j++) {
            if(edges[i][j]>0){
                return j;
            }
        }
        return -1;
    }

    public void dfs(boolean[] isVisited,int i){
        System.out.println(getValueByIndex(i)+"->"); //访问这个节点
        isVisited[i]=true; //并设置为已访问
        int w = getFirstNeighbor(i);
        while (w!=-1){
            if(!isVisited[w]){//如果没有访问过就用这个
                dfs(isVisited,w);
            }
            //访问过了 就跳过 去w的下一个?
            w = getNextNeighbor(i, w);
        }
    }


    public void bfs(boolean[] isVisited,int i) {
        int u;//表示队列头节点对应的下标
        int w;//表示邻接节点

        //初始0进来 访问 并标记
        System.out.print(getValueByIndex(i)+"=>");
        isVisited[i]=true;
        //先进队列   不是多态 直接是LinkedList对象
        LinkedList queque=new LinkedList();
        queque.add(i);

        while (!queque.isEmpty()){  //步骤3

            u = (int)queque.removeFirst();

            w=getFirstNeighbor(u);
            while (w!=-1){  //步骤6
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"=>");
                    //!!忘了设置已访问
                    isVisited[w]=true;
                    //尾部增加
                    queque.addLast(w);

                }
                w=getNextNeighbor(u,w);
            }
        }
    }


    public void bfs() {
        isVisited=new boolean[vertexList.size()];
        for (int j = 0; j < vertexList.size(); j++) {
            if(!isVisited[j]){
                bfs(isVisited,j);
            }
        }
    }

        /** 前一个临接 ← 本节点 →下一个临接
         *  根据前一个邻接节点的下标来获取下一个邻接节点
         * @param v1 是前一个邻接节点的行下标
         * @param v2 是前一个邻接节点的列下标
         * @return
         */
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2+1; j < vertexList.size(); j++) {
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    public void dfs(){
        isVisited=new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }

    }


    //返回list的string
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1 v2对应的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //添加节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }


    public void showGraph(){
        for (int[] link:edges) {
            System.err.println(Arrays.toString(link));
        }

    }

    /**
     *
     * @param v1 表示点的下面第几个顶点   A- 0 B- 1 C- 2 D- 3 E- 4
     * @param v2
     * @param weight
     */
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }

    public int getNumOfVertex(){
        return vertexList.size();
    }

    public int getNumOfEdges(){
        return numOfEdges;
    }

}
