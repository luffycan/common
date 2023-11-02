package com.luffycan.commonutils.config.webConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@Slf4j
public class XmWebMvcConfig implements WebMvcConfigurer {


    @Value("${rest-template-connectTimeout}")
    int restTemplateConnectTimeout;

    @Value("${rest-template-readTimeout}")
    int restTemplateReadTimeout;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("**.html", "**.js,**.css,**.ico", "/error");
    }

    @Bean
    public RestTemplate restTemplate(HttpsClientRequestFactory httpsClientRequestFactory) {
        log.info("restTemplateConnectTimeout:{},restTemplateReadTimeout:{}", restTemplateConnectTimeout, restTemplateReadTimeout);
        return new RestTemplate(httpsClientRequestFactory);
    }

    @Bean
    public HttpsClientRequestFactory httpsClientRequestFactory() {
        HttpsClientRequestFactory httpsClientRequestFactory = new HttpsClientRequestFactory();
        httpsClientRequestFactory.setConnectTimeout(restTemplateConnectTimeout);
        httpsClientRequestFactory.setReadTimeout(restTemplateReadTimeout);
        return httpsClientRequestFactory;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}
