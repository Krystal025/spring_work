package com.my.spring04.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring04.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	@Autowired
	private SqlSession session;
	
	//매개변수로 전달되는 아이디가 DB에 이미 존재하는지 여부를 리턴하는 메소드  
	@Override
	public boolean isExist(String inputId) {
		//전달된 아아디로 select해서
		UsersDto dto = session.selectOne("users.getData", inputId);
		//아이디 존재여부를 알아내서 (null이면 존재하지 않고, null이 아니면 존재함)
		boolean isExist = dto == null ? false : true;
		return isExist;
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public UsersDto getData(String id) {
		return session.selectOne("users.getData", id);
	}

	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}

}
