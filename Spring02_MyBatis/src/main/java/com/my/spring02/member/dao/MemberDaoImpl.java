package com.my.spring02.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring02.member.dto.MemberDto;

//component scan 시 bean이 되기위해 
@Repository
public class MemberDaoImpl implements MemberDao{
	//MyBatis를 사용하기 위한 핵심 의존객체 주입 
	@Autowired  //자동으로 연결   
	private SqlSession session;
		
	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto);
	}

	@Override
	public void update(MemberDto dto) {	
		session.update("member.update", dto);
	}

	@Override
	public void delete(MemberDto dto) {
		session.delete("member.delete", dto);
	}

	@Override
	public MemberDto getData(int num) {
		return session.selectOne("member.getData", num);
	}

	@Override
	public List<MemberDto> getList() {
		//회원목록을 얻어와서
		List<MemberDto> list = session.selectList("member.getList");
		//리턴해줌
		return list;
	}
	
}
