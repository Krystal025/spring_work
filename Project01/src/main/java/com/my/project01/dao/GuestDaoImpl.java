package com.my.project01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.project01.dto.GuestDto;


//component scan 
@Repository
public class GuestDaoImpl implements GuestDao{
	
	//mybatis 기반으로 DB연동을 하기위한 핵심 의존객체를 Dependency Injection 받음 
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(GuestDto dto) {

		session.insert("guest.insert", dto);
	}

	@Override
	public void update(GuestDto dto) {
		
		session.update("guest.update", dto);
	}

	@Override
	public void delete(GuestDto dto) {
		
		session.delete("guest.delete", dto);
		
	}

	@Override
	public GuestDto getData(int num) {
		
		return session.selectOne("guest.getData", num);
	}

	@Override
	public List<GuestDto> getList() {
		List<GuestDto> list = session.selectList("guest.getList");
		
		return list;
	}

}
