package com.my.spring03.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
 * 	[Interceptor 생성]
 * 
 * 	1. HandlerInterceptor 인터페이스 구현
 *  2. servlet-context.xml에 bean 설정 후 interceptor 목록에 등록 후 맵핑 
 */
public class MyInterceptor implements HandlerInterceptor{
	
	//Controller 실행 이전에 호출되는 메소드 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//request scope에 메세지 담기
		request.setAttribute("msg", "나는 인터셉트");
		System.out.println("preHandle()");
		/*
		 *	true를 리턴하면 계속 흐름을 이어가고 false를 리턴하면 이어가지 않음 
		 */
		return true;
	}
	//Controller 실행 이후에 호출되는 메소드 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle()");
		
	}
	//응답된 이후에 호출되는 메소드 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion()");
	}

}
