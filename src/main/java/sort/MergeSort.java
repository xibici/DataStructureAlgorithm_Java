package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int arr[]={8,4,5,7 , 1,3,6,2};

        int left=0;
        int right=arr.length-1;
        int mid=(left+right)/2;
        int tmp[]=new int[8];

        System.out.println(left);
        System.out.println(right);
        System.out.println(mid);
        merge(arr,left,mid,right,tmp);

    }

    private static void merge(int[] arr,int left,int mid,int right,int[] temp) {
        int i=left;
        int j=mid+1;  //中间节点再加一个右边
        int t=0; //t是tmp的下标

        //都还没结束
        while (i<=mid && j<=right){

            if(arr[i]<=arr[j]){
                temp[t]=arr[i];
                i++;
                t++;
            }else {
                temp[t]=arr[j];
                j++;
                t++;
            }

        }

        //j结束了 不满足上面条件
        while (i<=mid){
            temp[t]=arr[i];
            t++;
            i++;
        }
        //i结束了 不满足上面条件
        while (j<=right){
            temp[t]=arr[j];
            t++;
            j++;
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(temp));


        //tmp转回去arr中
        t=0;
        int tempLeft=left;
        while(tempLeft<=right){

            arr[tempLeft]=temp[t];
            t++;
            tempLeft++;
        }


        System.out.println("-------??----");
        System.out.println(Arrays.toString(arr));

    }


}
