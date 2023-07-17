package com.example.boot06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	
	@PostMapping("/member/insert")
	@ResponseBody
	public String insert(int num, String name, String addr) {
		
		MemberDto dto = new MemberDto();
		dto.setNum(num);
		dto.setName(name);
		dto.setAddr(addr);
		
		//이 형태로 많이 사용됨 
		MemberDto dto2 = MemberDto.builder()  // MemberDtoBuilder 타입 
				.num(num)
				.name(name)
				.addr(addr)
				.build();  // MemberDto 타입 
		return "ok";
	}
	
}
