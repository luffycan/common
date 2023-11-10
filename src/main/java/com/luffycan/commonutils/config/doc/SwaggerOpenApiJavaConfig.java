package com.luffycan.commonutils.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.filters.OpenApiMethodFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/03 13:52
 */
//@SpringBootConfiguration
public class SwaggerOpenApiJavaConfig {

    @Bean
    public OpenAPI openAPI() {

        Contact contact = new Contact()
                .email("xiaomaibianli@lufff.com")
                .url("https://www.xiaomai24h.com/product.php")
                .name("小麦便利");
        Info info = new Info().contact(contact)
                .version("1.0")
                .title("小麦便利小程序服务端接口文档")
                .description("小麦便利小程序服务端接口文档");
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().description("测试环境地址").url("https://xiaomai24htest.lufff.com"));
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setName("Authorization");
        securityScheme.setIn(SecurityScheme.In.HEADER);
        securityScheme.setType(SecurityScheme.Type.HTTP);
        new OpenAPI().schemaRequirement("Authorization",securityScheme);

//        servers.add(new Server().description("生产环境地址").url("ttps://m.xiaomai24h.com"));
        return new OpenAPI().info(info).servers(servers).addSecurityItem(new SecurityRequirement());
    }


    @Bean
    public GroupedOpenApi xmApi() {
        return GroupedOpenApi.builder().group("xmApi")
                .pathsToMatch("/XmApi/openDoor", "/XmApi/queryDeviceAndGoods", "/XmApi/getUserInfo")
                .displayName("小麦便利接口")
                .addOpenApiMethodFilter(new OpenApiMethodFilter() {
                    @Override
                    public boolean isMethodToInclude(Method method) {
                        return false;
                    }
                })
                .build();
    }

    @Bean
    public GroupedOpenApi wxApi(){
        return GroupedOpenApi.builder().group("wxApi")
                .pathsToMatch("/WxApp/jscode2session")
                .displayName("微信接口").build();
    }

    @Bean
    public GroupedOpenApi aliPay(){
        return GroupedOpenApi.builder().group("aliApi")
                .pathsToMatch("/AliApp/Alijscode2session")
                .displayName("支付宝接口").build();
    }


}
