package again.sort;

import java.util.Arrays;

public class MergeSortAg {

    public static void main(String[] args) {
        int arr[]={8,4,5,7,1,3,6,2};
        int[] tmp=new int[arr.length-1];
        mergeSort(arr,0,arr.length-1,tmp);
    }

    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        //分
        int mid=(left+right)/2;
        if(left<right){ //这里小于是为了判断等下等于的情况
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);

            mergeAg(arr,left,mid,right,temp);
        }

    }
    /**
     *这里是治
     * @param arr
     * @param left 遍历左边下标
     * @param mid   遍历中间下标
     * @param right 遍历右边下标
     * @param tmp   数据放在哪个数组
     */
    public static void mergeAg(int[] arr,int left,int mid,int right,int[] tmp){

        int i=left;
        int j=mid+1;
        int index=0;

        //有一方越界了那就只能剩下一方全部补满
        while (i<=mid && j<= arr.length-1){
            if(arr[i]<arr[j]){
                tmp[index]=arr[i];
                index++;
                i++;
            }else {
                tmp[index]=arr[j];
                index++;
                j++;
            }
        }

        if(i>mid){//左边满了
            while (j<=arr.length-1){
                tmp[index]=arr[j];
                index++;
                j++;
            }
        }
        //TODO
        //if(j> arr.length-1){//右 ???
        if(j> right){//右
            while (i<=mid){
                tmp[index]=arr[i];
                index++;
                i++;
            }
        }

    }
}
