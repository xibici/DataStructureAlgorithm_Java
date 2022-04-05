package again.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr={101,34,119,1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){

        for (int i = 1; i < arr.length ; i++) {
            int tmp=arr[i]; //需要移动或比较的目标值
            int preIndex=i-1;  //该值的前一个下标 因为每次比较时 前一位总是数组最大值

            while (preIndex>=0 && tmp <arr[preIndex]){//如果前一个下标在0的左边时候 证明已经没有需要比较的了
                //tmp <arr[index] 前一位总是数组最大值 加入tmp小于最大值就要一直遍历寻找适合tmp的位置
                //寻找位置的时候先把值后移
                arr[preIndex+1]=arr[preIndex];
                preIndex--;
            }
            //到0前一位 无需再比较 直接把数值放在0
            arr[preIndex+1]=tmp;
        }



    }
    public static void test(int[] arr){


        //第一轮
        int tmp=arr[1]; //需要移动或比较的目标值
        int preIndex=1-1;  //该值的前一个下标 因为每次比较时 前一位总是数组最大值

        while (preIndex>=0 && tmp <arr[preIndex]){//如果前一个下标在0的左边时候 证明已经没有需要比较的了
            //tmp <arr[index] 前一位总是数组最大值 加入tmp小于最大值就要一直遍历寻找适合tmp的位置
            //寻找位置的时候先把值后移
            arr[preIndex+1]=arr[preIndex];
            preIndex--;
        }

        //到0前一位 无需再比较 直接把数值放在0
        arr[preIndex+1]=tmp;

    }


}
