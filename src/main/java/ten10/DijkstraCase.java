package ten10;

import java.util.Arrays;

public class DijkstraCase {

    public static void main(String[] args) {

        int z=65535;
        char[] vertex={'A','B','C','D','E','F','G'};
        int[][] matrix={
                {z,5,7,z,z,z,2},
                {5,z,z,9,z,z,3},
                {7,z,z,z,8,z,z},
                {z,9,z,z,z,4,z},
                {z,z,8,z,z,5,4},
                {z,z,z,4,5,z,6},
                {2,3,z,z,4,6,z},
        };

        Graph graph=new Graph(matrix,vertex);
        //graph.showGraph();
        //g 开始
        graph.dsj(6);
        VisitedVertex vv = graph.vv;
        graph.showArr();
    }

}

class Graph{
    int[][] matrix; //邻接矩阵
    char[] vertex; //顶点数组
    VisitedVertex vv;



    public Graph(int[][] matrix, char[] vertex) {
        this.matrix = matrix;
        this.vertex = vertex;
    }

    public void showGraph(){

        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));
        }
    }


    //实现迪杰斯特拉算法  从index这个节点开始
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            int i1 = vv.updateArr();
            update(i1);
        }
    }


    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱节点
     * 就是换了当前节点之后 更新dis和pre_visited数组
     * 而dis数组从邻接矩阵中查找 matrix[index] 就是
     * @param index
     */
    public void update(int index){
        int len=0;

        //从邻接矩阵中查找
        for (int j = 0; j < matrix[index].length; j++) {
            //len 意思是 出发顶点到index的距离(前几次) +从index到j顶点的距离 (这次)
            //在更新之前 dis保存的还是index的上一个顶点到index的最短距离
            // 然后上一个顶点完成之后到index进行遍历了
            //一开始从G开始 dis[G]->dis[6]=0 +martix里面的值   而不是65535
            //每一次的getDis(index)获取index的距离都是 起点离index节点的距离
            len=vv.getDis(index)+matrix[index][j];
            //如果j顶点没有被问过 并且len小于出发顶点到j顶点的距离 就需要更新
            if(!vv.in(j)&& len<vv.getDis(j)){ //就是如果从起始节点进入到index的顶点 index顶点的pre和dis是不动的
                vv.updatePre(j,index);//更新j的前驱为index顶点
                vv.updateDis(j,len);//更新出发顶点到j顶点的距离
            }
        }

    }

    public void update2(int index){
        int len=0;

        for (int j = 0; j < vertex.length; j++) {
            len=matrix[index][j]+ vv.getDis(index);
            
        }
    }

    public void showArr(){
        System.out.println(Arrays.toString(vv.already_arr));
        System.out.println(Arrays.toString(vv.dis));
        System.out.println(Arrays.toString(vv.pre_visited));
    }
    
}


class VisitedVertex{

    //下标对应的都是字母
    int[] already_arr;  //记录已经被访问的顶点
    int[] dis; //表示当前顶点对于其他顶点之间的最小距离
    int[] pre_visited;  //每个下标顶点在最后一次被访问的时候的顶点下表  或每个下标对应的值为前一个顶点的下标

    final int z=65535; //表示此路不通或者自己的标志符


    /**
     *
     * @return 返回由当前节点做起点 对于其他所有节点为终点里面 路径最小而且没有被访问过得节点的下标
     */
    public int updateArr(){
        int min= 65535;
        int index=0;

        //跳过i等于0 dis[A]就没有了 错误了
        for (int i = 0; i < dis.length; i++) {//遍历每一个节点
            if(already_arr[i]==0 && dis[i]<min){//应该是判断有没有访问过
                min=dis[i];
                index=i;
            }

        }
        //最终找到最小的值了 标记为已访问并返回节点让他给别人访问
        already_arr[index]=1;
        return index;
    }


    /**
     *
     * @param length  需要构建的字符个数   字符数量
     * @param index   需要从第几个字符开始遍历 为起点
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.dis = new int[length];
        this.pre_visited = new int[length];

        already_arr[index]=1; //起始节点标为已访问
        Arrays.fill(dis,z); //距离除了起点 全设为z 65535最大值
        dis[index]=0;
    }

    /**
     * @param index 查询index这个节点有没有被访问过
     * @return
     */
    public boolean in(int index){
        return already_arr[index] ==1;
    }

    /**
     * 更新出发顶点到index这个下标的节点的最小路径
     * @param index 某个节点的下标
     * @param len   最小路径长度
     */
    public void updateDis(int index,int len){
            dis[index]=len;
    }

    /**
     * 更新该pre节点上一次被index这个节点访问了
     * 或为  更新顶点的前驱节点为index节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index) {
        pre_visited[pre]=index;
    }

    /**
     * @param index 返回出发顶点到index这个节点的距离
     */
    public int getDis(int index){
        return dis[index];
    }


    
}
