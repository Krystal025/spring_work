package com.example.boot06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // setter, getter 생성
@Builder  // setter메소드 대신 체인형태로 값을 넣어줄수 있게 함 
@NoArgsConstructor  // 기본생성자
@AllArgsConstructor // 인자로 모든 값이 전달되는 생성자 
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
