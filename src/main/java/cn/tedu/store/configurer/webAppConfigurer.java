package cn.tedu.store.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;


@Configuration
public class webAppConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns(
				"/user/**",
				"/web/**",
				"/address/**",
				"/cart/**"
				)
		//白名单
		.excludePathPatterns("/user/login.do")
		.excludePathPatterns("/user/reg.do")
		.excludePathPatterns("/web/login.html")
		.excludePathPatterns("/web/register.html")
		.excludePathPatterns("/web/index.html")
		.excludePathPatterns("/web/product.html")
		;
	}
	
	
}
