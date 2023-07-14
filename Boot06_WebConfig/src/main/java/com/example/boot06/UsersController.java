package com.example.boot06;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
	
	//개인정보 보기 요청처리
	@GetMapping("/users/info")
	public String info(Model model) {
		//DB에서 읽어온 개인정보라고 가정
		String address = "서울시 강남구 청담동";
		/*
		 	컨트롤러의 매개변수로 전달된 Model객체레 addAttribute()메소드를 이용해서 
		 	view page에 전달할 내용을 담으면 알아서 HttpServeltRequest 객체에 담김
		 	 또한 컨트롤러의 return type으로 리턴하지 않아도 동작함  
		 */
		model.addAttribute("address", address);
		return "users/info";
	}
	
	//로그아웃 요청처리
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//로그인 요청처리  
	@PostMapping("/users/login")
	public String login(HttpSession session, String id) {
		//가상의 로그인 처리
		// session.invalidate();  초기화 후에 세션에 정보를 담을 수 없음 
		session.setAttribute("id", id);
		return "redirect:/";  //최상위 경로로 리다이렉트 (다시 폼이 보임) 
	}
	
	//로그인 폼 요청처리 
	@GetMapping("/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
}
