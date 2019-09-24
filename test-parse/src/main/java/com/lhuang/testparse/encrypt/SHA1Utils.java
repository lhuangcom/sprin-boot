package com.lhuang.testparse.encrypt;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author LHuang
 * @since 2019/4/25
 */
public class SHA1Utils {
    /**
     * @Description: 对字符串进行md5加密
     * SHA1 比 MD5 的 安全性更强。对于长度小于 2 ^ 64 位的消息，
     * SHA1 会产生一个 160 位的 消息摘要。
     * 基于 MD5、SHA1 的信息摘要特性以及 不可逆 (一般而言)，
     * 可以被应用在检查 文件完整性 以及 数字签名 等场景。
     */
    public static String getSHA1Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("SHA1");
        String newstr = Base64.getEncoder().encodeToString(md5.digest(strValue.getBytes()));
        return newstr;
    }
}
