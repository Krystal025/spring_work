package com.my.spring02.member.dao;

import java.util.List;

import com.my.spring02.member.dto.MemberDto;

public interface MemberDao {
	public void insert(MemberDto dto);
	public void update(MemberDto dto);
	public void delete(MemberDto dto);
	public MemberDto getData(int num);
	public List<MemberDto> getList();
}
