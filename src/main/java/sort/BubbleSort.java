package sort;

public class BubbleSort {

    public static void main(String[] args) {

        //int arr[]={ 3,9,-1,10,-2};
        int arr[]={ 3,9,-1,10,-2,15,48,97,1,0,4,1651};

        //int mode=2;
        int mode=1;

        for (int i = 0; i <arr.length-1 ; i++) {

            for (int j = 0; j < arr.length - i-1; j++) {
                //逆序 从大到小
                switch (mode){
                    case 1:
                        if (arr[j]>arr[j+1]){
                            int tmp=arr[j];
                            arr[j]=arr[j+1];
                            arr[j+1]=tmp;
                        }
                        break;
                    case 2:
                        //顺序 从小到大
                        if (arr[j]<arr[j+1]){
                            int tmp=arr[j];
                            arr[j]=arr[j+1];
                            arr[j+1]=tmp;
                        }
                        break;
                }
            }
        }

        //System.out.println(arr);
        for (int o:arr){
            System.out.printf(o+" ");
        }
    }
}
