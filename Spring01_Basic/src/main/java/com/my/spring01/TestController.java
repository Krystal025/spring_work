package com.my.spring01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *	JSON 문자열 응답하는 방법
 *	1. pom.xml에 jackson-databind dependency를 추가
 *	2. 컨트롤러 메소드에 @ResponseBody 어노테이션을 등록
 *	3. Dto, List, Map 등을 컨트롤러에서 리턴해주면 해당 객체에 담긴 정보가 json으로 구성되어 응답됨   
 */

@Controller
public class TestController {

	@ResponseBody
	@RequestMapping("/test/json1")
	public String json1() {
		return "{\"num\":1, \"name\":\"Kim\", \"addr\":\"Seoul\" }";
	}

	@ResponseBody
	@RequestMapping("/test/json2")
	// DB에서 읽어온 회원정보라고 가정
	public MemberDto json2() {
		MemberDto dto = new MemberDto();
		dto.setNum(2);
		dto.setName("수정");
		dto.setAddr("서울");

		return dto;
	}

	@ResponseBody
	@RequestMapping("/test/json3")
	public Map<String, Object> json3() {
		Map<String, Object> map = new HashMap<>();
		map.put("num", 3);
		map.put("name", "영현");
		map.put("addr", "부여");
		return map;
	}

	@ResponseBody
	@RequestMapping("/test/json4")
	public List<String> json4() {
		List<String> names = new ArrayList<>();
		names.add("지영");
		names.add("지원");
		names.add("혜림");
		return names;
	}

	@ResponseBody
	@RequestMapping("/test/json5")
	public List<MemberDto> json5() {
		List<MemberDto> list = new ArrayList<>();
		list.add(new MemberDto(1, "수정", "서울"));
		list.add(new MemberDto(1, "희진", "춘천"));
		list.add(new MemberDto(1, "영현", "부여"));
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/test/json6")
	public List<Map<String, Object>> json6(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("num", 1);
		map1.put("name", "수정");
		map1.put("addr", "서울");
		Map<String, Object> map2 = new HashMap<>();
		map2.put("num", 2);
		map2.put("name", "희진");
		map2.put("addr", "춘천");
		Map<String, Object> map3 = new HashMap<>();
		map3.put("num", 3);
		map3.put("name", "영현");
		map3.put("addr", "부여");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		return list;
	}
	
	
}
