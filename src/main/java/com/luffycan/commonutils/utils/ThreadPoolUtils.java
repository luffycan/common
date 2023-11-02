package com.luffycan.commonutils.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: luffy
 * Time: 2023/10/28 18:29
 */
public class ThreadPoolUtils {



    private static final ThreadPoolExecutor executor;

    static {
        int cpuCount = Runtime.getRuntime().availableProcessors();
        executor = new ThreadPoolExecutor(cpuCount + 1, cpuCount * 2, 60L
                , TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        executor.allowCoreThreadTimeOut(true);

    }

    public static void execute(Runnable runnable) {
        executor.execute(runnable);

    }
}
