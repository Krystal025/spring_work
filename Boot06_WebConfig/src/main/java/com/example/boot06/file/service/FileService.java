package com.example.boot06.file.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.example.boot06.file.dto.FileDto;

public interface FileService {
	//파일 목록 얻어오기
	public void getList(HttpServletRequest request, Model model);
	//업로드된 파일 저장하기
	public void saveFile(FileDto dto, Model model, HttpServletRequest request);
	//파일 하나의 정보 얻어오기
	public void getFileData(int num, Model model);
	//파일 삭제하기
	public void deleteFile(int num, HttpServletRequest request);
}
