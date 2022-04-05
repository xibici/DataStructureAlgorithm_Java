package again.kmp;

import java.lang.reflect.Array;
import java.util.Arrays;

public class kmpDemo {

    public static void main(String[] args) {

        String src="AAACAAABBA";
        String dest="AAAB";
        int[] next = getNextArr(dest);
        System.out.println(Arrays.toString(next));

        int i = kmpMatch(src, dest, next);
        System.out.println(i);

    }

    private static int kmpMatch(String src,String dest, int[] next) {
        int i=0;
        int j=0;

        while (i<src.length()&& j<dest.length()){

            if(src.charAt(i)==dest.charAt(j)){
                i++;
                j++;
            }else {

                if(j==0){
                    i++;
                }else {
                    j=next[j-1];
                }
            }

        }


        if(j==dest.length()){
            return i-j+1;
        }else {
            return -1;
        }
    }


    public static int[] getNextArr(String str){
        int[] next=new int[str.length()];
        next[0]=0;

        for (int i= 1, j=0; i < str.length(); i++) {

            while (j>0 && str.charAt(i)!=str.charAt(j) ){
                j=next[j-1];
            }

            if (str.charAt(i)==str.charAt(j)){
                j++;
            }
            next[i]=j;


        }

        return next;
    }


}
