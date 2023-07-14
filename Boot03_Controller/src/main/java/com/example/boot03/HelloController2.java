package com.example.boot03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller 메소드에서 리턴하는 내용을 바로 클라이언트에게 응답하는 controller
//일반 문자열, xml, json형식의 문자열을 응답할 때 주로 사용됨
//모든 controller 메소드에 @Responsebody를 등록한 것과 같음 
@RestController
public class HelloController2 {
	
	@RequestMapping(method = RequestMethod.GET, value="/hello2")
	public String hello2() {
		return "Hi";
	}
	
	//GET방식 요청 맵핑을 간단하게 하는 방법 
	@GetMapping("/hello3")
	public String hello3() {
		return "bye";
	}
}
