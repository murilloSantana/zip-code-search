package com.zipcode.zipcodesearch.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
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

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfo("Zip Code Search Documentation",
                "https://github.com/murilloSantana/zip-code-search",
                projectVersion, null,
                null, "MIT", "https://opensource.org/licenses/MIT",
                new ArrayList<VendorExtension>());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zipcode.zipcodesearch.address.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo)
                .enableUrlTemplating(true);
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder
                .builder()
                .deepLinking(true)
                .defaultModelExpandDepth(2)
                .defaultModelsExpandDepth(2)
                .defaultModelRendering(ModelRendering.MODEL)
                .displayRequestDuration(false)
                .filter(false)
                .build();
    }
}