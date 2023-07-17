package com.example.boot07.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.gallery.dto.GalleryDto;
import com.example.boot07.gallery.service.GalleryService;
import com.example.boot07.users.dto.UsersDto;

@Controller
public class GalleryController {
	/*
	 *	GET방식 /users/signup_form 요청을 처리할 메소드
	 *	- 요청방식이 다르면 실행되지 않음 	
	 */
	@Autowired
	private GalleryService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping(
			value = "gallery/images/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
					MediaType.IMAGE_GIF_VALUE})
	@ResponseBody
	public byte[] galleryImage(@PathVariable("imageName")String imageName) throws IOException {
		
		String absolutePath = fileLocation + File.separator + imageName;
		//파일에서 읽어들일 inputStream
		InputStream is = new FileInputStream(absolutePath);
		//fileLocation : 필드에 저장되어있는 서버의 파일시스템 위치가 들어있음
		return IOUtils.toByteArray(is);
	}
	
	@GetMapping("/gallery/detail")
	public String detail(Model model, int num) {
		//갤러리  detail페이지에 필요한 데이터를 num으로 가져와, ModelAndView에 저장
		service.getDetail(model, num);
		
		return "gallery/detail";
	}
	
	//gallery 사진 업로드 & DB 저장
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto, HttpServletRequest request) {
		//form 에서 dto 로 데이터 받아옴
		//dto : caption, MultipartFile image 를 가지고 있다.
		//request :  imagePath 만드는데 사용, session 영역의 id 가져오는데 사용
		service.saveImage(dto, request);
			
		return "gallery/upload";
	}
	
	@GetMapping("/gallery/upload_form3")
	public String uploadForm3() {
		
		return "gallery/upload_form3";
	}
	
	// gallery 사진업로드 폼2 페이지로 이동 
	@GetMapping("/gallery/upload_form2")
	public String uploadForm2() {
		
		return "gallery/upload_form2";
	}
	
	// gallery 사진업로드 폼 페이지로 이동 
	@GetMapping("/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	
	@PostMapping("/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(GalleryDto dto, HttpServletRequest request){
		//서비스를 이용해서 업로드된 이미지를 저장하고 
		service.saveImage(dto, request);
		//("isSuccess", true)형식의 json문자열 응답
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
	@GetMapping("/gallery/list")
	public String getList(HttpServletRequest request) {
		service.getList(request);
		
		return "gallery/list";
	}
	
}
