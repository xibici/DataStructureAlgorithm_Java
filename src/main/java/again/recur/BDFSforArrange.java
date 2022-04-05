package again.recur;

import java.util.Arrays;
import java.util.Stack;

public class BDFSforArrange {
    //dfs bfs获得全排列
    public static Stack<Integer> stack = new Stack<Integer>();
    public static void main(String[] args) {

        int[] arr={1,2,3,4};
        fun(arr,4,0);
    }

    private static void f(int[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if(cur == targ) {
            System.out.println(stack);
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

    public static void fun(int[] shu, int targ, int cur){
        if(targ==cur){
            System.out.println(stack);
            System.out.println(stack.elements());
            return;
        }//结束了

        for (int i = 0; i < targ; i++) {
            if(!stack.contains(shu[i])){
                stack.push(shu[i]);
                fun(shu,targ,cur+1);
                stack.pop();
            }

        }

    }


}
