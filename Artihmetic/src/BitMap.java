import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author LHuang
 * @since 2019/6/25
 */
public class BitMap { // Java 中 char 类型占 16bit，也即是 2 个字节
    private char[] bytes;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits/16+1];
    }

    public void set(int k) {
        if (k > nbits) {
            return;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nbits) {
            return false;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean result = dateFormat.parse("2019-07-09 09:27:30").after(new Date());
        System.out.println(result);

       /* String dateForm ="\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        boolean result = Pattern.matches(dateForm,"2018-05-12 12:25:43");
        System.out.println(result);*/
        //System.out.println("".hashCode());


        //BitSet bitSet = new BitSet();

       /*List<Integer> list = new ArrayList();
        long start = System.currentTimeMillis();
        System.out.println();
       for (int i = 0;  i < 100000000;i++){
           bitSet.set(i);
           list.add(i);
       }*/

    }
}

