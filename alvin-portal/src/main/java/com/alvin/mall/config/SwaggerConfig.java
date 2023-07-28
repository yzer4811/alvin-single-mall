package com.alvin.mall.config;

import com.alvin.mall.common.config.BaseSwaggerConfig;
import com.alvin.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.alvin.mall.modules")
                .title("商城基础版项目前台管理系统")
                .description("alvin_mall项目前台管理接口文档")
                .contactName("Alvin")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
