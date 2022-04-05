package again.tenAg;

import java.util.Arrays;

public class Floyd{


    public static void main(String[] args) {
        //doSmall();
        doBig();
    }

    private static void doBig() {
        char[] vertex={'0','1','2','3','4','5','6','7','8'};
        int z=65535;
        int[][] dis={ //找个大点的
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

        GraphFloyd graph=new GraphFloyd(dis);
        floyd(graph);
        //graph.showPreGraph();
        graph.showDisGraph();
        System.out.println(Arrays.toString(vertex));
        System.out.println();

        graph.getRoad(0,3,vertex);
    }

    private static void doSmall() {
        char[] vertex={'A','B','C','D','E','F','G'};
        int z=65535;
        int[][] dis={
                {0,5,7,z,z,z,2},
                {5,0,z,9,z,z,3},
                {7,z,0,z,8,z,z},
                {z,9,z,0,z,4,z},
                {z,z,8,z,0,5,4},
                {z,z,z,4,5,0,6},
                {2,3,z,z,4,6,0},
        };

        GraphFloyd graph=new GraphFloyd(dis);

        floyd(graph);
        graph.showPreGraph();
        System.out.println(Arrays.toString(vertex));
        System.out.println();

        //获得一条路 C->D
        graph.getRoad(2,3,vertex);

    }

    public static void floyd(GraphFloyd graph) {
        int[][] dis=graph.dis;
        int[][] pre=graph.pre;
        int vlen=graph.dis.length;

        int mulLen=0;

        //k为前驱节点 中间节点  第一次遍历
        for (int k = 0; k < vlen; k++) {
            //i为出发顶点
            for (int i = 0; i < vlen; i++) {
                //j为终点
                for (int j = 0; j < vlen; j++) {
                    mulLen=dis[i][k]+dis[j][k];
                    if(mulLen<dis[i][j]){
                        dis[i][j]=mulLen;
                        //一般来说是k 但是实际上有问题
                        //本身一开始来说 就i开始到j来看 中间节点是k i是起点 j是终点
                        //而接下来从k到j 中间可能并不是一开始的初始化的时候自己的值K  意思是k到j可能不通 k通过j需要通过某个节点这时候就要记录某个节点了
                        //如果k到j不通 记录的是某个点如Z点 就需要到时候吧节点换成Z了  i->k->Z->j
                        pre[i][j]=pre[k][j];
                    }
                }
            }
        }


    }

}

class GraphFloyd {

    int[][] dis;
    int[][] pre;

    public GraphFloyd(int[][] dis) {
        this.dis = dis;
        //初始化pre
        int vlen = dis.length;
        pre = new int[vlen][vlen];

        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                pre[i][j] = i;
            }
            //或者直接一句 Arrays.fill(pre[i],i);
        }

    }

    public void showDisGraph() {
        for (int[] link : dis) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void showPreGraph() {
        for (int[] link : pre) {
            System.out.println(Arrays.toString(link));
        }
    }

   /*
    public void getRoad(int src, int dest, char[] vertex) {
        LinkedList road = new LinkedList();
        System.out.println(vertex[src] + "到" + vertex[dest] + "的长度为:" + dis[src][dest]);

        int mid;
        //左中
        *//*while (pre[src][midEnd] != src) {
            midEnd=pre[src][midEnd]; //有中间节点


            road.addFirst(midEnd);
        }*//*

        if (pre[src][dest] != src) {
            mid=pre[src][dest];
            while (pre[src][mid]!=src){
                mid=pre[src][mid];
                road.addFirst(mid);

                if(pre[mid][dest]!=mid){
                    mid
                }
            }

            //重置
            mid=pre[src][dest];
            while (pre[src][mid]!=src){
                mid=pre[src][dest];
                road.addFirst(mid);
            }

        }

        midEnd=dest;
        int midStart = src;


        while (pre[midStart][midEnd] != src) {
            src = pre[src][rightMid]; //有中间节点
            road.addLast(src);
        }

        //先移除首个
        System.out.print("路线依次为:" + vertex[src]);
        for (int i = 0; i < road.size(); i++) {
            System.out.print("->" + vertex[(int) road.get(i)]);
        }
        System.out.print("->" + vertex[dest]);
        System.out.println();
    }
*/

    public void getRoad(int src, int dest, char[] vertex) {
        System.out.print("路线为: ");
        getRoadRecur(src,dest,vertex);
        System.out.println(vertex[dest]);

    }

        /** 递归版
         * @param src
         * @param dest
         * @param vertex
         */
    public void getRoadRecur(int src, int dest, char[] vertex) {
        if(pre[src][dest]!=src){//左
            int mid=pre[src][dest];
            getRoadRecur(src,mid,vertex);
            getRoadRecur(mid,dest,vertex);
        }else {
            System.out.print(vertex[src]+"->");
        }
    }

}