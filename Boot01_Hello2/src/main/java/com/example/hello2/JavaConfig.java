package com.example.hello2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello2.pc.Computer;
import com.example.hello2.pc.Cpu;
import com.example.hello2.util.Remocon;
import com.example.hello2.util.RemoconImpl;
import com.example.hello2.util.TvRemocon;

/* 
 * 어떤 객체를 spring이 생성해서 관리할지 설정 (bean 설정)	
 * 
 * xml문서를 설정하던 bean 설정을 class 기반으로 실행 
 */
@Configuration
public class JavaConfig {
	 /*
	  *	이 메소드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록 함
	  * 아래의 메소드는 xml문서에서 <bean id = "myCar" class="com.example.demo.Car"/>과 같음 
	  */
	@Bean
	public Car myCar() { //method의 이름이 bean의 이름(id)역할을 함 
		System.out.println("myCar()메소드 호출됨");
		Car c1 = new Car();
		return c1;
	}
	
	//Remocon 인터페이스 타입이 spring이 관리하는 bean이 되도록 설정하시오
	@Bean
	public Remocon myRemocon() { //bean 이름 : myRemocon
		//의존관계를 느슨하게 하기 위해 인터페이스 타입을 사용 
		Remocon r1 = new RemoconImpl();
		return r1;
	}
	
	@Bean
	public Remocon tvRemocon() { //bean 이름 : tvRemocon 
		Remocon r1 = new TvRemocon();
			return r1;
	}
	
	@Bean
	public Cpu getCpu() {
		return new Cpu();
	}
	
	@Bean
	public Computer myComputer() {
		//생성자에 또 다른 Bean의 참조값이 필요하면 메소드를 호출해서 얻어내면 됨 
		Computer c1 = new Computer(getCpu());
		return c1;
	}
}

