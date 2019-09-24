package com.lhuang.testparse.encrypt;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author LHuang
 * @since 2019/4/25
 */
public class ByteTransformUtil {

    public static String byteToHexStringByPrimitive(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder(bytes.length*2);
        int j = bytes.length;
        for (int i =0 ; i < j; i++){
            int value = bytes[i] & 0xFF;
            if (value < 16){
                stringBuilder.append("0");
            }
            stringBuilder.append(Integer.toHexString(value));
        }
        return stringBuilder.toString();
    }

    public static String byteToHexStringByCustomize(byte[] bytes) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        int j = bytes.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = bytes[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return  new String(str);
    }

    public static void hexToString(String str){
        String[] strChars = str.split("-");

        StringBuilder stringBuilder = new StringBuilder();
        int length = strChars.length;
        for (int i = 0;i <length;i++ ){
            Character value = (char) Integer.parseInt(strChars[i],16);
            stringBuilder.append(value);
        }
        System.out.println(stringBuilder.toString());
    }

    public static String aesDecryptByBytes(String encryptPwd) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //必须要16位
        String decryptKey = "abcdefgabcdefg12";

        byte[] bytes = new BASE64Decoder().decodeBuffer(encryptPwd);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(bytes);
        return new String(decryptBytes);
    }


}
