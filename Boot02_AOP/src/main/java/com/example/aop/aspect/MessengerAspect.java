package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MessengerAspect {

	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자를 목록에서 얻어냄
		Object[] args = joinPoint.getArgs();
		for(Object tmp:args) {
			//만약  String type이라면
			if(tmp instanceof String) {
				String msg = (String) tmp;
				System.out.println("aspect에서 읽어낸 내용: "+msg);
				if(msg.contains("바보")) {
					System.out.println("바보는 금지된 단어입니다");
					return;  //메소드 끝내기 
				}
			}
		}
	
		//aspect가 적용된 메소드가 호출되기 직전에 할 작업은 proceed() 호출 전에 함 
		
		//proceed()를 호출해야 aspect가 적용된 메소드가 실행됨
		joinPoint.proceed();
		
		//aspect가 적용된 메소드가 호출된 후에 할 작업은 proceed() 호출 후에 함 
		
		System.out.println("aspect가 적용된 메소드가 리턴됨");
	}
	
	/*
	 *	return 타입 : String
	 *	get으로 시작한 메소드
	 *	메소드에 전달되는 인자 : 없음
	 *	java.lang 패키지에 있는 타입은 패키지명 생략 가능 
	 */
	@Around("execution(String com.example.aop.util.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {

		// aspect 가 적용된 메소드를 수행하고 리턴되는 데이터 받아오기 
		Object obj = joinPoint.proceed();
		
		//원래 type 으로 casting 해서 조사할 수 있음
		String a = (String)obj;

		//조사후 아예 다른 값을 리턴해줄수도 있음
		return "놀자 놀자";
	}	
}
