package again.sort;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.util.Arrays;

public class QuickSortAg {

    public static void main(String[] args) throws IOException {
        //int[] arr={-9,78,0,23,-567,70};
        //int[] arr={1,2,3,4,0,4,5};
        int[] arr=new int[15000];
       // int[] arr={-9,78,0,0,23,-567,-70};
        //int[] arr={-9,78,1,0,0,23,-567,-70};
        
        File f=new File("C:/Users/xibici/OneDrive/桌面/test/大文件排序/大文件排序排序后.txt");
        BufferedReader bf = new BufferedReader(new FileReader(f));
        String line="";
        int index=0;
        while((line=bf.readLine())!=null){
            int i = Integer.parseInt(line);
            arr[index++]=i;
        }
        
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr,int left,int right){
        //以中间点为比较对象 把左边大的移到右边
        //因为不想改变原先的left和right所以用l r新变量代替
        int l=left;
        int r=right;
        int privot=arr[(l+r)/2];

        while (l<r){
            while (arr[l]<privot){//等于的情况在下面
                l++;
            }
            while (arr[r]>privot){//等于的情况在下面
                r--;
            }
            if(l>=r){//等于的时候直接break了
                break;
            }
            //两者交换
            int tmp=arr[r];
            arr[r]=arr[l];
            arr[l]=tmp;

            //当数组的中间数是a 且除了中间还有别的地方有a的时候就会导致那个a与这个a一直循环交换位置
            //陷入死循环 这种情况 只能让一个变位
            if(arr[l]==privot){
                r--;
            }
            if(arr[r]==privot){
                l++;
            }


        }


        //等于的时候溢出了  停下来的这个点的时候保证了左边比这个点小 右边比这个点大
        if(l==r){
            l++;
            r--;
        }


        if(left<r){
            quickSort(arr,left,r);
        }

        if(l<right){
            quickSort(arr,l,right);

        }


    }

}
