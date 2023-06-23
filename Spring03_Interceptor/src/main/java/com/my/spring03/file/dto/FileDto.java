package com.my.spring03.file.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
	//input type="file" name"myFile"로 전송되는 파일의 파라미터명과 dto의 필드명을 일치시켜줌 
	private String title;
	private MultipartFile myFile;
	
	public FileDto() {}

	public FileDto(String title, MultipartFile myFile) {
		super();
		this.title = title;
		this.myFile = myFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getMyFile() {
		return myFile;
	}

	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}
	
}
