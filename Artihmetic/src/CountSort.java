/**
 * 计数排序
 * @author LHuang
 * @since 2019/6/18
 */
public class CountSort {


    public static void main(String[] args) {

        int a[] = {1,2,6,4,5,7,3,8,2,6};
        countSort(a);
        /*for (int num : a) {
            System.out.println(num);
        }
*/
    }


    public static void countSort(int a[]){

        //先找出桶的范围
        int length = a.length;
        if (length == 0){
            return;
        }
        int max = a[0];
        for (int i = 1; i < length; i++ ){
            max = a[i] > max ? a[i] : max;
        }

        //初始化桶数组
        int[] bucket = new int[max+1];
        for (int i = 0; i < max + 1;i++){
            bucket[i] = 0;
        }

        //将每个桶下标的值的个数存进数组里；
        for (int num : a){
            bucket[num]++;
        }
        //修改数组中每个位置存储的值为小于或等于改下标的数量
        for (int i = 1; i < max + 1;i++){
            bucket[i] = bucket[i]+bucket[i-1];
        }

        int sort[] = new int[length];
        //排序数组思路：查询a[i]的值，将其作为下标查询到bucket的值，
        // 此时的值作为sort下标并出入a[i],完成后sort就是排序后的数组；
        for (int i = length-1; i >= 0 ;i--) {
            sort[--bucket[a[i]]] = a[i];
        }
        for (int num : sort) {
            System.out.println(num);
        }

    }
}
