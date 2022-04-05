package again.tenAg;

public class BinarySearchNoRecurAg {

    public static void main(String[] args) {
        //必须是有序数组
        int[] arr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

        //二分查找 非递归版
        //查找一个数的下标
        int i = binarySearchNoRec(19, arr);
        System.out.println(i);
    }

    public static int binarySearchNoRec(int i,int[] arr){
        int left=0;
        int right=arr.length-1;
        int midIndex=0;


        while (left<=right){
            midIndex=(left+right)/2;
            if(i<arr[midIndex]){
                right=midIndex-1;
            }else if(i>arr[midIndex]){
                left=midIndex+1;//
            }else {//相等的情况找到了
                return midIndex;
            }
        }


        return -1;
    }

}
