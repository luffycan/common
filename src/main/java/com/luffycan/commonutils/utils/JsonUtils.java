//package com.luffycan.commonutils.utils;
//
//import com.google.gson.Gson;
//
///**
// * Author: luffy
// * Time: 2023/10/24 13:51
// */
//public class JsonUtils {
//
//    private JsonUtils() {
//    }
//
//    public static String objectToJson(Object obj) {
//        Gson gson = new Gson();
//        return gson.toJson(obj);
//    }
//
//    public static <T> T jsonToObject(String json, Class<T> clazz) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, clazz);
//    }
//}
