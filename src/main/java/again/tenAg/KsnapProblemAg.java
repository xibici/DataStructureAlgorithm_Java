package again.tenAg;

import java.util.Arrays;

public class KsnapProblemAg {

    public static void main(String[] args) {

        int[] val={1500,3000,2000};
        int[] w={1,4,3};

        int n=val.length; //商品个数
        int m=4;  //背包容量j

        //最大价值表
        int[][] v=new int[n+1][m+1];
        //备用 用于显示最大价值的选择路径
        int[][] path=new int[n+1][m+1];

        //0行 和 0列都置0
        for (int i = 0; i <= n; i++) {
            v[i][0]=0;
            v[0][i]=0;
        }


        //v表都从1开始  下表有问题
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if(w[i-1]>j){
                    v[i][j]=v[i-1][j];
                }else {//w[i] <=j

                    v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //如果是这个组合 就需要记录一下path
                    path[i][j]=1;
                }

            }
        }


        for (int[] link:v) {
            System.out.println(Arrays.toString(link));
        }
        System.out.println();
        for (int[] link:path) {
            System.out.println(Arrays.toString(link));
        }

        //从倒数来输出这个最后组合的 选择路径
        int i=path.length-1; //行
        int j=path[0].length-1;  //列

        while (i>0 && j>0){
            if(path[i][j]==1){
                System.out.print("->"+i);
                j=j-w[i-1];
            }
            i--;
        }
    }
}
