package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "me.firstapp.bbs" })
public class WebConfig extends WebMvcConfigurerAdapter {

	// @Bean
	// public ViewResolver viewResolver() {
	// FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
	// resolver.setCache(false);
	// resolver.setSuffix(".ftl");
	// // resolver.setExposeContextBeansAsAttributes(true);
	// resolver.setContentType("text/html;charset=UTF-8");
	// resolver.setViewClass(FreeMarkerView.class);
	// return resolver;
	// }

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/page/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// @Bean
	// public FreeMarkerConfig freeMarkerConfig() {
	// FreeMarkerConfigurer freeMarkerConfig = new FreeMarkerConfigurer();
	// freeMarkerConfig.setTemplateLoaderPath("/page/");
	//
	// // Properties properties = new Properties();
	// // properties.put("template_update_delay", 0);
	// // properties.put("default_encoding", "UTF-8");
	// //// properties.put("number_format", "0.##");
	// //// properties.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
	// //// properties.put("classic_compatible", true);
	// //// properties.put("template_exception_handler", "ignore");
	// // freeMarkerConfig.setFreemarkerSettings(properties);
	// return freeMarkerConfig;
	// }
}
