package com.my.project01.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.my.project01.NotDeleteException;

/*
 *	Spring MVC가 동작 중에 특정 type의 예외가 발생하면 해당 예외를 여기서 처리할 수 있음 
 */

@ControllerAdvice
public class ExceptionController {
	//spring framework가 동작하는 중에 NotDeleteException type의 예외가 발생하면 호출되는 메소드
	@ExceptionHandler(NotDeleteException.class)
	public ModelAndView notDelete(NotDeleteException nde) { //메소드의 인자로 예외객체가 전달됨 
		ModelAndView mView = new ModelAndView();
		//exception이라는 키값으로 예외객체를 담고
		mView.addObject("exception",nde);
		//view page (/WEB-INF/view/error/info.jsp)로 forward 이동해서 예외정보 응답
		mView.setViewName("error/info");
		return mView;
	}
	
}
