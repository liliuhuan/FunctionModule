package com.bolooo.rsaandaes;

/**
 * Author :李刘欢
 * DATA : 2017-12-05
 * DES :
 */

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES
 * Android端的加密思路需要4步：
 * 1.生成AES密钥；
 * 2.使用RSA公钥加密刚刚生成的AES密钥；
 * 3.再使用第1步生成的AES密钥，通过AES加密需要提交给服务端的数据；
 * 4.将第2与第3生成的内容传给服务端。

 * JAVA服务端的解密思路只需3步：
 * 1.获取到客户端传过来的AES密钥密文和内容密文；
 * 2.使用RSA私钥解密从客户端拿到的AES密钥密文；
 * 3.再使用第2步解密出来的明文密钥，通过AES解密内容的密文。
 */
public class AES {
    // /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/ECB/PKCS5Padding";
    // private static final String CipherMode = "AES";

    /**
     * 生成一个AES密钥对象
     *
     * @return
     */
    public static SecretKeySpec generateKey() {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom());
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成一个AES密钥字符串
     *
     * @return
     */
    public static String generateKeyString() {
        return byte2hex(generateKey().getEncoded());
    }

    /**
     * 加密字节数据
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] content, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过byte[]类型的密钥加密String
     *
     * @param content
     * @param key
     * @return 16进制密文字符串
     */
    public static String encrypt(String content, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] data = cipher.doFinal(content.getBytes("UTF-8"));
            String result = byte2hex(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过String类型的密钥加密String
     *
     * @param content
     * @param key
     * @return 16进制密文字符串
     */
    public static String encrypt(String content, String key) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encrypt(data, new SecretKeySpec(hex2byte(key), "AES").getEncoded());
        String result = byte2hex(data);
        return result;
    }

    /**
     * 通过byte[]类型的密钥解密byte[]
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] content, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过String类型的密钥 解密String类型的密文
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        byte[] data = null;
        try {
            data = hex2byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, hex2byte(key));
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过byte[]类型的密钥 解密String类型的密文
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] data = cipher.doFinal(hex2byte(content));
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节数组转成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) { // 一个字节的数，
        StringBuffer sb = new StringBuffer(b.length * 2);
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }

    /**
     * 将hex字符串转换成字节数组
     *
     * @param inputString
     * @return
     */
    private static byte[] hex2byte(String inputString) {
        if (inputString == null || inputString.length() < 2) {
            return new byte[0];
        }
        inputString = inputString.toLowerCase();
        int l = inputString.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = inputString.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }
}