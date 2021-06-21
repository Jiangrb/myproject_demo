package com.example.myproject.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swagger2Config {

    @Value("${swagger2.enable}")
    private Boolean enable;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // (第一种方式)扫描所有有注解的api，用这种方式更灵活
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // (第二种方式)扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.example.myproject.controller.api"))
                // (第三种方式)扫描所有
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Myproject接口文档")
                .description("Myproject 研究中")
                .termsOfServiceUrl("http://localhost:8081/doc.html")
                .contact(new Contact("Luffy Jiang", null, null))
                .version("1.0")
                .build();
    }


}
