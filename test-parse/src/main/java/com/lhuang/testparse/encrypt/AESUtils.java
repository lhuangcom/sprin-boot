package com.lhuang.testparse.encrypt;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 加密算法是密码学中的高级加密标准，该加密算法采用 对称分组密码体制，
 * 密钥长度的最少支持为 128 位、 192 位、256 位，分组长度 128 位，
 * 算法应易于各种硬件和软件实现。这种加密算法是美国联邦政府采用的 区块加密标准。
 * @author LHuang
 * @since 2019/4/25
 */
public class AESUtils {

    private  SecretKeySpec secretKey;
    private  IvParameterSpec ivParameterSpec;
    private  final String algorithm = "AES";
    /**
     * 必须是16位
     */
    private  final String key = "1234567890123456";
    /**
     * 必须是16位,使用CBC模式，需要一个向量iv，可增加加密算法的强度
     */
    private  String iv = "1234567890123456";
    private  Cipher encryptCipher;
    private  Cipher decryptCipher;

    private AESUtils(){
        try {
            if (key.length() < 16 || iv.length() < 16){
                throw new RuntimeException("错误的初始密钥");
            }
            if (iv == null){
                iv = MD5Utils.getMD5Str(key);
            }
            secretKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
            ivParameterSpec = new IvParameterSpec(iv.getBytes());
            //算法/模式/补码方式
            encryptCipher = Cipher.getInstance("AES/CFB/NoPadding");
            decryptCipher =  Cipher.getInstance("AES/CFB/NoPadding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            decryptCipher.init(Cipher.DECRYPT_MODE,secretKey,ivParameterSpec);

        } catch ( UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class  AESUtilsInner{

       private static final AESUtils AESInstance= new AESUtils();


    }

    private static AESUtils getInstance(){
        return  AESUtilsInner.AESInstance;
    }

    public  static String encrypt(String content){
        byte[] bytes = {};
        try {
            bytes = getInstance().encryptCipher.doFinal(content.getBytes());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bytes);

    }

    public  static String decrypt(String content){
        byte[] bytes = Base64.getDecoder().decode(content);
        try {
            bytes = getInstance().decryptCipher.doFinal(bytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    public static String randomKey(int size) {
        byte[] result = null;
        try {
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(size, new SecureRandom());
            result = gen.generateKey().getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(result);
    }


}
