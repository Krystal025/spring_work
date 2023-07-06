package com.my.project01;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//이 프로젝트의 최상위 경로 요청이 오면 
	@RequestMapping("/")
	public String home(HttpServletRequest request) {

		// /WEB-INF/views/home.jsp 페이지로 forward 이동해서 응답하겠다는 의미
		// "home"이라는 문자열을 리턴하면 앞에 "/WEB-INF/views/"뒤에 ".jsp"가 자동으로 붙음 
		return "home";
	}
	
}
