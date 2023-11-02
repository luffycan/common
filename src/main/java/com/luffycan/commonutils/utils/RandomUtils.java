package com.luffycan.commonutils.utils;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * Author: luffy
 * Time: 2023/10/24 18:08
 */
public class RandomUtils {

    private RandomUtils() {
        throw new UnsupportedOperationException();
    }

    private final static Random RANDOM = new Random();

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static int randomIntBetween(int head, int tail) {
        if (head < 0 || tail < 0 || head >= tail) {
            throw new IllegalArgumentException();
        }
        return RANDOM.nextInt(tail - head) + head;
    }

    public static void main(String[] args) {

        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int j = 0; j < 10000; j++) {
            int i = randomIntBetween(10, 20);
            integerIntegerHashMap.put(i,integerIntegerHashMap.getOrDefault(i,0)+1);
//            System.out.println(randomIntBetween(10,20));
        }
        System.out.println(integerIntegerHashMap);
    }

}
