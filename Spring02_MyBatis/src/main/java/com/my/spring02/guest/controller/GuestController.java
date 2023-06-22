package com.my.spring02.guest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring02.guest.dao.GuestDao;
import com.my.spring02.guest.dto.GuestDto;
import com.my.spring02.guest.service.GuestService;

@Controller
public class GuestController {
	
	@Autowired
	private GuestService service;
	
	//글 삭제 요청처리
	@RequestMapping(method = RequestMethod.POST, value = "/guest/delete")
	public String delete(GuestDto dto) {
		service.deleteGuest(dto);
		return "redirect:/guest/list";
	}
	
	//글 수정 요청처리
	@RequestMapping("/guest/update")
	public String update(GuestDto dto) {
		service.updateGuest(dto);
		return "guest/update";
	}
	
	//글 수정폼 요청처리
	@RequestMapping("/guest/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		// 수정할 글 정보를 얻어옴
		service.getGuestInfo(mView, num); //service로 받을 땐 객체를 따로 생성하지 않아도 됨 
		// View Page의 위치도 담음 
		mView.setViewName("guest/updateform");
		// 반환 
		return mView;
	}
	
	//글 등록 요청처리 
	@RequestMapping(method = RequestMethod.POST, value = "/guest/insert") //POST방식일때만 동작 
	public String insert(GuestDto dto) {
		service.addGuest(dto);
		return "guest/insert";
	}
	
	//글 등록 폼 요청처리
	@RequestMapping("/guest/insertform")
	public String insertform(GuestDto dto) {
		return "guest/insertform";
	}
	
	//글 목록보기 요청처리 
	@RequestMapping("guest/list")
	public ModelAndView list(ModelAndView mView) {
		//service 메소드를 호출해서 ModelAndView 객체의 참조값을 전달하면 방명록 목록이 담김 
		service.getGuestList(mView);
		//View Page 정보도 담음 
		mView.setViewName("guest/list");
		return mView;
		/*
		 *  두개의 정보가 모두 담긴 ModelAndView 객체를 리턴해주면
		 *  addObject(key, value)를 통해서 담은 정보는 request scope에 담기고
		 *  setViewName(view page위치)을 통해서 담은 정보는 해당 view page로 forward 이동해서 응답됨 
		 */
	}
}
