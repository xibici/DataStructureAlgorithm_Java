package again.tenAg;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Horse {

    public static int X=8;
    public static int Y=8;
    public static boolean[] isVisited;
    public static boolean finished;

    public static void main(String[] args) {
        int[][] chestboard=new int[X][Y];
        //初始点  从1开始 实际上等于下标0
        int row=1;
        int column=1;
        isVisited=new boolean[X*Y];
        traversalChessboard(chestboard,row-1,column-1,1);

        //易错点-> row*X+column   容易写成row*column
        //if不是while!!!!!!!!
        for (int[] link:chestboard) {
            System.out.println(Arrays.toString(link));
        }
    }


    public static void traversalChessboard(int[][] chestboard,int row,int column,int step){
        //这里忘了
        chestboard[row][column]=step;
        //从0开始 这里也是X
        isVisited[row*X+column]=true;
        Point p=new Point();
        p.x=column;
        p.y=row;

        ArrayList<Point> ps = next(p);
        sort(ps);

        while (!ps.isEmpty()){
            Point p1 = ps.remove(0);
            //这里是X*p1.y不是p1.x*p1.y
            //int index=p1.x*p1.y+p1.x;
            int index=X*p1.y+p1.x;
            //while (!isVisited[index]){  if不是while!!!!!!!!
            if(!isVisited[index]){
                //这样组覆盖了原数组 不是这样用的
                //next = next(new Point(p1));
                //递归
                traversalChessboard(chestboard,p1.y,p1.x,step+1);
            }
        }

        if(step<X*Y && !finished){
            //回溯取消这个点
            isVisited[row*X+column]=false;
            chestboard[row][column]=0;
        }else {
            finished=true;
        }

    }

    public static ArrayList<Point> next(Point curP){

        ArrayList ps=new ArrayList();
        int cx=curP.x;
        int cy=curP.y;

        Point p1=new Point();
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
