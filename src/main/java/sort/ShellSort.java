package sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {

        int arr[]={15,30,5,95,56,13,20,44,9};
        //doExample(arr);
        doCom(arr);

    }

    private static void doCom(int[] arr) {

        int tmp=0;
        int count=0;
        //步长
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i <arr.length ; i++) {
                for (int j = i-gap; j >=0 ; j-=gap) {

                    if(arr[j+gap]<arr[j]){
                        tmp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=tmp;
                    }
                }

            }
            //count++;
            //每一轮
            //System.out.println("第"+count+"轮数据:"+Arrays.toString(arr));
            System.out.println("第"+(++count)+"轮数据:"+Arrays.toString(arr));
        }



    }

    private static void doExample(int[] arr) {
        //第一轮
        //int gap=arr.length/2;

        int tmp=0;
        //分组后的最大组的第一个数

        for (int i = 5; i < arr.length; i++) {

            //不是很懂
            for (int j = i-5; j >=0; j-=5) {
                if (arr[j]>arr[j+5]){
                    //希尔排序
                    tmp=arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5]=tmp;
                }
            }
        }
        System.out.println("------第一轮--------");
        System.out.println(Arrays.toString(arr));


        //第二轮
        //4/2=2
        for (int i = 2; i < arr.length; i++) {

            //不是很懂
            for (int j = i-2; j >=0; j-=2) {
                if (arr[j]>arr[j+2]){
                    //希尔排序
                    tmp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=tmp;
                }
            }
        }
        System.out.println("------第二轮--------");

        System.out.println(Arrays.toString(arr));

        //第三轮
        //2/2=1
        for (int i = 1; i < arr.length; i++) {

            //不是很懂
            for (int j = i-1; j >=0; j-=1) {
                if (arr[j]>arr[j+1]){
                    //希尔排序
                    tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
        System.out.println("------第三轮--------");

        System.out.println(Arrays.toString(arr));



    }

}
