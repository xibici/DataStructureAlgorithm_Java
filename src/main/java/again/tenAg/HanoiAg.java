package again.tenAg;

public class HanoiAg {


    public static void main(String[] args) {

        //以输出文本的形式表述
        //A塔放着   最终要移到C塔
        int n=4;
        char a='A';
        char b='B';
        char c='C';
        hanoi(n,a,b,c);

    }

    /* @param n 表示一共多少个盘
     * @param a 起点用哪个塔
     * @param b 起点到终点的过程用了哪个塔
     * @param c 将起点的塔转移到哪个塔为终点
     */
    public static void hanoi(int n, char a, char b, char c){

        if(n==1){
            System.out.println("第1个盘从"+a+"到"+c);
        }else {
            //n>=2
            //分成上层全部
            hanoi(n-1,a,c,b);
            //分成下层一个
            //hanoi(1,a,b,c);
            System.out.println("第"+n+"个盘从"+a+"到"+c);

            hanoi(n-1,b,a,c);
        }


    }

}
