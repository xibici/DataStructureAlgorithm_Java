package ten10;

public class ViolenceMatch {

    public static void main(String[] args) {

        String str1="i love java!";
        String str2="java?";
        String str3="java";

        int res= violenceMatch(str1,str3);
        System.out.println(res);
    }

    public static int violenceMatch(String str1,String str2) {

        char[] s1=str1.toCharArray();
        char[] s2=str2.toCharArray();

        //s1指针i  s2指针j
        int i=0;
        int j=0;

        while (i<s1.length && j< s2.length){

            if(s1[i]==s2[j]){
                i++;
                j++;
            }else {
                //不相等时后回溯    最后+1是为了跳过这个字符 防止一直失败后重复遍历同一个
                i=i-(j-1);//i=i-j+1;
                j=0;
            }

        }

        //匹配完成或者匹配失败 //最后会溢出? j+1
        if(j==s2.length){
            return i-j;
        }else {
            return -1;
        }


    }


}
