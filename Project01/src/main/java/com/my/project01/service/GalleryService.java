package com.my.project01.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.project01.dto.GalleryDto;

public interface GalleryService {
	// 갤러리 list 가져오기 
	public void getList(HttpServletRequest request);
	// 갤러리에 사진 업로드 & DB 저장 
	public void saveImage(GalleryDto dto, HttpServletRequest request);
	// 갤러리 detail페이지에 필요한 데이터를 ModelAndView에 저장 
	public void getDetail(ModelAndView mView, int num);
}
