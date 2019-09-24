package com.lhuang.testparse.encrypt;

import java.security.MessageDigest;
import java.util.Base64;

public class MD5Utils {

	/**
	 * @Description: 对字符串进行md5加密 
	 */
	public static String getMD5Str(String strValue) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance("MD5");

		String newstr = Base64.getEncoder().encodeToString(md5.digest(strValue.getBytes()));


		//byte[] bytes = md5.digest(strValue.getBytes());
		//String newstr = ByteTransformUtil.byteToHexStringByPrimitive(bytes);
		//String newstr = ByteTransformUtil.byteToHexStringByCustomize(bytes);
		return newstr;
	}



	public static void main(String[] args) {
		try {
			long time = System.currentTimeMillis();
			String md5 = getMD5Str("imooc");
			System.out.println(System.currentTimeMillis()-time);
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
