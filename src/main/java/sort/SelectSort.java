package sort;

import java.util.Arrays;

public class SelectSort {


    public static void main(String[] args) {
        //doExample();
        doRes();


    }
    static void doRes(){
        //int arr[]={ 3,9,-1,10,-2,15,48,97,1,0,4,1651};
        int arr[]={ 16,1,5,14};
        System.out.println("原数组:"+ Arrays.toString(arr));

        int min=arr[0];
        int minIndex=0;
        for (int i = 0; i < arr.length-1; i++) {
            //第一轮
            //比较四个数拿第一个最小值  { 3,9,-1,20}
            min=arr[i];
            minIndex=i;

            //这里要注意 随着轮数 开始的数也在增加
            for (int j = 0+i; j < arr.length; j++) {
                if(min>arr[j]){
                    min =arr[j];
                    minIndex=j;
                }
            }

            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }

            System.out.println("第"+(i+1)+"轮结果:"+ Arrays.toString(arr));

        }


    }

    static void doExample(){
        //int arr[]={ 3,9,-1,10,-2,15,48,97,1,0,4,1651};
        int arr[]={ 16,1,5,14};


        //第一轮
        //比较四个数拿第一个最小值  { 3,9,-1,20}
        int min=arr[0];
        int minIndex=0;

        for (int j = 0; j < arr.length; j++) {
            if(min>arr[j]){
                min =arr[j];
                minIndex=j;
            }
        }
        arr[minIndex]=arr[0];
        arr[0]=min;

        System.out.println("第一轮结果:"+ Arrays.toString(arr));

        //第二轮  去掉第一个的后面数组寻找最小值  {(3,) 9,-1,20}
        //所以假设第二个为最小
        min=arr[1];
        minIndex=1;

        for (int j = 1; j < arr.length; j++) {
            if(min>arr[j]){
                min =arr[j];
                minIndex=j;
            }
        }
        arr[minIndex]=arr[1];
        arr[1]=min;

        System.out.println("第二轮结果:"+ Arrays.toString(arr));

        //第二轮  去掉第一个的后面数组寻找最小值  {(3,) 9,-1,20}
        //所以假设第二个为最小
        min=arr[2];
        minIndex=2;

        for (int j = 2; j < arr.length; j++) {
            if(min>arr[j]){
                min =arr[j];
                minIndex=j;
            }
        }
        if(minIndex!=2){
            arr[minIndex]=arr[2];
            arr[2]=min;
        }


        System.out.println("第三轮结果:"+ Arrays.toString(arr));
    }
}
