package again.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class shellSort {

    public static void main(String[] args) {

        int[] arr={8,9,1,7,2,3,5,4,6,0};
        int gap=arr.length/2;
        shellSortAg(arr);
        //test1(arr);
    }

    public static void test1(int[] arr){
        int gap=arr.length/2;
        //第一回合
        //分五组
        int tmp=0;
        for (int i = 5; i < arr.length; i++) {
            //倒着来一直比
            for (int j = i-5; j >=0 ; j=j-5) {
                if(arr[j+5]<arr[j]){
                    tmp=arr[j+5];
                    arr[j+5]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //2轮
        gap=gap/2;
        for (int i = 2; i < arr.length; i++) {
            //倒着来一直比
            //9的时候 j从7开始 一直到循环到1
            for (int j = i-2; j >=0 ; j=j-2) {
                if(arr[j+2]<arr[j]){
                    tmp=arr[j+2];
                    arr[j+2]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //3
        gap=gap/2;
        for (int i = 1; i < arr.length; i++) {
            //倒着来一直比
            //9的时候 j从7开始 一直到循环到1
            for (int j = i-1; j >=0 ; j=j-1) {
                if(arr[j+1]<arr[j]){
                    tmp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));


    }

    public static void shellSortAg(int[] arr){
        int tmp=0;
        for (int gap = arr.length/2; gap>=1; gap=gap/2) {
            for (int i = gap; i < arr.length; i++) {
                //倒着来一直比
                //9的时候 j从7开始 一直到循环到1
                for (int j = i-gap; j >=0 ; j=j-gap) {
                    if(arr[j+gap]<arr[j]){
                        tmp=arr[j+gap];
                        arr[j+gap]=arr[j];
                        arr[j]=tmp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));


    }
}
