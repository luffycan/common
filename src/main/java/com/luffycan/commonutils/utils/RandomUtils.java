package com.luffycan.commonutils.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Author: luffy
 * Time: 2023/10/24 18:08
 */
public class RandomUtils {

    private RandomUtils() {
    }

    private static final Random random = new Random();

    public static String genRanStrAndFillLeftWithZero(int bound) {
        if (bound <= 0) throw new IllegalArgumentException("invalid value: " + bound + "; value should > 0");
        int ran = random.nextInt(bound) + 1;
        int boundLength = String.valueOf(bound).length();
        int ranLength = String.valueOf(ran).length();
        if (boundLength == ranLength) return ran + "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < boundLength - ranLength; i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(ran);
        return stringBuilder.toString();
    }


    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(genRanStrAndFillLeftWithZero(1000));
        }
    }

}
