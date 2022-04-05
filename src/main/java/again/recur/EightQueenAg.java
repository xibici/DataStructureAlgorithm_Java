package again.recur;

import java.util.Arrays;

public class EightQueenAg{

    public static int max=8;
    public static int[] arr;//记录皇后在哪列
    ;//记录皇后在哪列

    public static void main(String[] args) {
        //是没有用到地图的
        arr=new int[max];
        check(0);
        /*for (int[] link:map) {
            System.out.println(Arrays.toString(link));

        }*/
    }

    private static void check(int n) {
        if(n==max){
            print();
            return;
        }


        for (int i = 0; i < max; i++) {//每一列
            arr[n]=i;//假设这列可以 =i   假如不行就会进入下一次遍历i+1 然后此次的i不进取n+1行的下一个节点的遍历
            //判断该点是否运行时候是在递归方法内判断
            if(judge(n)){
                check(n+1);
            }
        }

    }


    private static int getRes(int n) {//第n行
        //之前是在这里循环
        for (int i = n-1; i >=0; i--) {//行数
            if(arr[i]==arr[n] || Math.abs(arr[i]-arr[n])==Math.abs(i-n)){
                break;
            }
        }



        return 0;
    }

    private static void print() {
        System.out.println(Arrays.toString(arr));
    }


    private static boolean judge(int n) {
        //查看n行的点是否能与其他点不冲突
        for (int i = 0; i < n; i++) {//遍历所有行
            if(arr[i]==arr[n] || Math.abs(n-i)==Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return true;
    }


}
