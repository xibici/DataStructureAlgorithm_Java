package ten10;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChessboard {

    private static int X;//棋盘列数
    private static int Y;//棋盘行数
    private static boolean[] visited; //存放二维数组数量个元素 表示下标为这个元素是已经被访问过 X*Y+X
    private static boolean finished; //如果为true 表示成功 结束了


    public static void main(String[] args) {
        X=8;
        Y=8;
        //马儿的初始位置行列 从1开始
        int row=1;
        int column=1;

        visited=new boolean[X*Y];
        int[][] chessboard=new int[Y][X];

        traversalChessboard(chessboard,row-1,column-1,1);
        for (int[] link:chessboard) {
            System.out.println(Arrays.toString(link));
        }

        /*结果
        [1, 8, 11, 16, 3, 18, 13, 64]
        [10, 27, 2, 7, 12, 15, 4, 19]
        [53, 24, 9, 28, 17, 6, 63, 14]
        [26, 39, 52, 23, 62, 29, 20, 5]
        [43, 54, 25, 38, 51, 22, 33, 30]
        [40, 57, 42, 61, 32, 35, 48, 21]
        [55, 44, 59, 50, 37, 46, 31, 34]
        [58, 41, 56, 45, 60, 49, 36, 47]
        * */
    }

    /**
     * 完成骑士周游问题的算法
     * @param chessboard 棋盘
     * @param row 马儿当前位置的行 从0开始
     * @param column 马儿当前位置的列 从0开始
     * @param step 是第几步,初始位置就是第一步
     */
    public static void traversalChessboard(int[][] chessboard,int row,int column,int step) {
        chessboard[row][column]=step;
        visited[row*X+column]=true;
        Point p1=new Point();
        p1.x=column; //数一下就知道了 x就是列数  y是行数
        p1.y=row;

        ArrayList<Point> ps = next(p1);
        sort(ps);
        while (!ps.isEmpty()){
            //进行遍历
            Point p = ps.remove(0);
            int index=X*p.y+p.x;
            if(!visited[index]){
                //visited[index]=true;
                //得到一个点 再次以这个点进行骑士周游
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //一个循环结束后
        //1.棋盘到目前位置,仍然没有走完
        //2.棋盘处于一个回溯过程

        //第一次的话意思是 走到了step=32而且 [7,0]这个点结束了 而且其他方向进不去
        // 最后一步step=32这里重置一下 [7,0]=0; 将这个点设置为没有访问过
        //并且退回到step=31 退回到31步里面回溯 而且31本次中不再选择[7,0]这个点接着进行遍历   (下次还是可以访问到31这个点)

        //**总结 就是走投无路就走到这一步 然后如果步数少于格子数而且没有结束的话
        //**就退回上一次并且不选择造成走投无路的这个点 进行继续遍历
        //if(step<X*Y &&!finished){//64的时候不满足前一个条件直接else了
        if(step<X*Y &&!finished){//64的时候不满足前一个条件直接else了  而且保证了64结束之后不回溯

            chessboard[row][column]=0;
            visited[row*X+column]=false;
        }else { //step 64的时候直接结束了 意思是走完了
            finished=true;

        }




    }

        /**
         * @param curPoint 传入当前节点
         * @return 返回一个当前节点可以走的Point数组
         */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps=new ArrayList<>();
        Point p1=new Point();
        int cx=curPoint.x;
        int cy=curPoint.y;
        //加入的是下一个顶点 而不是当前顶点
        //走5
        if((p1.x=cx-2)>=0 && (p1.y=cy-1)>=0){
            ps.add(new Point(p1));
        }
        //走6
        if((p1.x=cx-1)>=0 && (p1.y=cy-2)>=0){
            ps.add(new Point(p1));
        }
        //走7
        if((p1.x=cx+1)<X && (p1.y=cy-2)>=0){
            ps.add(new Point(p1));
        }
        //走0
        if((p1.x=cx+2)<X&& (p1.y=cy-1)>=0){
            ps.add(new Point(p1));
        }
        //走1
        if((p1.x=cx+2)<X && (p1.y=cy+1)<Y){
            ps.add(new Point(p1));
        }
        //走2
        if((p1.x=cx+1)<X && (p1.y=cy+2)<Y){
            ps.add(new Point(p1));
        }
        //走3
        if((p1.x=cx-1)>=0 && (p1.y=cy+2)<Y){
            ps.add(new Point(p1));
        }
        //走4
        if((p1.x=cx-2)>=0 && (p1.y=cy+1)<Y){
            ps.add(new Point(p1));
        }

        return ps;
    }

    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1=next(o1).size();
                int count2=next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });

    }

}


