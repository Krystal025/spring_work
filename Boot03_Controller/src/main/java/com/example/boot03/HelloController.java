package com.example.boot03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@ResponseBody //리턴하는 문자열이 클라이언트에게 바로 출력될 수 있도록 함 
	@RequestMapping("/hello")
	public String hello() {
		return "Nice to meet you";
	}
}
