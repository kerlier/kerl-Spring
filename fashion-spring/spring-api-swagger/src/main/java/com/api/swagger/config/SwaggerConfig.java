package com.api.swagger.config;

import com.api.swagger.version.ApiVersion;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


//    @Bean
//    public Docket swaggerAll() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("api-1.0")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.api.swagger.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(new ApiInfoBuilder().version("1.0").title("Person API").description("Documentation Person API v1.0").build());
//    }

    @Bean
    public Docket swaggerPersonApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-1.0")
                .select()
                .apis(input -> {
                    ApiVersion apiVersion = input.getHandlerMethod().getMethodAnnotation(ApiVersion.class);
                    if(apiVersion!=null&& Arrays.asList(apiVersion.group()).contains("v1.0")){
                        return true;
                    }
                    return false;
                })
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Person API").description("Documentation Person API v1.0").build());
    }

    @Bean
    public Docket swaggerPersonApi11() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-1.1")
                .select()
                .apis(input -> {
                    ApiVersion apiVersion = input.getHandlerMethod().getMethodAnnotation(ApiVersion.class);
                    if(apiVersion!=null&& Arrays.asList(apiVersion.group()).contains("v1.1")){
                        return true;
                    }
                    return false;
                })
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.1").title("Person API").description("Documentation Person API v1.1").build());
    }

}
