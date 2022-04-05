package again.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,7, 8, 10, 89,111,112,113,115,116,222,333,666,787,989, 1000, 1234};
        int maxSize = 20;
        int res = fibSearchAg(1234,arr, fib(maxSize));
        System.out.println(res);

    }

    /**
     * 做一个maxSize长度的斐波那契数组
     *
     * @param maxSize 斐波那契长度
     * @return
     */
    public static int[] fib(int maxSize) {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    //需要查的value
    public static int fibSearch(int[] arr,int[] f, int value) {

        int low=0;
        int mid=0;
        //下标
        int high=arr.length-1;
        //获得长度
        //寻找可以做黄金分割的k的点 k从0开始
        int k=0;
        //公式是做一个长度f[k]-1的数组  目的是为了凑公式f[k]-1 =(f[k-1]-1)+(f[k-2]-1) +1
        // 其中公式中+1 表示的是mid这个点  如果是直接用斐波那契数列
        // 以公式 f[k]=f[k-1]+f[k-2] 来的话只能分成左右两个数列 而没有一个中间点用来分开左右两边遍历
        while (f[k]-1 <arr.length){//等于的时候就可以了吧
            k++;
        }
        //找到一个可以构建的斐波那契数值的下标了

        //得到k 可能数组长度不够f[k]-1个 那就构建
        int[] tmp=Arrays.copyOf(arr, f[k]-1);
        //如果不够 就用最大值对多余的补
        for (int i = arr.length; i < tmp.length; i++) {
            tmp[i]=arr[arr.length-1];
        }
        System.out.println(Arrays.toString(tmp));


        while (low<=high){
            mid=low+f[k-1]-1;

            if(value==arr[mid]){
                if(mid<=arr.length-1){
                    return mid;
                }else {
                    return high;
                }

            }else if(value<=arr[mid]){
                k--;
                high=mid-1;
            }else {
                k=k-2;
                low=mid+1;
            }
        }

        return -1;
    }

    public static int fibSearchAg(int value,int[] arr,int[] f){
        int low=0;
        int high=arr.length-1;
        int mid=0;
        //先做出arr数组对应的斐波那契的值 用来扩充长度
        int k=0;
        while (f[k]-1<= arr.length){
            k++;
        }

        //直接扩充 不管是否长度f[k]-1 即需要的长度是否比原来数组长  直接做成f[k] - 1长度
        int[] tmp = Arrays.copyOf(arr, f[k] - 1);
        //并将扩充内容填补   用原来arr的最大值补
        //从数组长度即 原来最大下标的下一个扩充
        for (int i = arr.length; i < tmp.length; i++) {
            tmp[i]=arr[arr.length-1];
        }
        //扩充完毕 进行查找操作 可以画图理解
        //high要赋值不然直接跳了
        while (low<=high){
            mid=low+f[k-1]-1;
            if(value==tmp[mid]){
                if(mid<arr.length){
                    return mid;
                }else {
                    //return mid; 不能返回mid 因为mid是针对tmp数组的
                    // 可能返回arr数组结果的话会越界 比如说mid是在扩充后的部分 目标值下标远远大于原来数组的最大下标
                    //而出现这种情况的话 说明目标值就是扩容后的值,而扩容的值就是原来数组最大值 下标最大的值
                    return arr.length-1;
                }
            }else if(value<tmp[mid]){
                //改变原来k长度 f[k]-1缩小范围
                //左边是大的部分 是f[k-1]
                k--;
                high=mid-1;
            }else {
                k=k-2;
                low=mid+1;
            }
        }

        //没找到的时候返回-1
        return -1;
    }

}