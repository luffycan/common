package com.luffycan.commonutils.utils;

import com.luffycan.commonutils.common.exception.BaseRuntimeException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author :luffy
 * hash算法中的SHA
 */
public class HashAlgorithmUtils {

    private static final String SHA_1 = "SHA-1";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_512 = "SHA-512";
    private static final String MD5 = "MD5";


    public static String getSHA1(String input) {
        return hash(input, SHA_1);
    }

    public static String getSHA256(String input) {
        return hash(input, SHA_256);
    }

    public static String getSHA512(String input) {
        return hash(input, SHA_512);
    }


    public static String getMD5(String input) {
        return hash(input, MD5);
    }

    private static String hash(String input, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] hash = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new BaseRuntimeException(e);
        }
    }
}
