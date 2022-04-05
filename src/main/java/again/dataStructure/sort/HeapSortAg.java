package again.dataStructure.sort;

import java.util.Arrays;

public class HeapSortAg {

    public static void main(String[] args) {

        int[] arr={4,6,8,5,9};

        //构建大顶堆的方法
        System.out.println(Arrays.toString(arr));
        for (int i =arr.length/2-1; i >=0 ; i--) {
            //初次是全数组
            sortBigHeap(arr,i,arr.length);
        }

        //第一次做成大顶堆之后 真正开始排序
        int tran;
        for (int i = arr.length-1; i >=0; i--) {
            tran=arr[0];
            arr[0]=arr[i];
            arr[i]=tran;

            sortBigHeap(arr,0,i);
            //误区2 是从0开始的
            /*int lastOne=0;
            for ( lastOne =(i-1)/2-1; i >=0 ; i--) {
                //初次是全数组
                sortBigHeap(arr,lastOne,i);
            }
            if(lastOne<0){
                break;
            }*/

        }



        System.out.println(Arrays.toString(arr));



    }

    /**
     * @param arr
     * @param i 选择一个点进行对子树的比较和值替换 大顶堆
     * @param length 每次需要排序的 后面沉入底的时候要用
     */
    public static void sortBigHeap(int[] arr,int i,int length){
        int tmp=arr[i];
        for (int k = 2*i+1; k < length; k=2*k+1) {

            if(k+1 <length && arr[k]<arr[k+1]){
                k++;
            }

            if(tmp<arr[k]){
                arr[i]=arr[k];
                i=k;
            }else {
                break;//1
            }
        }
        //这里的i与最初的i不一样 i已经k代替了
        arr[i]=tmp;

    }

}
