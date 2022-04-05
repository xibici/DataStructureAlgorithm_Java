package ten10;

public class KnapsackProblem {

    //动态规划分布背包问题
    public static void main(String[] args) {

        int[] val={1500,3000,2000};
        int[] w={1,4,3};

        int n=val.length; //商品个数
        int m=4;  //背包容量j

        int[][] v=new int[n+1][m+1];
        int[][] path=new int[n+1][m+1];

        //看看二维数组长度是什么意思   应该是行数
        //System.out.println(v.length);

        //第一行第一列置0
        for (int i = 0; i < v.length; i++) {
            //v[0].length 一行有多少个数 也就是列数
            v[i][0]=0;
        }
        for (int i = 0; i < v.length; i++) {
            //v[0].length 一行有多少个数 也就是列数
            v[0][i]=0;
        }

        //都从1开始 不管0了
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //本来是 w[i] 但是由于是从1而不是0开始 下标开始整体移动
                if(w[i-1]>j){
                    v[i][j]=v[i-1][j];
                }else { //相反 w[w-1]<=j
                   // v[i][j]=Math.max(v[i-1][j],(val[i-1]+v[i-1][j-w[i-1]]));
                    if(v[i-1][j]<(val[i-1]+v[i-1][j-w[i-1]])){
                        v[i][j]=(val[i-1]+v[i-1][j-w[i-1]]);
                        //而且path设置
                        path[i][j]=1;
                    }else {
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }

        //打印看看
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("----------------------------------");
        //因为需要知道最后一排最后一个数组的详细搭配组合 而不只是最佳最大值
        //因为只需要知道最后的排列组合 重写一个
        /*for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j]==1){
                    System.out.printf("放入了第%d个商品",i);
                    System.out.println();
                }
            }
        }*/

        //重写后
        int i=path.length-1;
        int j=path[0].length-1;

        while (i>0&&j>0){
            if(path[i][j]==1){
                System.out.println("放入了第"+(i)+"个商品");
                j-=w[i-1];
            }
            //从倒数开始遍历
            //比如电脑占去了3个重量剩下1  j=1
            // 然后就去寻找 path[?][1]中 ?为谁的时候 是满足path[?][1]=1的条件
            // 这时候?就是需要寻找的商品了
            i--;

        }

    }




}
