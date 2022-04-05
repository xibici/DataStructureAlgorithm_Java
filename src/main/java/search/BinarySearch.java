package search;

import java.util.ArrayList;

public class BinarySearch {



    public static void main(String[] args) {
        //必须要从小到大排!!!!!!!!!!!  有顺序的排序
        //错误演示: int[] arr={11,61,1,3,94,64156,131513,51,515,3156,1561,561,561,56,1561,5,864,85,15,156,5,15};
        int[] arr={1,2,3,4,4,4,4,4,4,5,8,9};

        int a1=4;
        System.out.println(binarySearch(arr,0,arr.length-1,a1));
    }

    private static ArrayList<Integer> binarySearch(int[] arr,int left,int right,int findVal) {

        if(left>right){
            //return -1;
            return new ArrayList<Integer>();
        }

        int mid=(left+right)/2;
        int midVal=arr[mid];

        //  123456  mid  0+5    /2
        //123
        if (findVal<midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }else if(findVal>midVal){
            return binarySearch(arr,mid+1,right,findVal);
        }else {

            //假如是连续的
            //  因为是有顺序的排序 所以  不会出现连续出现几个相同的而且中间夹着一个不同的
            //  {100,101,100} 比如这种情况是不可能出现的

            ArrayList<Integer> arrayList=new ArrayList();

            //向前看看有没有相同的
            int tmp=mid-1;
            while(true){
                if(tmp<0||arr[tmp]!=findVal){
                    break;
                }
                //保证顺序正
                arrayList.add(0,tmp);
                tmp--;
            }

            //前面查完了再加中间找这个
            arrayList.add(mid);
            //前面找完了找找后面 重置位置
            tmp=mid+1;
            while(true){
                if(tmp>arr.length-1||arr[tmp]!=findVal){
                    break;
                }
                arrayList.add(tmp);
                tmp++;
            }


            return arrayList;
        }


    }

}
