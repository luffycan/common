package com.luffycan.commonutils.common;

/**
 * @author : luffy
 * @date: : 2023/10/26 10:50
 */
public class TokenHolder {


    private static final ThreadLocal<String> TOKEN_THREADLOCAL = new ThreadLocal<>();

    public static String get(){
        return TOKEN_THREADLOCAL.get();
    }

    public static void set(String token){
        TOKEN_THREADLOCAL.set(token);
    }

    public static void remove(){
        TOKEN_THREADLOCAL.remove();
    }

}
