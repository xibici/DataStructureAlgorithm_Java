package ten10;

import java.util.ArrayList;

public class BinarySearchNoRecur {
    //非递归 二分查找法

    public static void main(String[] args) {
        //必须要从小到大排!!!!!!!!!!!  有顺序的排序
        //错误演示: int[] arr={11,61,1,3,94,64156,131513,51,515,3156,1561,561,561,56,1561,5,864,85,15,156,5,15};
        int[] arr={1,2,3,4,5,8,9};

        System.out.println(binarySearch(arr,2));
        System.out.println(binarySearch(arr,4));
        System.out.println(binarySearch(arr,8));
        System.out.println(binarySearch(arr,9));
        System.out.println(binarySearch(arr,0));
    }

    private static int binarySearch(int[] arr,int target) {
        int left=0;
        int right=arr.length;
        //用mid接收最后结果
        int mid;

        //这样截取或者下面这种 while (left<=right){
        while (true){
            if(left>=right){
                return -1;
            }
            mid=(left+right)/2;
            if (target==arr[mid]){
                return mid;
            }else if(target<arr[mid]){//顺序排列  target比中间小
                right=mid-1;
            }else {
                left=mid+1;
            }



        }
        //没有找到

    }

}
