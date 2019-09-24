/**
 * 快速排序
 * @author LHuang
 * @since 2019/6/18
 */
public class QuickSort {


    public static void main(String[] args) {

        int a[] = {1,2,6,4,5};
        quickSort(a, 0, 4);
        for (int num : a) {
            System.out.println(num);
        }

    }

    public static void quickSort(int[] a, int p, int r) {

        if (p >= r){
            return;
        }
        int i=partition(a,p,r);
        quickSort(a,p,i-1);
        quickSort(a,i+1,r);



    }

    /**
     * 原地分区函数
     * 分区思路：取一个分区点（pivot）将数据分为未处理分区和已处理分区，
     * 分区点一般取值为数组的最后一个值
     * @param a
     * @param p
     * @param r
     * @return
     */
    public static int partition(int[] a, int p, int r) {

        //使用i，j 2个游标分别指向已处理分区的尾部和整个分区的开头，
        //然后比较j指向的数据和pivot，如果小于pivot且i不等于j，则将a[j]添加到处理区,i指向下一个位置；
        int i = p;
        int j = p;
        int value = a[r];
        for (;j < r;j++){
            if (a[j] < value){
                //如果两者相等，则不要移动，只需下移指针；
                if ( i != j){
                    swap(a,i,j);
                }
                i++;
            }
        }

        //当j指向的最后一个数据并且比较处理完成后，如果i == r，说明整个数组的数据都比pivot大
        //数据就不要再交换位置了
        if ( i != r){
            swap(a,i,r);
        }
        return i;
    }

    public static void swap(int a[],int i,int j){
        int tmp= a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}