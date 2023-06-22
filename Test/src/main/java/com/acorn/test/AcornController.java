package com.acorn.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AcornController {

	 @RequestMapping("/users/login")
	    public String login(String email, String password) {
	       
	        //입력한 이메일과 비밀번호를 콘솔창에 출력해 보세요.
	        System.out.println();
	       
	        return "login";
	    }
}
