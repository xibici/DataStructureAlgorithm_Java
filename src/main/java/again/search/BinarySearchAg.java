package again.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchAg {
    public static void main(String[] args) {
        //从小到大
        //然后需要解决找到所有匹配的值 重复的时候
        //int arr[]={1,2,3,4,5,6,7,8,9,10};
        int arr[]={1,2,3,4,5,6,7,7,7,7,8,9,10};
        //int index = binarySearchBase(7, 0, arr.length, arr);
        List<Integer> resList = binarySearch(1, 0, arr.length, arr);
        System.out.println(resList);
    }

    /**
     * 基础版 只能找到一个值
     * @param value 需要查找的值
     * @param left  本次查找时从左边开始的索引
     * @param right 本次查找时从右边结束的索引
     * @param arr   查找需要用到的数组arr
     * @return 返回查找到值的索引
     */
    public static int binarySearchBase(int value,int left,int right,int[] arr){
        int mid=(left+right)/2;
        /*if(mid>=arr.length){
            return -1;
        }*/
        if(left>=right){
            return -1;
        }

        if(value==arr[mid]){//返回中间值
            return mid;
        }else if(value<arr[mid]){
            return binarySearchBase(value,left,mid-1,arr);
        }else {// >
            return binarySearchBase(value,mid+1,right,arr);
        }
    }

    /**
     * 正常版 找到所有相同的数
     * @param value
     * @param left
     * @param right
     * @param arr
     * @return
     */
    public static List<Integer> binarySearch(int value, int left, int right, int[] arr){
        int mid=(left+right)/2;
        if(left>=right){
            return null;
        }
        //因为是用mid匹配 从中间找的 所以还需要知道前面的值是不是
        // 而且因为是有序的 不用担心相同的值中间穿插一个数如 {101,100,101}
        if(arr[mid]==value){//返回中间值
            List<Integer> tmpList=new ArrayList<Integer>();
            tmpList.add(mid);
            //这次向左
            int tmp=mid-1;
            while (tmp>=0){
                if(arr[tmp]==value){
                    tmpList.add(tmp);
                    tmp--;
                }else {
                    break;
                }
            }
            //这次向右
            tmp=mid+1;
            while (tmp<=arr.length-1){
                if(arr[tmp]==value){
                    tmpList.add(tmp);
                    tmp++;
                }else {
                    break;
                }
            }

            return tmpList;
        }else if(value<arr[mid]){
            return binarySearch(value,left,mid-1,arr);
        }else {// >
            return binarySearch(value,mid+1,right,arr);
        }
    }

}
