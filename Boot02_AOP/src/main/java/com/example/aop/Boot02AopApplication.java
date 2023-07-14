package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.aop.util.MemberDto;
import com.example.aop.util.MemberService;
import com.example.aop.util.Messenger;
import com.example.aop.util.WritingUtil;

import ch.qos.logback.classic.pattern.Util;

/*
 *	@SpringBootApplication 어노테이션이 등록된 main메소드가 존재하는 이 패키지를 포함해서
 *	하위의 모든 패키지에 component scan이 자동으로 일어나게 됨  
 */
@SpringBootApplication
public class Boot02AopApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(Boot02AopApplication.class, args);
		
		WritingUtil util = ctx.getBean(WritingUtil.class);
		//spring이 관리하는 bean들 중 WritingUtil 타입 객체의 참조값을 얻어옴 
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
		
		Messenger m1 = ctx.getBean(Messenger.class);
		String msg = m1.getMessage();
		System.out.println("Messenger 객체로부터 받은 메세지: "+msg);
		
		m1.sendGreeting("안녕");
		m1.sendGreeting("바보");
		m1.sendGreeting("다음에 봐");	
		
		MemberService service = ctx.getBean(MemberService.class);
		MemberDto dto = service.getMember(1);
		System.out.println(dto.getNum()+" | "+ dto.getName()+" | "+dto.getAddr());
		
		}
	}

