package com.example.boot07.users.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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

import com.example.boot07.users.dto.UsersDto;
import com.example.boot07.users.service.UsersService;

@Controller
public class UsersController {
	/*
	 *	GET방식 /users/signup_form 요청을 처리할 메소드
	 *	- 요청방식이 다르면 실행되지 않음 	
	 */
	@Autowired
	private UsersService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping(
			value = "users/images/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
					MediaType.IMAGE_GIF_VALUE})
	@ResponseBody
	public byte[] getImage(@PathVariable("imageName")String imageName) throws IOException {
		
		String absolutePath = fileLocation + File.separator + imageName;
		//파일에서 읽어들일 inputStream
		InputStream is = new FileInputStream(absolutePath);
		//fileLocation : 필드에 저장되어있는 서버의 파일시스템 위치가 들어있음
		return IOUtils.toByteArray(is);
	}
	
	//개인정보 삭제 요청처리
	@GetMapping("/users/delete")
	public String delete(HttpSession session, Model model) {
		/*
		 * Controller의 메소드로 전달받은 Model 객체를 Service 객체에 전달해서
		 * view 페이지에 전달할 데이터(Model)가 담기도록 해야함
		 * Model 객체에 addAttribute()해서 담은 데이터는 spring framework가
		 * HttpServletRequest 객체에 setAttribute()로 대신 담아줌
		 * 따라서 forward(응답을 위임받은) jsp 페이지에서 해당 데이터를 사용할 수 있음 */
		service.deleteUser(session, model);
		
		//view 페이지의 정보를 리턴(forward 이동할 jsp페이지의 위치) 
		return "users/delete";
	}
	
	//개인정보 수정 요청처리
	@PostMapping(value = "/users/update")
	public String update(UsersDto dto, HttpSession session, Model model) {
		//service를 이용해서 개인정보를 수정 
		service.updateUser(dto, session);
		//개인정보 보기로 리다이렉트 이동시킴
		return "redirect:/users/info";
	}
	
	//ajax 프로필 사진 업로드 요청처리
	@ResponseBody
	@PostMapping(value = "/users/profile_upload")
	public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image){
		//service를 이용해서 이미지를 upload폴더에 저장하고 리턴되는 Map을 리턴해서 json문자열 응답하기
		return service.saveProfileImage(request, image);
	}
	
	//개인정보 수정폼 요청처리
	@GetMapping("/users/updateform")
	public String updateform(HttpSession session, Model model) {
		service.getInfo(session, model);
		return "users/updateform";
	}
	
	//비밀번호 수정 요청처리
	@PostMapping("/users/pwd_update")
	public String pwdUpdate(UsersDto dto, Model model, HttpSession session) {
		//서비스에 필요한 객체의 참조값을 전달해서 비밀번호 수정 로직을 처리함
		service.updateUserPwd(session, dto, model);
		//View page로 forward 이동해서 작업결과를 응답
	    return "users/pwd_update";
	}		
	
	//비밀번호 수정폼 요청처리
	@GetMapping("/users/pwd_updateform")
	public String pwdUpdateForm() {
		return "users/pwd_updateform";
	}
	
	//개인정보 보기 요청처리
	@GetMapping("/users/info")
	public String info(HttpSession session, Model model) {
		service.getInfo(session, model);
		
		return "users/info";
	}	
	
	//로그아웃 요청처리
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		//세션에서 id라는 키값으로 저장된 값 삭제
		session.removeAttribute("id");
		return "users/logout";
	}
	
	//로그인 요청처리
	@PostMapping("/users/login")
	public String login(Model model, UsersDto dto, String url, HttpSession session) {
		/*
		 *	서비스에서 비즈니스 로직을 처리할 때 필요로 하는 객체를 컨트롤러에서 직접 전달해줘야함
		 *	주로 HttpServletRequest, HttpServletResponse, HttpSession, ModelAndView등의 객체임  
		 */
		service.loginProcess(dto, session);
		
		//로그인 후에 가야할 목적지 정보를 인코딩하지 않는 것과 인코딩한 것을 모두 ModelAndView 객체에 담고
		String encodedUrl = URLEncoder.encode(url);
		model.addAttribute("url", url);
		model.addAttribute("encodedeUrl", encodedUrl);
		
		//view page로 forward 이동해서 응답
		return "users/login";
	}
	
	//로그인 폼 요청처리
	@GetMapping("/users/loginform")
	public String loginForm() {
		return "users/loginform";
	}
	
	//회원가입 요청처리 
	@PostMapping("/users/signup")
	public String signup(UsersDto dto) {
		//서비스를 이용해서 DB에 저장하고
		service.addUser(dto);
		return "users/signup";
	}
	
	//회원가입 폼 요청처리
	@GetMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
}
