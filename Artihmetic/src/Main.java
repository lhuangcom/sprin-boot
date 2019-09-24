import java.util.*;

public class Main {

    public static void main(String[] args) {
        int a[] = {2,9,4,4,4,3,1,2};

        System.out.println(binarySearch(a,0,a.length-1,4));
        insertSort(a);
        for (int num:a){
            System.out.print(num);
        }
        System.out.println();
        String string = "dshfjksdfsjffs";
        HashMap hashMap = new HashMap<String,String>(16);
        hashMap.put("123",string);
        int h ="12345".hashCode();
        System.out.println(Integer.toBinaryString(-16));
        System.out.println(Integer.toBinaryString(-16 >>> 16));

        System.out.println((h ^ (h >>> 16))&(16-1));
        List arrayList = new LinkedList();
        //insertSort(a);
        System.out.println("Hello World!");

    }


    /**
     * 冒泡排序，a 表示数组，n 表示数组大小
     */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1){
            return;
        }

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j+1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }





    public static void insertSort(int a[]){
        int length = a.length;

        if (length <= 1){
            return;
        }

        for (int i = 1; i < length;i++){
            int value = a[i];
            int j =i-1;
            for (;j >= 0;j--){
                if (value < a[j]){
                    a[j+1] = a[j];
                }
                else {
                    break;
                }
            }
            a[j+1] = value;
        }


    }

    public  static void selectionSort(int a[]){
        int n = a.length;
        if (n <= 1){
            return;
        }

        for (int i = 0; i < n - 1; ++i) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }

    }


    public static int binarySearch(int[] a, int start,int end, int value){



        int mid = (start + end) / 2;

        if (start > end ){
            return -1;
        }

        if (a[mid] == value){
            return mid;
        }
        else if ( value < a[mid]){
            return binarySearch(a,start,mid-1,value);
        }
        else {
            return binarySearch(a,mid+1,end,value);
        }

    }
}
