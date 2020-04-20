package nl.ramonpeek.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    /**
     * A method for defining which controllers to use in Swagger.
     * @return a Docket object which contains configuration settings.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("nl.ramonpeek.controllers"))
                .paths(PathSelectors.any()).build().apiInfo(metaData());
    }

    /**
     * A method for changing the metadata of Swagger (mainly information on landing-page).
     * @return an ApiInfo object containing all the set metadata.
     */
    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("WolfPack RESTful API")
                .description("RESTful API for WolfPack.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Ramon Peek", "http://ramonpeek.nl", "ramonpeek@hotmail.com"))
                .build();
    }

    /**
     * Method for making the Swagger UI accessible.
     * @param registry the Spring-registry.
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
