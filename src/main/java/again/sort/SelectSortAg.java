package again.sort;

import java.util.Arrays;

public class SelectSortAg {

    public static void main(String[] args) {
        int[] arr={101,34,119,1};
        int[] arr2={101,34,119,1};

        //selectSort(arr);
        selectSort(arr);
        System.out.println();
        selectSortAg(arr2);

        System.out.println(Arrays.toString(arr));

    }

    public static void test(int[] arr){
        int minIndex=0;
        int min=arr[0];


        for (int i = 1; i < arr.length ; i++) {
            if(arr[i]<min){
                min=arr[i];
                minIndex=i;
            }
        }

        arr[minIndex]=arr[0];
        arr[0]=min;

            //第二轮
        minIndex=1;
        min=arr[1];


        for (int i = 1; i < arr.length ; i++) {
            if(arr[i]<min){
                min=arr[i];
                minIndex=i;
            }
        }

        arr[minIndex]=arr[1];
        arr[1]=min;


    }


    public static void selectSort(int[] arr){
        //这样做会重复多几次 错误
        int minIndex=0;
        int min=arr[0];
        int count=0;
        for (int z = 0; z < arr.length; z++) {
            minIndex=-1;
            min=arr[z];

            for (int i = z; i < arr.length ; i++) {
                count++;
                if(arr[i]<min){
                    min=arr[i];
                    minIndex=i;
                }
            }

            if(minIndex!=-1){
                arr[minIndex]=arr[z];
                arr[z]=min;
            }
        }
        System.out.printf("运行了%d次",count);
    }

    public static void selectSortAg(int[] arr){
        int count=0;
        for (int z = 0; z < arr.length; z++) {
            int minIndex=z;
            int min=arr[z];
            for (int i = z; i < arr.length ; i++) {
                count++;
                if(arr[i]<min){
                    min=arr[i];
                    minIndex=i;
                }
            }
            arr[minIndex]=arr[z];
            arr[z]=min;
        }
        System.out.printf("Ag运行了%d次",count);

    }

}
