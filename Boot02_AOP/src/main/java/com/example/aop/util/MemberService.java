package com.example.aop.util;
/*
 *	MemberService 메소드의 getMember() 메소드에 적용할 Aspect 클래스를 만들어서
 *	getMember()가 리턴해주는 MemberDto 객체에 1, "김수정", "서울"을 담아 리턴하시오  
 */
public class MemberService {
	
	public MemberDto getMember(int num) {
		//객체를 생성해서 바로 리턴해주기 
		MemberDto dto = new MemberDto();
		return dto;
	}
	
	
}
