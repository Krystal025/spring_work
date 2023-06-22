package com.my.spring01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FortuneController {
	
	@RequestMapping("/fortune1")
	public String fortune(HttpServletRequest request) {
		//오늘의 운세(모델)
		String fortuneToday = "술은 적당히 먹는걸로^^";
		//request scope에 응답에 필요한 데이터를 담음
		request.setAttribute("fortundToday", fortuneToday);
		//WEB-INF/views/fortune.jsp 페이지로 forward 이동해서 응답하기
		return "fortune";
	}
	
	@RequestMapping("/fortune2")
	public ModelAndView fortune2() {
		//오늘의 운세(모델)
		String fortuneToday = "오늘 끝까지 달려";
		//WEB-INF/views/fortune.jsp 페이지로 forward 이동해서 응답하기
		ModelAndView mView = new ModelAndView();
		//view page에 전달할 내용을 담음 
		mView.addObject("fortuneToday", fortuneToday);
		//view page의 위치도 담음
		mView.setViewName("fortune");
		//모델과 view page 정보가 모두 담겨있는 ModelAndView 객체를 리턴해주면 똑같이 동작
		return mView;
	}
	//ModelAndView를 매개변수로 선언하면 스프링 프레임워크가 알아서 객체생성후 참조값을 넣어줌 
	@RequestMapping("/fortune3")
	public ModelAndView fortune3(ModelAndView mView) {
		//오늘의 운세(모델)
		String fortuneToday = "일찍 집에 들어가유";
		//view page에 전달할 내용을 담음 
		mView.addObject("fortuneToday", fortuneToday);
		//view page의 위치도 담음
		mView.setViewName("fortune");
		//모델과 view page 정보가 모두 담겨있는 ModelAndView 객체를 리턴해주면 똑같이 동작
		return mView;
	}
}
