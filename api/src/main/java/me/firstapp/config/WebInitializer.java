package me.firstapp.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do", "*.bdo", "*.html", "*.css", "*.js" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// 配置OpenSessionInViewFilter
		Dynamic openSessionInViewFilter = servletContext.addFilter("openSessionInViewFilter",
				OpenSessionInViewFilter.class);
		openSessionInViewFilter.setInitParameter("sessionFactoryBeanName", "sessionFactory");
		openSessionInViewFilter.setInitParameter("singleSession", "true");
		openSessionInViewFilter.setInitParameter("flushMode", "AUTO");
		openSessionInViewFilter.addMappingForUrlPatterns(null, false, "/*");
		openSessionInViewFilter.addMappingForServletNames(null, false, "dispatcher");

		// 验签配置
		Dynamic apiSecuritySignFilter = servletContext.addFilter("apiSecuritySignFilter", DelegatingFilterProxy.class);
		apiSecuritySignFilter.addMappingForUrlPatterns(null, false, "/*");
		super.onStartup(servletContext);
	}

}
