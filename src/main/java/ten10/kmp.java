package ten10;

import java.lang.reflect.Array;
import java.util.Arrays;

public class kmp {



    public static void main(String[] args) {
        String s1="BBC ABCDAB ABCDABCDABDE";
        String s2="ABCDABD";

        int[] next = kmpNext(s2);
        int res = kmpSearch(s1, s2, next);
        System.out.println(Arrays.toString(next));
        System.out.println(res);

    }

    //获取一个字符串(子串)的部分匹配值
    public static int[] kmpNext(String dest){
        int[] next=new int[dest.length()];
        next[0]=0;

        for (int i=1,j = 0; i<dest.length() ; i++) {
            if(j>0 && dest.charAt(i) != dest.charAt(j)){
                j=next[j-1];
            }

            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }


    public static int kmpSearch(String s1,String s2,int[] next){

        for (int i=0,j = 0; i < s1.length(); i++) {

            while (j>0 && s1.charAt(i)!=s2.charAt(j)){
                j=next[j-1];
            }
            if(s1.charAt(i)==s2.charAt(j)){
                j++;
            }
            if(j==s2.length()){
                return i-j+1;
            }

        }

        return -1;

    }
}
