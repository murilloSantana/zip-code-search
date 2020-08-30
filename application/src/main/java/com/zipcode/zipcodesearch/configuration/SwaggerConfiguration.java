package com.zipcode.zipcodesearch.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${project.version}")
    private String projectVersion;

    @Value("${swagger.document.api.info.title}")
    private String apiInfoTitle;

    @Value("${swagger.document.api.info.description}")
    private String apiInfoDescription;

    @Value("${swagger.document.api.info.license}")
    private String apiInfoLicense;

    @Value("${swagger.document.api.info.license.url}")
    private String apiInfoLicenseUrl;

    @Bean
    public Docket address() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Address")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zipcode.zipcodesearch.address.controller"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(this.apiInfo())
                .enableUrlTemplating(true);
    }

    @Bean
    public Docket actuator() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Metrics and Healthcheck")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zipcode.zipcodesearch.analytics.controller"))
                .build()
                .apiInfo(this.apiInfo())
                .enableUrlTemplating(true);
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiInfoTitle)
                .description(apiInfoDescription)
                .version(projectVersion)
                .license(apiInfoLicense)
                .licenseUrl(apiInfoLicenseUrl)
                .build();
    }

}