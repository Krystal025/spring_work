package com.my.project01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.project01.dto.GuestDto;
import com.my.project01.service.GuestService;


@Controller
public class GuestController {
	
	@Autowired
	private GuestService service;
	
	//글 목록보기 요청처리 
	@RequestMapping("/guest/list")
	public ModelAndView list(ModelAndView mView) { 
		service.getGuestList(mView);
		
		mView.setViewName("guest/list");
		return mView;
	}
	
	//글 등록 폼 요청처리
	@RequestMapping("/guest/insertform")
	public String insertform(GuestDto dto) {
		return "guest/insertform";
	}

	//글 등록 요청처리 
	@RequestMapping(method = RequestMethod.POST, value = "/guest/insert") //POST방식일때만 동작 
	public String insert(GuestDto dto) {
		service.addGuest(dto);
		
		return "guest/insert";
	}
	
	//글 수정폼 요청처리
	@RequestMapping("/guest/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		service.getGuestInfo(mView, num); //service로 받을 땐 객체를 따로 생성하지 않아도 됨 
		mView.setViewName("guest/updateform");
		
		return mView;
	}
	
	//글 수정 요청처리
	@RequestMapping("/guest/update")
	public String update(GuestDto dto) {
		service.updateGuest(dto);
		
		return "guest/update";
	}
	
	//글 삭제 요청처리
	@RequestMapping(method = RequestMethod.POST, value = "/guest/delete")
	public String delete(GuestDto dto) {
		service.deleteGuest(dto);
		
		return "redirect:/guest/list";
	}
	
}
