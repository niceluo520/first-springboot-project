package com.test.firstspringbootproject.api.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 对Swagger2的配置信息
 *
 * @author wendell
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.firstspringbootproject.sys.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //配置在线文档的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，https://me.csdn.net/blog/miachen520")
                .termsOfServiceUrl("https://me.csdn.net/blog/miachen520")
                .version("1.0")
                .build();
    }


   /* @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.firstspringbootproject.sys.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder().title("SpringBoot整合Swagger")
                                .description("SpringBoot整合Swagger，详细信息......")
                                .version("9.0")
                                .contact(new Contact("啊啊啊啊","blog.csdn.net","aaa@gmail.com"))
                                .license("The Apache License")
                                .licenseUrl("http://www.baidu.com")
                                .build());
    }*/
}
