package com.lhuang.testparse.encrypt;

import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author LHuang
 * @since 2019/4/25
 * HMAC 是密钥相关的哈希运算消息认证码（Hash-based Message Authentication Code），HMAC 运算利用 哈希算法 (MD5、SHA1 等)，
 * 以 一个密钥 和 一个消息 为输入，生成一个 消息摘要 作为 输出。
 * HMAC 发送方 和 接收方 都有的 key 进行计算，而没有这把 key 的第三方，则是 无法计算 出正确的 散列值的，这样就可以 防止数据被篡改。
 */
public class HMACUtils {

    private HMACUtils(){

        try {
            secretKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
            mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * MAC算法可选以下多种算法
     * HmacMD5/HmacSHA1/HmacSHA256/HmacSHA384/HmacSHA512
     */

    private  SecretKeySpec secretKey;
    private  Mac mac;
    private  final String algorithm = "HmacMD5";
    private  final String key = "123456789";

    public static class MacInner {
       private static final HMACUtils MacInstance = new HMACUtils();
    }

    private static  HMACUtils getInstance(){
        return MacInner.MacInstance;
    }

    public static String encrypt(String content){
        if (StringUtils.isEmpty(content)){
            throw new RuntimeException("验证的数据不对");
        }
        byte[] contentBytes= content.getBytes();
        byte[] contentEncryptBytes = getInstance().mac.doFinal(contentBytes);
        return Base64.getEncoder().encodeToString(contentEncryptBytes);
    }

    /**
     * 验证数据的完整性
     * @param signature
     * @param content
     * @return
     */
    public static Boolean verify(String signature,String content) {
        if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(content)){
            throw new RuntimeException("数据签名或验证的数据不对");
        }
        String result = encrypt(content);
        return result.equals(signature);
    }


    public static void main(String args[]){
        System.out.println(encrypt("lhuang123131"));
    }


}
