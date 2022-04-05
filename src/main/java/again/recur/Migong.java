package again.recur;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class Migong {

    public static int[][] allRun;
    public static int runCount;
    public static int judgeCount;




    public static void main(String[] args) {

        int[][] migong=new int[8][7];
        Arrays.fill(migong[0],1);
        Arrays.fill(migong[migong.length-1],1);

        for (int i = 0; i < migong.length; i++) {
            migong[i][0]=1;
            migong[i][migong[i].length-1]=1;
        }
        migong[3][0]=1;
        migong[3][1]=1;
        migong[3][2]=1;

        //上1 右2 下3 左4
        int[] mode={1,2,3,4};
        int arrageCount = getCcount2(mode.length);
        allRun=new int[arrageCount][mode.length+1+1];//多出来一位放一共走了多少格子 再多一位放一共判断了多少次

        int[] resRunCounts=new  int[allRun.length];
        int[] resJudgeCounts=new  int[allRun.length];
        f(mode,4,0);

        for (int i = 0; i < allRun.length; i++) {
            runMigongCountBest(migong,1,1,allRun[i]);//左上下右
            allRun[i][allRun[i].length-2]=runCount;
            allRun[i][allRun[i].length-1]=judgeCount;
            /*System.out.println("--------------------------");
            System.out.println("路线: "+Arrays.toString(allRun[i]));
            for (int[] link:migong) {
                System.out.println(Arrays.toString(link));
            }
            System.out.println("--------------------------");*/
            resRunCounts[i]=runCount;
            resJudgeCounts[i]=judgeCount;
            //重置一下
            runCount=0;
            judgeCount=0;
            resetMap(migong);
        }

        for (int[] link:allRun) {
            System.out.println(Arrays.toString(link));
        }

        System.out.println(Arrays.toString(resRunCounts));

        int min=resRunCounts[0];//假设第1个最小 下标0
        int minIndex=0;
        for (int i = 0; i < resRunCounts.length; i++) {
            if(resRunCounts[i]<min){
                min=resRunCounts[i];
                minIndex=i;
            }else if(resRunCounts[i]==min){
                if(resJudgeCounts[i]<resJudgeCounts[minIndex]){
                    min=resRunCounts[i];
                    minIndex=i;
                }
            }
        }

        System.out.print("最佳方向:");
        for (int i = 0; i < allRun[minIndex].length; i++) {
            if(i!=0&&i!=allRun[minIndex].length-1){
                System.out.print("->");
            }
            int direct = allRun[minIndex][i];
            switch (direct){
                case 1://上1 右2 下3 左4
                    System.out.print("上->");
                    break;
                case 2:
                    System.out.print("右");
                    break;
                case 3:
                    System.out.print("下");
                    break;
                case 4:
                    System.out.print("左");
                    break;
            }
        }

        System.out.println();
        System.out.println(Arrays.toString(allRun[minIndex])+"----结果重演-----");
        showRoute(migong,1,1,minIndex);
        for (int[] link:migong) {
            System.out.println(Arrays.toString(link));
        }
    }

    public static void showRoute(int[][] migong,int i,int j,int index){
        runMigongCountBest(migong,i,j,allRun[index]);
    }

    public static void resetMap(int[][] migong){
        for (int i = 0; i < migong.length; i++) {
            for (int j = 0; j < migong[i].length; j++) {
                if(migong[i][j]==2){
                    migong[i][j]=0;
                }
            }
        }
    }

    public static Stack<Integer> stack = new Stack<Integer>();
    public static int index=0;
    /**
     *
     * @param shu   待选择的数组
     * @param targ  要选择多少个次
     * @param cur   当前选择的是第几次
     */
    private static void f(int[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if(cur == targ) {
            Iterator<Integer> iterator = stack.iterator();
            for (int i = 0; i < allRun[index].length-1-1; i++) {
                allRun[index][i]=iterator.next();
            }
            index++;
            return;
        }

        for(int i=0;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f(shu, targ, cur+1);
                stack.pop();
            }

        }
    }

    public static int getCcount2(int modeLen){
        if(modeLen>1){
            return modeLen*getCcount2(modeLen-1);
        }else {
            return modeLen;
        }
    }


    //虽然可行 但是墙壁起点就会出错
    public static boolean runMigong(int[][] migong,int i,int j){
        //进来先访问
        migong[i][j]=2;
        //如果到终点了结束
        if(migong[6][5]==2){
            return true;
        }else { //进来就选择方向前进
            //上右下左
            if(migong[i-1][j]==0){
                return runMigong(migong,i-1,j);
            }else if(migong[i][j+1]==0){
                return runMigong(migong,i,j+1);
            }else if(migong[i+1][j]==0){
                return runMigong(migong,i+1,j);
            }else if(migong[i-1][j]==0){
                return runMigong(migong,i-1,j);
            }else {
                //死路
                return false;
            }
        }

    }

    public static boolean runMigongAg(int[][] migong,int i,int j,int mode){
        //如果到终点了结束
        if(migong[6][5]==2){
            return true;
        }else { //进来就选择方向前进
            //上右下左
            if(migong[i][j]==0){
                migong[i][j]=2;
                    if(runMigongAg(migong,i-1,j,1)){
                        return true;
                    }else if(runMigongAg(migong,i,j+1,1)){
                        return true;
                    }else if(runMigongAg(migong,i+1,j,1)){
                        return true;
                    }else if(runMigongAg(migong,i,j-1,1)){
                        return true;
                    }else {
                        return true;
                    }
            }else {
                return false;
            }
        }
        //return false;

    }

    public static boolean runMigongCountBest(int[][] migong,int i,int j,int[] runMode){
        //如果到终点了结束
        if(migong[6][5]==2){
            return true;
        }else { //进来就选择方向前进
            //上右下左
            if(migong[i][j]==0){
                migong[i][j]=2;
                //每判断一次都算作一次
                runCount++;
                //长度5 第五个是放结果的
                for (int k = 0; k < runMode.length-1-1; k++) {//一共四个方向 4次 4个数 上右下左
                    judgeCount++;
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
    }
}
