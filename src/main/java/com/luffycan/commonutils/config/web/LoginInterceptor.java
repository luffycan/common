package com.luffycan.commonutils.config.web;


import com.luffycan.commonutils.common.TokenHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private final Set<String> requestSet = new CopyOnWriteArraySet<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestSet.add(request.getRequestURI());
        logger.info("requestURI:{}", request.getRequestURI());
        String authorize = request.getHeader("Authorize");
        logger.info("authorize:{}", authorize);
        if (StringUtils.hasText(authorize)) {
            TokenHolder.set(authorize);
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoNeedsLogin methodAnnotation = handlerMethod.getMethodAnnotation(NoNeedsLogin.class);
            if (methodAnnotation == null || !methodAnnotation.value()) {
                logger.error("请求地址:{}被拦截", request.getRequestURI());
//                ResponseResult notLogin = ResponseResult.fail("not login!");
//                response.getWriter().write(JsonUtils.objectToJson(notLogin));
                return false;
            }
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenHolder.remove();
        logger.info("请求的路径有！！！！！{}", requestSet);
    }
}
