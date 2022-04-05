package again.tenAg;

import java.util.Arrays;
import java.util.LinkedList;

public class Dj {
    public static void main(String[] args) {

        //doSmall();
        doBig();
    }

    private static void doBig() {
        char[] vertex={'0','1','2','3','4','5','6','7','8'};
        int z=65535;
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

        int vlen=vertex.length;
        int start=0;
        GraphDj graphDj=new GraphDj(matrix,start,vlen);

        dsj(graphDj,start);

        //start只能是从同一个 不能查的时候修改其他值到另一个点的值 因为只能这个算法只是计算了start为起点的最短路径
        graphDj.getRoad(start,7,vertex);
    }

    private static void doSmall() {
        char[] vertex={'A','B','C','D','E','F','G'};
        int z=65535;
        int[][] matrix={
                {0,5,7,z,z,z,2},
                {5,0,z,9,z,z,3},
                {7,z,0,z,8,z,z},
                {z,9,z,0,z,4,z},
                {z,z,8,z,0,5,4},
                {z,z,z,4,5,0,6},
                {2,3,z,z,4,6,0},
        };
        int vlen=vertex.length;
        int start=6;
        GraphDj graphDj=new GraphDj(matrix,start,vlen);

        dsj(graphDj,start);

        graphDj.getRoad(start,3,vertex);
    }


    public static void dsj(GraphDj graphDj,int index){

        graphDj.generate(index);
        graphDj.showDisGraph();
        graphDj.showAlready();
        graphDj.showPreVisitedGraph();

        System.out.println("----------------------------------");

        int[] dis=graphDj.dis;
        //获得一个最小路径的 而且未被访问节点的下标

        for (int i = 0; i < dis.length; i++) {
            int minIndex = graphDj.updateArr();
            if(minIndex!=-1){
                graphDj.update(minIndex);
            }
        }

        graphDj.showDisGraph();
        graphDj.showAlready();
        graphDj.showPreVisitedGraph();


    }


}

class GraphDj{

    int z=65535;
    int[] dis;
    int[] pre_visited;
    int[] already;
    int[][] matrix;

    public GraphDj(int[][] matrix,int index,int vlen) {//从哪个点开始的遍历 构建
        this.matrix=matrix;
        dis=new int[vlen];
        Arrays.fill(dis,z);
        dis[index]=0;

        already=new int[vlen];
        //初始构建的已访问设为1
        already[index]=1;

        pre_visited=new int[vlen];
    }

    public void generate(int index){
        Arrays.fill(pre_visited,index);
        pre_visited[index]=0;

        for (int j = 0; j < matrix.length; j++) {
            //这里是有问题的 111111111 TODO
            if(matrix[index][j]<dis[j]){
                //dis[j]=matrix[index][j]+dis[index] ;
                dis[j]=matrix[index][j];
            }
        }
    }

    public void update(int index){
        already[index]=1;
        int len=0;
        for (int j = 0; j < matrix[index].length; j++) {
            //这里是有问题的 111111111 TODO
            //if(matrix[index][j]<65535){
            len=matrix[index][j]+dis[index];
            if(len < dis[j]){
                //dis[j]=matrix[index][j]+dis[index] ;
                dis[j]=len;
                pre_visited[j]=index;
            }
        }
    }

    /**
     *
     * @return 返回最小路径的下标
     */
    public int updateArr(){
        //min应该是65535 第一次用于替换
        int min=65535;
        int index=-1;
        //得到最小值但是你需要知道 最小值的下标
        for (int i = 0; i < dis.length; i++) {
            if(dis[i]<min && already[i]!=1){
                min=dis[i];
                index=i;
            }
        }

        return index;
    }

    public void showDisGraph(){
        System.out.println("dis:"+Arrays.toString(dis));
    }

    public void showPreVisitedGraph(){
        System.out.println("pre_visited:"+Arrays.toString(pre_visited));
    }

    public void showAlready(){
        System.out.println("already:"+Arrays.toString(already));
    }


    /**
     * @param start 起点
     * @param index 目标 也是终点
     * @param vertex 下标对应的字符char数组
     */
    public void getRoad(int start,int index,char[] vertex){
        System.out.print("路线为:"+vertex[start]);
        int pre=index;
        LinkedList linkedList=new LinkedList();
        pre=pre_visited[pre];
        while (pre!=start){
            linkedList.addFirst(pre);
            //System.out.print("->"+vertex[pre]);
            pre=pre_visited[pre];

        }
        linkedList.addLast(index);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print("->"+vertex[(int)linkedList.get(i)]);
        }

    }
}