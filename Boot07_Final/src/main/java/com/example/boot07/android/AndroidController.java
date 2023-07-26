package com.example.boot07.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeDto;
import com.example.boot07.gallery.dao.GalleryDao;
import com.example.boot07.gallery.dto.GalleryDto;

//Controller 메소드에서 리턴하는 데이터가 바로 클라이언트에게 응답되는 Controller 
@RestController 
public class AndroidController {
	
	@Autowired
	private GalleryDao galleryDao;
	
	@GetMapping("/android/gallery/list")
	public List<GalleryDto> galleryList(){
		//20개만 select해오도록 GalleryDto에 정보를 담음
		GalleryDto dto = new GalleryDto();
		dto.setStartRowNum(1);
		dto.setEndRowNum(20);
		//GalleryDao 객체가 리턴해주는 데이터를 바로 리턴해줌 
		return galleryDao.getList(dto);
	}
	
	//String 타입을 리턴하면 리턴된 String의 내용이 그대로 응답됨 
   @GetMapping("/android/tweet")
   public String tweet(String msg) {
      System.out.println("안드로이드에서 전송된 문자열:"+msg);
      return "success!";
   }
   
   //Map 타입을 리턴하면 Map에 담긴 내용이 {}형식의 json문자열로 응답됨
   @PostMapping("/android/tweet2")
   public Map<String, Object> tweet2(String msg){
      System.out.println("안드로이드에서 전송된 문자열:"+msg);
      Map<String, Object> map = new HashMap<>();
      map.put("isSuccess", true);
      return map;
   }
   
   //List 타입을 리턴하면 List에 담긴 내용이 []형식의 json문자열로 응답됨 
   @GetMapping("/android/tweet3")
   public List<String> tweet3(String msg){
      System.out.println("안드로이드에서 전송된 문자열:"+msg);
      
      List<String> names = new ArrayList<String>();
      names.add("수정");
      names.add("희진");
      names.add("영현");
      
      return names;
   }
   
   @Autowired
   private CafeDao dao;
   
   @GetMapping("/android/list")
   public List<CafeDto> list(){
	   //i page의 내용을 select해오기 위한  CafeDto 객체 준비
	   CafeDto dto = new CafeDto();
	   dto.setStartRowNum(1);
	   dto.setEndRowNum(10);
	   
	   List<CafeDto> list = dao.getList(dto);
	   return list;
   }
}






