package ten10;

import java.util.Arrays;

public class Prim {

    public static void main(String[] args) {

        //顶点数据
        char[] data=new char[]{'A','B','C','D','E','F','G'};

        //有多少条边
        int verxs=data.length;

        int z=10000;

        int[][] weight=new int[][]{
                {z,5,7,z,z,z,2},
                {5,z,z,9,z,z,3},
                {7,z,z,z,8,z,2},
                {z,9,z,z,z,4,z},
                {z,z,8,z,z,5,4},
                {z,z,z,4,5,z,6},
                {2,3,z,z,4,6,z},
        };

        MGraph mGraph=new MGraph(verxs);
        /*mGraph.data=data;
        mGraph.weight=weight;*/

        MinTree minTree=new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.showGraph(mGraph);
        System.out.println("------------------------");
        //从A点开始
        minTree.prim(mGraph,0);

    }

}

//最小生成树 ->
class MinTree{

    public void createGraph(MGraph graph,int verxs,char data[],int[][] weight){
        int i;
        int j;
        for ( i = 0; i < verxs; i++) {
            graph.data[i]=data[i];
            for ( j = 0; j <verxs ; j++) {
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    /**
     *
     * @param mGraph  用哪个图进行prim算法
     * @param v       从哪个点开始
     */
    public void prim(MGraph mGraph,int v){

        int verxs= mGraph.verxs;
        char[] data= mGraph.data;
        int[][] weight= mGraph.weight;

        int[] isVisited=new int[data.length];
        isVisited[v]=1;

        //表示两点之间的通路 最小花销值
        int min=10000;

        //用来记录最小花销的开始节点
        int h1=0;
        //用来记录最小花销的终点
        int h2=0;

        //进行对每个边查找
        for (int k = 0; k < verxs-1; k++) {
            //以第一个循环为已经访问的  因为只是比较已经访问过的i 所以所有没有访问过比如只有A访问过
            // 从A开始 到所有j结束之后到B的时候 因为B和之后的点都没有访问过之后直接跳过
            for (int i = 0; i < verxs; i++) {
                //以第二个循环为未坊问的 是当前节点需要找的最优路径的终点节点
                for (int j = 0; j < verxs; j++) {

                    if(isVisited[i]==1 && isVisited[j]==0 && weight[i][j]<min ){
                        min=weight[i][j];
                        h1=i;
                        h2=j;

                    }

                }

            }

            //每条边都找到了 加入进去
            isVisited[h2]=1;
            System.out.println(data[h1]+"点的最小花销路径是"+data[h2]+",花销为:"+weight[h1][h2]);
            //每找一次边都要重置为最大值用于比较
            min=10000;
        }



    }

    public void showGraph(MGraph graph){
        System.out.print("   ");
        for (int i = 0; i < graph.data.length; i++) {
            System.out.print("  "+graph.data[i]+"  "+" ");
        }
        System.out.println();

        for (int i = 0; i < graph.weight.length; i++) {
            System.out.print(graph.data[i]+"  ");
            for (int j = 0; j < graph.weight[i].length; j++) {
                if(graph.weight[i][j]<10000){
                    System.out.print("  "+graph.weight[i][j]+"   ");
                }else{
                    System.out.print(graph.weight[i][j]+" ");
                }

            }
            System.out.println();
        }
    }
}

class MGraph{
    int verxs;//节点个数
    char[] data; //节点数据
    int[][] weight;//存放边数据

    public MGraph(int verxs) {
        this.verxs = verxs;
        data=new char[verxs];
        weight=new int[verxs][verxs];

    }
}






