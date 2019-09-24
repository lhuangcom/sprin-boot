package com.lhuang.testparse.encrypt;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * RSA 加密算法是目前最有影响力的 公钥加密算法，并且被普遍认为是目前 最优秀的公钥方案 之一。
 * RSA 是第一个能同时用于 加密 和 数字签名 的算法，它能够 抵抗 到目前为止已知的 所有密码攻击，
 * 已被 ISO 推荐为公钥数据加密标准。
 * @author LHuang
 * @since 2019/4/25
 */
public class RSAUtils {

    private  RSAPublicKey rsaPublicKey;
    private  RSAPrivateCrtKey privateCrtKey;
    private  Cipher encryptCipher;
    private  Cipher decryptCipher;

    private RSAUtils(){
        try {
            initKeyPair();
            encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE,rsaPublicKey);
            decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE,privateCrtKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private  void initKeyPair(){
        try {
            //KeyPairGenerator用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //初始化密钥对生成器，密钥大小为96-1024
            keyPairGenerator.initialize(1024,new SecureRandom());
            //生成一个密钥对，保存在keypair中
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //得到私钥
            privateCrtKey = (RSAPrivateCrtKey) keyPair.getPrivate();
            //得到公钥
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static class RsaInner {
        private static final RSAUtils rsaInstance = new RSAUtils();

    }

    private static RSAUtils getInstance(){
        return RsaInner.rsaInstance;
    }


    public  static String encrypt(String content){
        byte[] contentBytes = content.getBytes();
        try {
            int size = getInstance().rsaPublicKey.getModulus().bitLength() / 8 - 11;
            ByteArrayOutputStream baos = new ByteArrayOutputStream((contentBytes.length + size - 1) / size * (size + 11));
            int left = 0;
            for (int i = 0; i < contentBytes.length; ) {
                left = contentBytes.length - i;
                if (left > size) {
                    getInstance().encryptCipher.update(contentBytes, i, size);
                    i += size;
                } else {
                    getInstance().encryptCipher.update(contentBytes, i, left);
                    i += left;
                }
                baos.write(getInstance().encryptCipher.doFinal());
            }
            return  Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  static String decrypt(String content){
        byte[] bytes = Base64.getDecoder().decode(content);
        try {
            int size = getInstance().privateCrtKey.getModulus().bitLength() / 8;
            ByteArrayOutputStream baos = new ByteArrayOutputStream((bytes.length + size - 12) / (size - 11) * size);
            int left = 0;
            for (int i = 0; i < bytes.length; ) {
                left = bytes.length - i;
                if (left > size) {
                    getInstance().decryptCipher.update(bytes, i, size);
                    i += size;
                } else {
                    getInstance().decryptCipher.update(bytes, i, left);
                    i += left;
                }
                baos.write(getInstance().decryptCipher.doFinal());
            }
            return new String(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    public static String sign(String content){
        byte[] bytes = {};
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(getInstance().privateCrtKey);
            signature.update(content.getBytes());
            bytes = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bytes);

    }

    public static boolean verify(String signatureData,String content){

        boolean result = false;
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(getInstance().rsaPublicKey);
            signature.update(content.getBytes());
            result = signature.verify(Base64.getDecoder().decode(signatureData));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }

        return result;


    }









}
