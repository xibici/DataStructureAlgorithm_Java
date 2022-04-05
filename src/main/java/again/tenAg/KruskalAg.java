package again.tenAg;

import java.util.Arrays;

public class KruskalAg {
    public static void main(String[] args) {
        //doSmall();
        doBig();

    }

    private static void doBig() {
        final int INF=Integer.MAX_VALUE;
        int z=INF;

        char[] vertex={'0','1','2','3','4','5','6','7','8'};
        int[][] matrix={ //找个大点的
                {0,1,5,z,z,z,z,z,z},//0
                {1,0,3,7,5,z,z,z,z},//1
                {5,3,0,z,1,7,z,z,z},//2
                {z,7,z,0,2,z,3,z,z},//3
                {z,5,1,2,0,3,6,9,z},//4
                {z,z,7,z,3,0,z,5,z},
                {z,z,z,3,6,z,0,2,7},
                {z,z,z,z,9,5,2,0,4},
                {z,z,z,z,z,z,7,4,0},//8
        };

        GraphKru graphKru=new GraphKru(matrix,vertex);

        krusalAl(graphKru);
    }

    private static void doSmall() {final int INF=Integer.MAX_VALUE;
        int z=INF;

        char[] vertex=new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix={
                {0,12,z,z,z,16,14},
                {12,0,10,z,z,7,z},
                {z,10,0,3,5,6,z},
                {z,z,3,0,4,z,z},
                {z,z,5,4,0,2,8},
                {16,7,6,z,2,0,9},
                {14,z,z,z,8,9,0},
        };

        GraphKru graphKru=new GraphKru(matrix,vertex);

        krusalAl(graphKru);
    }


    public static void krusalAl(GraphKru graphKru){
        int vlen=graphKru.vlen;
        EData[] resArr=new EData[vlen-1];
        int h1;     //当前边的起点
        int h2;     //当前边的终点
        int m;        //当前边的起点在图中的最终终点
        int n;        //当前边的终点在图中的最终终点
        int index=0;

        for (int i = 0; i < graphKru.edgeNum; i++) {
            EData eOne=graphKru.edges[i];
            h1=eOne.start; //当前边的起点
            h2=eOne.end; //当前边的终点

            m = getEnd(h1, graphKru.ends); //起点 对应ends的终点的下标
            n = getEnd(h2, graphKru.ends); //终点
            if(m!=n){ //如果这条线段的两个终点不相等 让这个线段起点的终点变成终点
                graphKru.ends[m]=n;
                resArr[index]=eOne;
                index++;
            }
        }
        System.out.println(Arrays.toString(resArr));
    }

    /**
     * @param index 需要查询的点的下标
     * @return 查询到的点在edns中下标的结果
     */
    public static int getEnd(int index,int[] ends){
        int tmp=index;
        //为0即这个点在当前图中终点为自身 返回自身
        //再次错误 end必须一直循环找到终点  如果是if只找一次 就会出现问题
        //if(res!=0){
        while(ends[tmp]!=0){
            tmp=ends[tmp];
            //return tmp;

        }
        if(tmp!=0){
            return tmp;
        }else {
            return index;
        }
    }

}


class GraphKru{
    //用来记录各个下标对应的终点
    int[] ends;

    EData[] edges;
    int edgeNum;
    //顶点个数
    int vlen;

    char[] vertex;

    public GraphKru(int[][] matrix,char[] vertex) {
        this.vertex=vertex;
        this.vlen=matrix.length;
        ends=new int[vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                edgeNum++;
            }

        }
        edges=getEdges(matrix);
        sort();

    }




    public EData[] getEdges(int[][] matrix){
        EData[] res=new EData[edgeNum];
        int index=0;
        //矩阵matrix进行遍历 并把所有边加进去  上三角
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                res[index]=new EData(i,j,matrix[i][j],vertex);
                index++;
            }
        }
        return res;
    }

    public void showEnds(){
        System.out.println(Arrays.toString(ends));
    }

    public void showEdges(){
        System.out.println(Arrays.toString(edges));
    }

    public void sort(){
        Arrays.sort(edges);
    }

}

class EData implements Comparable<EData>{

    int start;
    int end;
    int weight;

    char[] vertex;


    public EData(int start, int end, int weight,char[] vertex) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.vertex=vertex;
    }

    @Override
    public String toString() {

        //return start + "->" + end +
        //改成用字母
        return vertex[start] + "->" + vertex[end] +
                "(" + weight+")";
    }

    @Override
    public int compareTo(EData o) {
        return this.weight-o.weight;
    }
}