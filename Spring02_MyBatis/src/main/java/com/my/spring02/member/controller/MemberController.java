package com.my.spring02.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring02.member.dto.MemberDto;
import com.my.spring02.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	/*
	 * @RequestParam(value = "파라미터명", defaultValue = "기본값")
	 * 기본값은 없어도 되고 파라미터명과 매개변수명도 일치한다면 생략가능함 
	 */
	//회원삭제 요청처리
	@RequestMapping("/member/delete")
	public String delete(MemberDto dto) {
		service.deleteMember(dto);
		//'목록보기'로 리다이렉트 응답 
		return "redirect:/member/list";
	}
	
	/*
	 * @RequestMapping에 method 속성값을 명시하지 않으면 요청방식을 가리지 않고 
	 * 경로만 맞으면 모두 처리해 준다 (GET, POST, PUT, DELETE) 
	 * 
	 * method 속성값을 명시하면 경로가 맞더라도 요청방식이 다르면 처리해주지 않는다
	 * 일반적으로 POST인 경우에는 요청방식을 명시해주는 것이 좋다
	 */
	//회원수정 요청처리 
	@RequestMapping(method = RequestMethod.POST, value = "/member/update")
	public String update(MemberDto dto) {
		service.updateMember(dto);
		return "member/update";
	}
	
	//회원수정 폼 요청처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		//수정할 회원의 정보를 얻어옴
		service.getMemberInfo(mView, num);
		/*
		 *	수정할 회원의 정보를 ModelAndView 객체의 addObiect(key, value)메소드를 이용해서 담는다
		 * 	ModelAndView 객체에 담은 값은 결국 HttpServletRequest 객체에 담긴다 (request scope)
		 */
		//view page의 위치도  ModelAndView 객체에 담아서 리턴해야함
		mView.setViewName("member/updateform");
		//모델(data)와 view page의 정보가 모두 담긴  ModelAndView 객체를 리턴해주면 spring이 알아서 처리해줌
		return mView;
	}	
	
	//회원추가 요청처리
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {
		//MemberDao 객체를 이용하여 DB에 저장
		service.addMember(dto);
		//view 페이지로 forward 이동해서 응답
		return "member/insert";
	}
	
	//회원추가 폼 요청처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		return "member/insertform";
	}
	
	//회원목록 보기 요청처리 
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//회원목록을 얻어와서
		service.getMemberList(mView);
		//request scope에 담고
		mView.setViewName("member/list");
		// /WEB-INF/view/member/list.jsp 페이지로 forward 이동해서 응답 
		return mView;
	}
}
