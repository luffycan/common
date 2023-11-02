package com.luffycan.commonutils.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES加解密工具
 * @author : luffy
 * @date: 2023/10/26 8:55
 */
public class AESEncryptUtils {
    /**
     * AES算法
     */
    private AESEncryptUtils(){
        throw new UnsupportedOperationException();
    }
    private static final String AES_ALG = "AES";
    private static final String AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";
    private static final byte[] AES_IV = initIv(AES_CBC_PCK_ALG);
    public static String encrypt(String content, String key, String charset) {
        return encrypt(content, key, null, charset);
    }
    public static String decrypt(String content, String key, String charset) {
        return decrypt(content, key, null, charset);
    }
    public static String decrypt(String content, String key, String iv, String charset) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec ivps;
            if (iv != null && !iv.isEmpty()) {
                byte[] aesIv = initIv(iv);
                ivps = new IvParameterSpec(aesIv);
            } else {
                ivps = new IvParameterSpec(AES_IV);
            }
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), AES_ALG), ivps);
            byte[] cleanBytes = cipher.doFinal(Base64.getDecoder().decode(content.getBytes()));
            return new String(cleanBytes, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     * @param content 加密内容
     * @param key 加密使用到的key 必须为16,24,32个字节，对应应128,192,256个字节数组
     * @param iv
     * @param charset 编码格式
     * @return
     */
    public static String encrypt(String content, String key, String iv, String charset) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec ivps;
            if (iv != null && !iv.isEmpty()) {
                byte[] aesIv = initIv(iv);
                ivps = new IvParameterSpec(aesIv);
            } else {
                ivps = new IvParameterSpec(AES_IV);
            }
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), AES_ALG), ivps);
            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            return new String(Base64.getEncoder().encode(encryptBytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     */
    private static byte[] initIv(String fullAlg) {

        try {
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();
            return new byte[blockSize];
        } catch (Exception e) {
            int blockSize = 16;
            return new byte[blockSize];
        }
    }
}
