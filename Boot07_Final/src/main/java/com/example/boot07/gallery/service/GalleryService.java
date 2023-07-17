package com.example.boot07.gallery.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.example.boot07.gallery.dto.GalleryDto;

public interface GalleryService {
	// 갤러리 list 가져오기 
	public void getList(HttpServletRequest request);
	// 갤러리에 사진 업로드 & DB 저장 
	public void saveImage(GalleryDto dto, HttpServletRequest request);
	// 갤러리 detail페이지에 필요한 데이터를 ModelAndView에 저장 
	public void getDetail(Model model, int num);
}
