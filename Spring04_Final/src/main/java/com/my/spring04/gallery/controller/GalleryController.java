package com.my.spring04.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring04.file.dto.FileDto;
import com.my.spring04.gallery.dto.GalleryDto;
import com.my.spring04.gallery.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	
	
	@RequestMapping(method=RequestMethod.GET, value = "/gallery/ detail")
	public ModelAndView detail(ModelAndView mView, int num) {
		//갤러리  detail페이지에 필요한 데이터를 num으로 가져와, ModelAndView에 저장
		service.getDetail(mView, num);
		mView.setViewName("gallery/detail");
		
		return mView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/gallery/upload")
	public String upload(GalleryDto dto, HttpServletRequest request) {
		/*
		 	form에서 dto로 데이터 받아옴
			dto : caption, MultipartFile image를 가지고 있음
			request : imagePath 생성시 사용, session영역의 id를 가져올 때 사용 
																	*/
		service.saveImage(dto, request);
		
		return "gallery/upload";
	}
	
	@RequestMapping("/gallery/upload_form3")
	public String uploadForm3() {
		
		return "gallery/upload_form3";
	}
	
	// gallery 사진업로드 폼2 페이지로 이동 
	@RequestMapping("/gallery/upload_form2")
	public String uploadForm2() {
		
		return "gallery/upload_form2";
	}
	
	// gallery 사진업로드 폼 페이지로 이동 
	@RequestMapping("/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(GalleryDto dto, HttpServletRequest request){
		//서비스를 이용해서 업로드된 이미지를 저장하고 
		service.saveImage(dto, request);
		//("isSuccess", true)형식의 json문자열 응답
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
	@RequestMapping("/gallery/list")
	public String getList(HttpServletRequest request) {
		service.getList(request);
		
		return "gallery/list";
	}
	
}
