package com.example.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot07.interceptor.LoginInterceptor;

/*
 *	[Spring MVC  관련설정] 
 *
 *	1. WebMvcConfigurer 인터페이스 구현
 *	2. 설정에 필요한 메소드만 오버라이드
 * 	      주로 Resource Handler, Interceptor, view page 관련설정을 여기서 함
 * 	3. 설정에 관련된 클래스에는 @Configuration 어노테이션을 등록해야함 
 */
@Configuration
public class WebConfig implements WebMvcConfigurer	{
	
	//LoginInterceptor DI 
	@Autowired LoginInterceptor loginInter;
	
	//Interceptor를 추가할 때 override해야하는 메소드 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//메소드의 인자로 전달되는 InterceptorRegistry 객체를 이용해서 Interceptor를 등록하면 됨
		
		registry.addInterceptor(loginInter)
			.addPathPatterns("/users/*", "/cafe/*", "/file/*", "/gallery/*")
			.excludePathPatterns("/users/loginform", "/users/login", "/users/signup_form", "/users/signup")
			.excludePathPatterns("/cafe/list", "/cafe/detail", "/cafe/ajax_comment_list")
			.excludePathPatterns("/file/list", "/file/download")
			.excludePathPatterns("/gallery/list", "/gallery/detail", "gallery/images/*");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
}
