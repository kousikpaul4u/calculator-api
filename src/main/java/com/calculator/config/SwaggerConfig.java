package com.calculator.config;

import com.calculator.constance.StatusConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.calculator"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    UiConfiguration uiConfig() {

        return UiConfigurationBuilder.builder()
                .validatorUrl("")
                .build();

    }

    private ApiInfo apiInfo() {

        StatusConstants.HttpConstants[] httpConstants = StatusConstants.HttpConstants.values();

        String alphaHostname = "Alpha: alpha-calculator.com/calculator-svc";
        String stagingHostname = "Staging: staging-calculator.com/calculator-svc";
        String productionHostname = "Production: calculator.com/calculator-svc";
        String newLine = System.lineSeparator();

        String responseCodes = "Response codes:" + newLine + Arrays.stream(httpConstants).map(httpConstant -> "[" + httpConstant.getCode() + ", " + httpConstant.getDesc() + "]").collect(Collectors.joining(newLine));

        String apiDescription = alphaHostname + newLine + stagingHostname + newLine + productionHostname + newLine + newLine + responseCodes;

        return new ApiInfo("Calculator Service API", apiDescription, "1.0", "urn:tos", ApiInfo.DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    }

}