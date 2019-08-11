package com.avalon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

	  @Bean
	  public Docket avalonApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.avalon"))
	        .build()
	        .apiInfo(metaData());

	  }

	  private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	        .title("Avalon REST API")
	        .description("\"Avalon is a REST API to obtain transactions data from OpenBack sandbox\"")
	        .version("1.0.0")
	        .build();
	  }

	  @Override
	  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	  }
	
}
