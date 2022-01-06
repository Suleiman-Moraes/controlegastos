package br.com.smtech.controlegastos.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SuppressWarnings("deprecation")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String securitySchemaOAuth2 = "oauth2schema";
    public static final String authorizationScopeGlobal = "global";
    public static final String authorizationScopeGlobalDesc = "accessEverything";

    @Value("api.security.host")
    public String host = "http://localhost:8080";

    @Bean
    public Docket api() {
        String swaggerToken = "Bearer ";
        return new Docket(DocumentationType.SWAGGER_2).enable(Boolean.TRUE).select()
                .apis(RequestHandlerSelectors
                        .basePackage("br.com.smtech.controlegastos.api.controller"))
                .paths(PathSelectors.any()).build();
                // .securityContexts(Collections.singletonList(securityContext()))
                // .securitySchemes(Arrays.asList(securitySchema())).apiInfo(apiInfo())
                // .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                //         .name("Authorization").modelRef(new ModelRef("string"))
                //         .parameterType("header").required(false).hidden(true)
                //         .defaultValue(swaggerToken).build()));
    }

    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

    //     registry.addResourceHandler("/webjars/**")
    //             .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    // @Bean
    // public SecurityScheme apiKey() {
    //     return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
    // }

    // @Bean
    // public SecurityScheme apiCookieKey() {
    //     return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
    // }

    // private OAuth securitySchema() {

    //     List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
    //     authorizationScopeList.add(new AuthorizationScope("read", "read all"));
    //     authorizationScopeList.add(new AuthorizationScope("write", "access all"));

    //     List<GrantType> grantTypes = new ArrayList<>();
    //     GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant("/oauth/token");
    //     grantTypes.add(passwordCredentialsGrant);

    //     return new OAuth("oauth2", authorizationScopeList, grantTypes);
    // }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    // @Bean
    // public SecurityConfiguration security() {
    //     return new SecurityConfiguration("angular", "@ngul@r0", "", "", "Bearer access token",
    //             ApiKeyVehicle.HEADER, HttpHeaders.AUTHORIZATION, "");
    // }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API").description("\"Web Service\"")
                .version("1.0.1").termsOfServiceUrl("www.google.com")
                .contact(new Contact("Suleiman Alves de Moraes", "https://suleiman-moraes.github.io/",
                        "suleimanmoraes@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"").build();
    }
}
