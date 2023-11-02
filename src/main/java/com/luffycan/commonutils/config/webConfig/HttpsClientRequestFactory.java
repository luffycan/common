package com.luffycan.commonutils.config.webConfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.net.HttpURLConnection;
import java.security.KeyStore;


@Slf4j
public class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {


    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
        try {
            // http协议
            //throw new RuntimeException("An instance of HttpsURLConnection is expected");
            if (!(connection instanceof HttpsURLConnection)) {
                super.prepareConnection(connection, httpMethod);
            }
            // https协议，修改协议版本
            if (connection instanceof HttpsURLConnection) {
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                // 信任任何链接
                TrustStrategy anyTrustStrategy = (x509Certificates, s) -> true;
                SSLContext ctx = SSLContexts.custom().setProtocol("TLS").loadTrustMaterial(trustStore, anyTrustStrategy).build();
                ((HttpsURLConnection) connection).setSSLSocketFactory(ctx.getSocketFactory());
                HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
                super.prepareConnection(httpsConnection, httpMethod);
            }
        } catch (Exception e) {
            log.error("初始化https失败:", e);
        }
    }
}
