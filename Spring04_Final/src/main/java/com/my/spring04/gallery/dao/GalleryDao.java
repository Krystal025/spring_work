package com.my.spring04.gallery.dao;

import java.util.List;

import com.my.spring04.gallery.dto.GalleryDto;

public interface GalleryDao {
	// 이미지 목록보기
	public List<GalleryDto> getList(GalleryDto dto);
	// 이미지 갯수
	public int getCount ();
	// 갤러리에 사진 저장 (이미지 업로드)
	public void insert(GalleryDto dto);
	// PK num에 해당하는 DB에서  갤러리 게시글 하나의 데이터 가져오기 (이미지 정보 얻어오기) 
	public GalleryDto getData(int num);

}
