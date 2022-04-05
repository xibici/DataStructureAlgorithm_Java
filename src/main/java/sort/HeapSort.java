package sort;

import java.util.Arrays;

public class HeapSort {




    public static void main(String[] args) {
        //doHeapSort();
        test1();
    }

    private static void test1(){
        int[] arr={4,6,8,5,9,2,3};
        doHeapSort(arr);
    }
    private static void doHeapSort() {
        int[] arr={4,6,8,5,9};
        doHeapSort(arr);
    }

    private static void doHeapSort(int[] arr) {
        //顺序存储
        int index=0;
        System.out.println("初始:"+Arrays.toString(arr));

        for (int i = arr.length/2-1; i >=0 ; i--) {
            index++;
            adjustHeap(arr,i, arr.length);
            System.out.printf("第%d次:"+Arrays.toString(arr)+"\n",index);
        }


        //然后最大值往倒数的顺序排
        for (int i = arr.length-1; i >0; i--) {
            int tmp=arr[i];
            arr[i]=arr[0];
            arr[0]=tmp;

            //从底调整 大顶堆
            adjustHeap(arr,0,i);



        }

        System.out.printf("res:"+Arrays.toString(arr));


    }

    private static void doHeapSortProto(int[] arr) {
        //顺序存储
        System.out.println("原来:"+Arrays.toString(arr));
        //找最后一个非叶子节点排序 然后找最后第二个
        adjustHeap(arr,1, arr.length);
        //第一轮结果:{ 4,9,8,5,6}
        System.out.println("1:"+Arrays.toString(arr));
        adjustHeap(arr,0, arr.length);
        //第二轮结果:{9,6,8,5,4}   变成大顶堆
        System.out.println("2:"+Arrays.toString(arr));
    }



    /*
    * arr 排序数组
    * i是非叶子节点在数组中的索引 下标
    * length表示多少个元素继续调整 lenth逐渐减少
    * */
    private static void adjustHeap(int arr[],int i,int length) {
        //拿到目标值 用于保存
        int tmp=arr[i];

        //然后换  目标是该节点的左右两个子节点
        //小于防止越界
        //因为比较的时候k已经变大了 再来一次2k+1 其实就是下一个子树的意思 直接越界
        for (int k = 2*i+1; k < length; k=2*k+1) {
            //这里需要加一个条件
            if(k+1<length&&arr[k]<arr[k+1]){
                k++;
            }
            //i与k换位
            //第二次时,有一段时间是 0 1下标都是9  而保存原本0下标的是tmp 一直没有变 用来保存和后续子树比较
            //之后tmp的4因为比子树小 换到子树下面   而逻辑是跟左右子树更大的那个换 所以是6换到了最初9的位置  也就是临时看做4的位置
            if(tmp<arr[k]){
                arr[i]=arr[k];
                i=k;
                //i位置与k互换 当前与子树的互换
            }else {
                break;//没有换位置就直接跑
            }
        }
        //这里是转移到子树位置之后 i(变成了k)对应的下标的值
        arr[i]=tmp;

    }



}
