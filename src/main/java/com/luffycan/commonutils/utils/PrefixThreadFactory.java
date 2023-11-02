package com.luffycan.commonutils.utils;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : luffy
 * @date: 2023/11/02 15:41
 */
public class PrefixThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNum = new AtomicInteger();
    private final String prefix;

    public PrefixThreadFactory(String prefix) {
        this.prefix = prefix;
    }
    @Override
    public Thread newThread(Runnable r) {
        Objects.requireNonNull(r);
        Thread t = new Thread(r, prefix + "-thread-" + threadNum.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
