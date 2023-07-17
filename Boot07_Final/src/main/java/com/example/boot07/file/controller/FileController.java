package com.example.boot07.file.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.boot07.file.dto.FileDto;
import com.example.boot07.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService service;
	
	   @RequestMapping("/file/list")
	   public String list(HttpServletRequest request) {
	      
	      service.getList(request);
	      
	      return "file/list";
	   }
	   
	   @RequestMapping("/file/upload_form")
	   public String uploadForm() {
	      
	      return "file/upload_form";
	   }
	   
	   @RequestMapping("/file/upload")
	   public String upload(FileDto dto, Model model, HttpServletRequest request) {
		   //FileDto에는 폼 전송되는 title과 myFile이 들어있음 
	      service.saveFile(dto, model, request);
	      
	      return "file/upload";
	   }
	   
	   @RequestMapping("/file/download")
	   public ResponseEntity<InputStreamResource> download(int num) throws FileNotFoundException, UnsupportedEncodingException {
		   // num : 다운로드해줄 파일의 번호(PK) 
	      
	      // 응답을 할 bean 의 이름을 설정 
	      
	      return service.getFileData(num); 
	   }
	   
	   @RequestMapping("/file/delete")
	   public String delete(int num, Model model, HttpServletRequest request) {
	      service.deleteFile(num, request);
	     
	      return "redirect:/file/list";
	   }
}
