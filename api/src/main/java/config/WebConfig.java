package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages = { "me.firstapp.api" })
public class WebConfig extends WebMvcConfigurerAdapter {

	// @Bean
	// public ViewResolver viewResolver() {
	// InternalResourceViewResolver resolver = new
	// InternalResourceViewResolver();
	// resolver.setPrefix("/page/");
	// resolver.setSuffix(".jsp");
	// resolver.setExposeContextBeansAsAttributes(true);
	// return resolver;
	// }

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public SpringSwaggerConfig springSwaggerConfig() {
		return new SpringSwaggerConfig();
	}

	@Bean
	public SwaggerSpringMvcPlugin swaggerSpringMvcPlugin(SpringSwaggerConfig springSwaggerConfig) {
		return new SwaggerSpringMvcPlugin(springSwaggerConfig);
	}
	

//	@Bean
//	public SwaggerSpringMvcPlugin customImplementation(SpringSwaggerConfig springSwaggerConfig) {
//		return new SwaggerSpringMvcPlugin(springSwaggerConfig)//
//				.apiVersion("V1.0")//
//				.apiInfo(apiInfo())//
//				.includePatterns(".*?")//
//				.genericModelSubstitutes(ResponseEntity.class, HttpEntity.class).build();
//
//	}

//	private ApiInfo apiInfo() {
//		ApiInfo apiInfo = new ApiInfo(//
//				"CRM系统 API接口列表", //
//				"CRM系统 API接口列表", //
//				"http://www.fantasee.cn", //
//				"service@fantasee.cn", //
//				null, //
//				null);
//		return apiInfo;
//	}
}
