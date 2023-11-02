package com.luffycan.commonutils.config.webConfig;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoNeedsLogin {
    boolean value() default true;
}
