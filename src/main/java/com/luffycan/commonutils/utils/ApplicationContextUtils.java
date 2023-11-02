package com.luffycan.commonutils.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author: luffy
 * Time: 2023/10/30 17:36
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBean(beanName, clazz) : null;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBean(clazz) : null;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBeansOfType(clazz) : null;
    }
}
