package sort;

import java.util.Arrays;

public class RadixSort {


    public static void main(String[] args) {
        int arr[]={53,3,542,748,14,214};
        System.out.println("0:"+Arrays.toString(arr));
        System.out.println("0:"+arr);

        //doTest(arr);
        doRes(arr);
        System.out.println("1:"+arr);

    }

    private static void doRes(int[] arr) {
        //创建10个桶 0-9   每个桶的长度是数组长度
        int[][] buckets=new int[10][arr.length];
        //需要知道目前buckets每个桶有多少个数 用数组记录
        int[] bucketsCount=new int[10];

        //找出数组最大数的位数
        int tmpMax=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(tmpMax<arr[i]){
                tmpMax=arr[i];
            }
        }

        int digiMax=(tmpMax+"").length();
        System.out.println("最大:"+tmpMax+"\t位数:"+digiMax);

        int mul=1;
        for (int z = 0; z < digiMax; z++) {

            //应该是循环数组的数
            for (int i = 0; i < arr.length; i++) {
                //int digitOfElement=arr[i] / (int)Math.pow(10, z) % 10; //个位
                int digitOfElement=arr[i] / (int)Math.pow(10, z) % 10; //个位
                buckets[digitOfElement][bucketsCount[digitOfElement]]=arr[i];
                bucketsCount[digitOfElement]++;
            }

            //上面存进去 然后拿出来给arr
            int index=0;
            //遍历桶
            for (int i = 0; i < bucketsCount.length; i++) {
                //System.out.println("第"+i+"桶");
                if(bucketsCount[i]!=0){
                    for (int j = 0; j < bucketsCount[i]; j++) {
                        arr[index]=buckets[i][j];
                        index++;
                        //然后清空
                        buckets[i][j]=0;
                    }
                }
                bucketsCount[i]=0;
            }
            System.out.println("第"+(z+1)+"轮数组为:"+Arrays.toString(arr));
        }





    }

    public static void doTest(int[] arr){

        //创建10个桶 0-9   每个桶的长度是数组长度
        int[][] buckets=new int[10][arr.length];

        //需要知道目前buckets每个桶有多少个数 用数组记录
        int[] bucketsCount=new int[10];

        //应该是循环数组的数
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement=arr[i] / 1 % 10; //个位
            buckets[digitOfElement][bucketsCount[digitOfElement]]=arr[i];
            bucketsCount[digitOfElement]++;
        }
        
        //上面存进去 然后拿出来给arr
        int index=0;
        //遍历桶
        for (int i = 0; i < bucketsCount.length; i++) {
            //System.out.println("第"+i+"桶");
            if(bucketsCount[i]!=0){
                for (int j = 0; j < bucketsCount[i]; j++) {
                    arr[index]=buckets[i][j];
                    index++;
                    //然后清空
                    buckets[i][j]=0;
                }
            }
            bucketsCount[i]=0;
        }
        System.out.println("个位排序: "+Arrays.toString(arr));



        //第二轮 十位
        //应该是循环数组的数
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement=arr[i] / 10 % 10; //个位
            buckets[digitOfElement][bucketsCount[digitOfElement]]=arr[i];
            bucketsCount[digitOfElement]++;
        }

        //上面存进去 然后拿出来给arr
        index=0;
        //遍历桶
        for (int i = 0; i < bucketsCount.length; i++) {
            //System.out.println("第"+i+"桶");
            if(bucketsCount[i]!=0){
                for (int j = 0; j < bucketsCount[i]; j++) {
                    arr[index]=buckets[i][j];
                    index++;
                    //然后清空
                    buckets[i][j]=0;
                }
                bucketsCount[i]=0;
            }
        }
        System.out.println("十位排序: "+Arrays.toString(arr));

//第二轮 十位
        //应该是循环数组的数
        for (int i = 0; i < arr.length; i++) {
            int digitOfElement=arr[i] / 100 % 10; //个位
            buckets[digitOfElement][bucketsCount[digitOfElement]]=arr[i];
            bucketsCount[digitOfElement]++;
        }

        //上面存进去 然后拿出来给arr
        index=0;
        //遍历桶
        for (int i = 0; i < bucketsCount.length; i++) {
            //System.out.println("第"+i+"桶");
            if(bucketsCount[i]!=0){
                for (int j = 0; j < bucketsCount[i]; j++) {
                    arr[index]=buckets[i][j];
                    index++;
                    //然后清空
                    buckets[i][j]=0;
                }
                bucketsCount[i]=0;
            }
        }
        System.out.println("百位排序: "+Arrays.toString(arr));


    }
}
