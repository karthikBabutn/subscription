package com.subscription.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Config class for Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates a new {@link Docket} for swagger documentation
     *
     * @return {@link Docket} instance
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    /**
     * Method that defines api info for swagger
     *
     * @return instance of {@link ApiInfo}
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Subscription Service")
                .description("Subscription Service is used to create, update subscription details of customers")
                .contact(new Contact("Karthik Babu", null, "tnkarthikbabu@outlook.com"))
                .termsOfServiceUrl(null)
                .version("1.0")
                .build();
    }
}
