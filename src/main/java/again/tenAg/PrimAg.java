package again.tenAg;

import java.util.Arrays;

public class PrimAg {

    public static void main(String[] args) {
        doSmall();

    }

    private static void doSmall() {
        char[] vertex=new char[]{'A','B','C','D','E','F','G'};

        int z=65535;

        int[][] matrix=new int[][]{
                {z,5,7,z,z,z,2},
                {5,z,z,9,z,z,3},
                {7,z,z,z,8,z,2},
                {z,9,z,z,z,4,z},
                {z,z,8,z,z,5,4},
                {z,z,z,4,5,z,6},
                {2,3,z,z,4,6,z},
        };
        GraphPrimAg graphPrimAg=new GraphPrimAg(matrix);
        //graphPrimAg.showGraph();
        primAg(0,graphPrimAg,vertex);
    }

    /** 普利姆算法
     * @param index 从哪个节点开始
     */
    public static void primAg(int index,GraphPrimAg graphPrimAg,char[] vertex){
        int[][] matrix = graphPrimAg.matrix;
        boolean[] isVisited = graphPrimAg.isVisited;
        isVisited[index]=true;

        int vlen=matrix.length;
        int edgeNum=vlen-1;


        int start=0;
        int end=0;
        int min;
        //记录min真正index
        //int minIndex;
/*A->G :2
G->B :3
G->E :4
E->F :5
F->D :4
A->C :7
*/
        /*上三角
        * A->G :2
A->B :5
A->C :7
C->E :8
E->F :5
B->D :9
        * */
        //每条边
        for (int z = 0; z < edgeNum; z++) {
            //必须重置 不然ag之后 min等于2 min不重置有问题
            start=0;
            //minIndex=0;
            end=0;
            min=65535;
            for (int i = 0; i <matrix.length ; i++) {
                //就不能再在区域里选最短的
                for (int j = i+1; j < matrix[i].length; j++) {
                    //i已访问而且j未访问 上三角
                    int len=matrix[i][j];
                    if(isVisited[i]==true&& isVisited[j]==false && len <min){
                        start=i;
                        min=len;
                        end=j;
                    }
                }
                //每轮i结束后就是选的每一轮起点结束 决定了x->y的路径

            }
            isVisited[end]=true;
            System.out.println(vertex[start]+"->"+vertex[end]+" :"+min);

        }
        

    }
}


class GraphPrimAg{

    boolean[] isVisited;
    int[][] matrix;

    public GraphPrimAg(int[][] matrix) {
        this.isVisited = new boolean[matrix.length];
        this.matrix = matrix;
    }

    public void showGraph(){
        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));
        }

    }

}