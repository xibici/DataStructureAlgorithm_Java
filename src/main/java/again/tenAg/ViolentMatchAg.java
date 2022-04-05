package again.tenAg;

public class ViolentMatchAg {

    public static void main(String[] args) {
        //String s1="i love1 love love you";
        String s1="i love1 love love2 you";
        String s2="love2";

        System.out.println(s1.lastIndexOf("love2"));

        int i = violentMat(s1, s2);
        System.out.println(i);

    }

    /**
     * @param s1 总文本
     * @param s2 需要匹配的str
     * @return
     */
    public static int violentMat(String s1,String s2){

        int i=0,j=0;

        while (i<s1.length()&&j<s2.length()){

            if(s1.charAt(i)==s2.charAt(j)){
                i++;
                j++;
            }else {
                i=i-j+1;
                j=0;
            }


        }

        if(j==s2.length()){
            return i-j;
        }
        return -1;
    }

}
