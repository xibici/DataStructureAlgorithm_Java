package again.recur;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class MigongBFS {
    //类似马踏棋盘算法
    public static int min=0;
    public static boolean[] isVisited;
    public static int X;//长度 到时候减下标
    public static int Y;
    public static int step=0;
    public static boolean finished=false;

    public static int[][] migong;

    public static void main(String[] args) {

        Y=8;//8行
        X=7;//7列
        isVisited=new boolean[X*Y];
        migong=new int[Y][X];

        for (int i = 0; i < migong[0].length; i++) {//每列
            migong[0][i]=-1;
            migong[migong.length-1][i]=-1;
            isVisited[i]=true;
            isVisited[i*migong[i].length-1+X]=true;
        }
        for (int i = 0; i < migong.length; i++) {//每行
            migong[i][0]=-1;
            migong[i][migong[i].length-1]=-1;

            isVisited[i*X+0]=true;
            isVisited[i*X+migong[i].length-1]=true;
        }
        migong[3][0]=-1;
        migong[3][1]=-1;
        migong[3][2]=-1;
        isVisited[3*X+0]=true;
        isVisited[3*X+1]=true;
        isVisited[3*X+2]=true;


        /*for (int[] link:migong) {
            System.out.println(Arrays.toString(link));
        }*/

        //1,1 开始
        int startY=1;
        int startX=1;
        Point tp=new Point(startX,startY);
        runMigong(tp);
        //获得当前可以移动的地点的ArrayList

        for (int i = 0; i < migong.length; i++) {
            for (int j = 0; j < migong[i].length; j++) {
                System.out.printf("%3d ",migong[i][j]);
            }
            System.out.println();
        }

    }

    public static void runMigong(Point tp){
        if(!finished){
            step++;
            int tIndex=tp.y*X+tp.x;
            isVisited[tIndex]=true;
            migong[tp.y][tp.x]=step;

            if(migong[migong.length-2][migong[0].length-2]!=0 && !finished){
                finished=true;

                System.out.println(step);
                return;
            }

            ArrayList<Point> nextPoints = next(tp);
            //System.out.println(nextPoints);
            while (!nextPoints.isEmpty()){
                //获得下一步
                Point p1 = nextPoints.remove(0);
                if(p1!=null){
                    //如果没有访问过就进去
                    int vIndex=p1.y*X+p1.x;
                    if(!isVisited[vIndex]){
                        //访问他
                        runMigong(p1);
                    }
                }

            }
        }

    }



    public static ArrayList<Point> next(Point curP) {
        //tP->tmpP
        //暂时不考虑是否已访问
        Point p=new Point();
        int cx= curP.x;
        int cy= curP.y;
        ArrayList<Point> list=new ArrayList();
        //四个方向
        //上
        //下标  x是0和6是墙
        // 还有3,0 3,1 3,2
        if((p.y=cy-1)>0){
            p.x=cx;
            list.add(new Point(p));
        }
        //左
        if((p.x=cx-1)>0){
            p.y=cy;
            list.add(new Point(p));
        }
        //右
        if((p.x=cx+1)<X-1){//6
            p.y=cy;
            list.add(new Point(p));
        }
        //下
        if((p.y=cy+1)<Y-1){//7
            p.x=cx;
            list.add(new Point(p));
        }

        return list;
    }

/*
    public static ArrayList<Point> runMigongCountBest(int[][] migong, int i, int j, int[] runMode){
        //如果到终点了结束
        if(migong[6][5]==2){
            return true;
        }else { //进来就选择方向前进
            //上右下左
            if(migong[i][j]==0){
                migong[i][j]=2;
                min++;
                //每判断一次都算作一次
                //长度5 第五个是放结果的
                for (int k = 0; k < runMode.length-1-1; k++) {//一共四个方向 4次 4个数 上右下左
                    switch (runMode[k]){
                        case 1://上
                            if(runMigongCountBest(migong,i-1,j,runMode)) return true;
                            break;
                        case 2://右
                            if(runMigongCountBest(migong,i,j+1,runMode)) return true;
                            break;
                        case 3://下
                            if(runMigongCountBest(migong,i+1,j,runMode)) return true;
                            break;
                        case 4://左
                            if(runMigongCountBest(migong,i,j-1,runMode)) return true;
                            break;
                        default:
                            System.out.println("未知错误");
                            break;

                    }
                }
            }else {
                return false;
            }
        }
        return false;
    }*/
}
