package com.luffycan.commonutils.config.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/03 11:43
 */
@SpringBootConfiguration
@SecuritySchemes(value = {
        @SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearer", in = SecuritySchemeIn.HEADER, paramName = "Authorization", scheme = "bearer"),
        @SecurityScheme(type = SecuritySchemeType.OAUTH2, in = SecuritySchemeIn.QUERY, paramName = "token"),
        @SecurityScheme(type = SecuritySchemeType.HTTP, name = "basic", in = SecuritySchemeIn.HEADER,paramName = "Authorization",scheme = "basic")})
@OpenAPIDefinition(info = @Info(title = "小麦便利小程序服务端接口文档", version = "1.0", description = "小麦便利小程序服务端接口文档",
        contact = @Contact(name = "小麦便利", url = "https://www.xiaomai24h.com/product.php", email = "xiaomaibianli@lufff.com")),
        security = @SecurityRequirement(name = "basic")
//        servers = {@Server(url = "https://xiaomai24htest.lufff.com", description = "测试环境地址")
//                , @Server(description = "生产环境地址", url = "https://m.xiaomai24h.com")}
)
public class SwaggerOpenApiAnnotationConfig {

}
