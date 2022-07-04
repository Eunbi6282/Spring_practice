package com.mybatis.join.vo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<JoinVO> selectListJoin(JoinVO vo) {
		System.out.println("selectListJoin() 메소드 호출");
		return mybatis.selectList("join.selectListJoin", vo);
	}
	
	public List<AllJoinvo>selectListJoin3(AllJoinvo vo) {
		System.out.println("selectListJoin2() 메서드 호출");
		return mybatis.selectList("join.selectJoin3", vo);
	}
	
	public List<AllJoinvo2> selectListJoin4(AllJoinvo2 vo) {
		System.out.println("selectListJoin4() 메서드 호출");
		return mybatis.selectList("join.selectJoin4", vo);
	}
	
	
}
