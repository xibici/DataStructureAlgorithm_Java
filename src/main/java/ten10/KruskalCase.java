package ten10;

import java.util.Arrays;

public class KruskalCase {

    public static void main(String[] args) {

        final int INF=Integer.MAX_VALUE;
        int z=INF;

        char[] vertexs=new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix={
                {0,12,z,z,z,16,14},
                {12,0,10,z,z,7,z},
                {z,10,0,3,5,6,z},
                {z,z,3,0,4,z,z},
                {z,z,5,4,0,2,8},
                {16,7,6,z,2,0,9},
                {14,z,z,z,8,9,0},
        };

        Kruskal kruskal=new Kruskal(vertexs,matrix);
        kruskal.print();
        //初始化一下

        kruskal.kruskalAlg();
    }



}

class Kruskal{
    private static final int INF=Integer.MAX_VALUE;

    private char[] vertexs;    //顶点数
    private int[][] matrix;    //临接矩阵

    private int edgeNum;    //边个数
    EData[] edges; //所有的边

    public Kruskal( char[] vertexs, int[][] matrix) {
        //初始化顶点数和边个数
        int vlen=vertexs.length;

        //初始顶点
        //this.vertexs=vertexs; 这样是共用同一个地址 会导致大家到时候一起修改
        //隔离开 拷贝
        this.vertexs=new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i]=vertexs[i];
        }

        //初始化边 拷贝
        this.matrix=new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j]=matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }


    }

    public void kruskalAlg(){
        int index=0;
        int[] ends=new int[edgeNum];
        EData[] edges = getEdges();
        sortEdges(edges);
        //?
        EData[] resEdges=new EData[edgeNum];

        //已经排序过了 权值从小到大排序
        for (int i = 0; i < edgeNum; i++) {
            //单看本身自己一条边的时候 起点顶点 的下标 EF时为E p1为4 p2为5
            int p1=getPosition(edges[i].start);
            //单看本身自己一条边的时候 终点顶点
            int p2=getPosition(edges[i].end);
            //p1->p2    起点  p1  ------  p2  终点

            //查看p1 p2在构成地图的时候的终点
            int m=getEnd(ends,p1);  //p1的终点为m   在ends数组中
            int n=getEnd(ends,p2); //p2的终点为n
            //一开始ends都是0 所以 m为p1本身4  n为p2本身5

            //如果最终不指向同一个终点则加入
            if(m!=n){  //重点!!   前后顶点一直寻找的终点看看是不是同一个 不然就不加入
                //p1的终点改成p2   原本p1的终点为m
                //ends[4] 表示E的终点 改为F ->5
                ends[m]=n;
                resEdges[index++]= this.edges[i];
            }
        }

        //结束后 遍历一下
        System.out.println(Arrays.toString(resEdges));
    }

    public int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    public EData[] getEdges(){
        edges=new EData[edgeNum];
        int index=0;         //matrix
        for (int i = 0; i < vertexs.length; i++) {
            //防止重复 对称矩阵对面
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=INF){
                    //edges[index++]
                    edges[index]=new EData(vertexs[i], vertexs[j], matrix[i][j]);
                    index++;
                }
            }
        }
        return edges;
    }




    //显示矩阵
    public void print(){

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //不过20位的时候就占位
                System.out.printf("%10d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //从小到大
    public void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length-i-1; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    // i j 别搞错了
                    EData tmp=edges[j+1];
                    edges[j+1]=edges[j];
                    edges[j]=tmp;
                }
            }
        }
    }

    //从ends数组里获取 某个char顶点对应终点的下标
    public int getEnd(int[] ends,int i){
        //错误写法
        /*if(ends[i]!=0){ //若ends里面有这个数对应的终点的的值 则返回
            return ends[i];
        }else{//否则为0 (初始化的时候为全0) 返回它本身
            return i;
        }*/
        //正确写法
        while (ends[i]!=0){  //一直循环 在一个终点后继续寻找这个终点有没有终点
            i=ends[i];
            //de时候   看E 下标为4的为5 指向5 F  然后看看F的终点是多少 结果是0
            //I=ends[4]=5;
            //然后 ends[5]为0 直接返回了i=5,


        }
        return i;

    }

}

//两点一线 边/线段/路的data
class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }


    @Override
    public String toString() {
        /*return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';*/
        return "["+this.start+","+this.end+","+this.weight+"]";
    }
}







