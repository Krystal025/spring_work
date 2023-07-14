package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.aop.util.MemberDto;

@Aspect
@Component
public class MemberAspect {

	@Around("execution(com.example.aop.util.MemberDto get*(..))")
	public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect가 적용된 메소드를 수행하고 리턴되는 값(참조값)을 받아옴 
		Object obj = joinPoint.proceed();
		//원래 타입으로 Casting
		MemberDto dto = (MemberDto)obj; 
		//필드에 값을 넣어줌
		dto.setNum(1);
		dto.setName("김수정");
		dto.setAddr("서울");
		//joinPoint.proceed()메소드가 리턴한 참조값을 그대로 리턴해줌 
		return dto;
	}
}
