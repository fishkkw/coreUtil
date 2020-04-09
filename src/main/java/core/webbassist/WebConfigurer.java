package core.webbassist;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import core.webbassist.bean.paramValidator.VaildParamConfig;
import core.webbassist.frameConfig.ParamCheckFilter;
import core.webbassist.frameConfig.WebFilter;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
	@Autowired
	private WebFilter webFilter;

	// 过滤器注册
	@Bean
	public FilterRegistrationBean<Filter> filterRegist() {
		FilterRegistrationBean<Filter> frBean = new FilterRegistrationBean<>();
		frBean.setFilter(webFilter);
		frBean.addUrlPatterns("/*");
		frBean.setOrder(1);
		return frBean;
	}

	// 过滤器注册
	@Bean
	public FilterRegistrationBean<Filter> paramCheckFilter() {
		FilterRegistrationBean<Filter> frBean = new FilterRegistrationBean<>();
		VaildParamConfig config = new VaildParamConfig();
		config.setPath("validate/validate.zip");
		config.setResourceType(".zip");
		frBean.setFilter(new ParamCheckFilter(config));
		frBean.addUrlPatterns("/*");
		frBean.setOrder(2);
		return frBean;
	}

}
