package com.my.spring04.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring04.cafe.dto.CafeCommentDto;
import com.my.spring04.cafe.dto.CafeDto;
import com.my.spring04.cafe.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService service;
		
		//댓글 수정 요청처리
		@RequestMapping("/cafe/comment_update")
		@ResponseBody
		public Map<String, Object> commentUpdate(CafeCommentDto dto) {
			service.updateComment(dto);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSuccess", true);
			// ("isSuccess", true)형식의 JSON 문자열이 응답되도록 함 
			return map;
		}
		
		//댓글 삭제 요청처리
		@RequestMapping("/cafe/comment_delete")
		@ResponseBody
		public Map<String, Object> commentDelete(HttpServletRequest request) {
			service.deleteComment(request);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSuccess", true);
			// ("isSuccess", true)형식의 JSON 문자열이 응답되도록 함 
			return map;
		}
	
		//댓글 더보기 요청 처리
		@RequestMapping("/cafe/ajax_comment_list")
		public String commentList(HttpServletRequest request) {
			//테스트를 위해 시간 지연시키기
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.moreCommentList(request);
			return "cafe/ajax_comment_list";
		}
		
		//새로운 댓글 저장 요청 처리
		@RequestMapping("/cafe/comment_insert")
		public String commentInsert(HttpServletRequest request, int ref_group) {
			//새로운 댓글을 저장하는 로직을 수행
			service.saveComment(request);
			//reg_group은 원글의 글번호이기 때문에 원글 자세히 보기로 다시 리다이렉트 이동됨
			return "redirect:/cafe/detail?num="+ref_group;
		}
		
	   @RequestMapping("/cafe/list")
	   public String list(HttpServletRequest request) {
		   //Service에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고 
	      service.getList(request);
	      //View page로 forward 이동해서 응답하기 
	      return "cafe/list";
	   }
	   
	   @RequestMapping("/cafe/insertform")
	   public String insertform() {
	      return "cafe/insertform";
	   }
	   
	   @RequestMapping("/cafe/insert")
	   public String insert(CafeDto dto, HttpSession session) {
	      //글 작성자는 세션에서 얻어낸다.
	      String writer=(String)session.getAttribute("id");
	      //dto 는 글의 제목과 내용만 있으므로 글작성자는 직접 넣어준다.
	      dto.setWriter(writer);
	      service.saveContent(dto);
	      return "cafe/insert";
	   }
	   
	   @RequestMapping("/cafe/detail")
	   public String detail(HttpServletRequest request) {
		//Service에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고
	      service.getDetail(request);
	      
	      return "cafe/detail";
	   }
	   
	   @RequestMapping("/cafe/delete")
	   public String delete(int num, HttpServletRequest request) {
		  //Service에 삭제할 글번호와 HttpServletRequest 객체를 전달해서 해당글을 삭제시키고 
	      service.deleteContent(num, request);
	      //글 목록보기로 리다이렉트 이동
	      return "redirect:/cafe/list";
	   }
	   
	   @RequestMapping("/cafe/updateform")
	   public String updateForm(HttpServletRequest request) {
	      service.getData(request);
	      return "cafe/updateform";
	   }
	   
	   @RequestMapping("/cafe/update")
	   public String update(CafeDto dto) {
	      service.updateContent(dto);
	      return "cafe/update";
	   }
	
}
