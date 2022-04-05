package again.sort;

import java.util.Arrays;

public class RadixBucketSortAg {

    public static void main(String[] args) {
        int[] arr={53,3,542,748,14,214};

        buckSort(arr);
    }

    private static void buckSort(int[] arr) {
        //二维数组表示10个桶  int[][] buckets=new int[arr.length][10];
        int[][] buckets=new int[10][arr.length];

        //需要做个数组记录 不知道放的桶有多少个 不然不知道下标多少放进去
        int[] sizes=new int[10];
        //用来后面重新排arr数组
        int arrIndex;

        int max=0;
        for (int i = 0; i < arr.length; i++) {
            if(max<arr[i]){
                max=arr[i];
            }
        }


        int digitalNum=(max+"").length();
        System.out.println(digitalNum);
        //for (int i = 0; i < digitalNum; i++) {
        for (int i = 0; i < digitalNum; i++) {
        //while (sizes[0]!=arr.length){ //这样百变千多一次其实不好
            sizes=new int[10];
            buckets=new int[10][arr.length];
            //个位开始
            for (int j = 0; j < arr.length; j++) {
                int units=arr[j]/ (int)(Math.pow(10,i)) %10;
                int oIndex=sizes[units];
                buckets[units][oIndex]=arr[j];
                sizes[units]++;
            }
            //然后重新排数组
            arrIndex=0;
            for (int j = 0; j < buckets.length; j++) {
                for (int k = 0; k < sizes[j]; k++) {
                    arr[arrIndex]=buckets[j][k];
                    arrIndex++;
                }

            }

        }
/*

        //个位开始
        for (int j = 0; j < arr.length; j++) {
            int units=arr[j]%10;
            int oIndex=sizes[units];
            buckets[units][oIndex]=arr[j];
            sizes[units]=oIndex+1;
        }
        //然后重新排数组
        arrIndex=0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                    arr[arrIndex]=buckets[i][j];
                    arrIndex++;
            }
        }

        //十位
        sizes=new int[10];
        buckets=new int[10][arr.length];
        for (int j = 0; j < arr.length; j++) {
            int units=arr[j]/10%10;
            int oIndex=sizes[units];
            buckets[units][oIndex]=arr[j];
            sizes[units]=oIndex+1;
        }
        //然后重新排数组
        arrIndex=0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                arr[arrIndex]=buckets[i][j];
                arrIndex++;
            }
        }


        //百位
        sizes=new int[10];
        buckets=new int[10][arr.length];
        for (int j = 0; j < arr.length; j++) {
            int units=arr[j]/10/10%10;
            int oIndex=sizes[units];
            buckets[units][oIndex]=arr[j];
            sizes[units]=oIndex+1;
        }
        //然后重新排数组
        arrIndex=0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                arr[arrIndex]=buckets[i][j];
                arrIndex++;
            }
        }

*/

        for (int[] link:buckets) {
            System.out.println(Arrays.toString(link));
        }
        System.out.println();
        System.out.println(Arrays.toString(sizes));
        System.out.println();
        System.out.println(Arrays.toString(arr));

    }

}
