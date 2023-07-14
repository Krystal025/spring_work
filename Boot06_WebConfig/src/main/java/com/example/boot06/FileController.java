package com.example.boot06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot06.file.dto.FileDto;

@Controller
public class FileController {
	
	@Autowired
	private com.example.boot06.file.service.FileService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/file/list")
	   public String list(HttpServletRequest request, Model model) {
	      
	      service.getList(request, model);
	      
	      return "file/list";
	   }
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName,
			  String saveFileName, long fileSize) throws UnsupportedEncodingException, FileNotFoundException{
	      //원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.
	      
	      //다운로드 시켜줄 원본 파일명
	      String encodedName=URLEncoder.encode(orgFileName, "utf-8");
	      //파일명에 공백이 있는경우 파일명이 이상해지는것을 방지
	      encodedName=encodedName.replaceAll("\\+"," ");
	      //응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스)구성하기 (웹브라우저에 알릴정보)
	      HttpHeaders headers=new HttpHeaders();
	      //파일을 다운로드 시켜 주겠다는 정보
	      headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
	      //파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어줌)
	      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
	      //파일의 크기 정보
	      headers.setContentLength(fileSize);
	      
	      //읽어들일 파일의 경로 구성
	      String filePath=fileLocation + File.separator + saveFileName;
	      //파일에서 읽어들일 Stream 객체
	      InputStream is=new FileInputStream(filePath);
	      //InputStreamResource 객체의 참조값을 얻어냄 
	      InputStreamResource isr=new InputStreamResource(is);
	      
	      //ResponseEntity 객체의 참조값을 얻어냄
	      ResponseEntity<InputStreamResource> resEn=ResponseEntity.ok()
	         .headers(headers)
	         .header("Content-Transfer-Encoding", "binary")
	         .body(isr);
	         
	      return resEn;

	}
	
	@GetMapping("/file/uploadform")
	public String uploadform() {
		return "file/uploadform";
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto, Model model) {
		/* dto에 있는 정보를 이용해서 C://acorn202304/upload 폴더에 업로드된 파일을 저장해보시오
		 * 파일명  : UUID + 원본파일명으로 만들 것 
		 */
		//업로드도니 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값을 얻어옴
		MultipartFile myFile = dto.getMyFile();
		//원본 파일명
		String orgFileName = myFile.getOriginalFilename();
		//파일 크기
		long fileSize = myFile.getSize();
		//저장할 파일명 구성
		String saveFileName = UUID.randomUUID().toString();
		String filePath = fileLocation + File.separator + saveFileName;
		try {
			//원하는 경로에 파일 저장 
			myFile.transferTo(new File(filePath));
		}catch(Exception e){
			e.printStackTrace();
		}
		//원래는 DB에 저장해야함 (테스트를 위해 view 페이지에 전달) 
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("saveFileName", saveFileName);
		model.addAttribute("fileSize", fileSize);
		return "file/upload";
	}
	
	@GetMapping("/file/delete")
	   public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
	      service.deleteFile(num, request);
	      mView.setViewName("redirect:/file/list");
	      return mView;
	   }
}
