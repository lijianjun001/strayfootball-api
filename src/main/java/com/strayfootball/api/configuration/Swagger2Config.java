package com.strayfootball.api.configuration;

import com.strayfootball.api.config.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile("dev")
public class Swagger2Config {


    @Bean
    public Docket createRestApi() {

        List<Parameter> pars = new ArrayList<>();
        pars.add(new ParameterBuilder().name(Constants.AUTHORIZATION).description("令牌").defaultValue("YR3D+mEt8SQSBFNpCoOLiJTh/nZMACMncBFB0C35vJiKKePQv5f8Lg2+LDKinC4wCGDXnVF875F4g9bhFwNpW/jkfuhSde6KmEyamhg/rU9o1JRbqxmKd8bGIHwun/+S").modelRef(new ModelRef("string")).parameterType("header").required(false).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.strayfootball.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .description("api接口文档")
                .version("1.0")
                .build();
    }

}