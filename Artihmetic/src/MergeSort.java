import java.util.HashMap;

/**
 * 归并排序：
 * 如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，
 * 再将排好序的两部分合并在一起
 * @author LHuang
 * @since 2019/6/12
 */
public class MergeSort {

    public static void main(String[] args){

        int a[] = {8,2,6,3};
        mergeSort(a,0,3);
        for (int num : a){
            System.out.println(num);
        }

    }

    public static  void mergeSort(int[] a,int p,int r){


        if (p >= r){
            return ;
        }
        int q = (p+r)/2;

        mergeSort(a, p,q);
        mergeSort(a,q+1,r);
        merge(a,p,r);

    }

    public static void merge(int[] a,int p,int r){


        int q = (p+r)/2;
        int tmp[] = new int[r-p+1];
        int i = p;
        int j = q+1;
        int k = 0;
        //左右两边的数组两两比较，较小的优先存入临时数组，直到一边值取完后
        while (i <= q && j <= r){
            if (a[i] <= a[j]){
                tmp[k] = a[i];
                i++;

            }
            else {
                tmp[k] = a[j];
                j++;

            }
            k++;
        }


        while (i<= q){
            tmp[k++] = a[i++];
        }

        while(j<=r){
            tmp[k++] = a[j++];
        }

        int l = 0;
        int length = tmp.length;
        while (l < length){
            a[p++] = tmp[l++];
        }


    }
}
