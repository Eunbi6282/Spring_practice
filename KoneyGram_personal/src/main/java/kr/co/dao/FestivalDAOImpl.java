package kr.co.dao;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import kr.co.vo.FestivalVO;
import kr.co.vo.SearchCriteria;

@Repository
public class FestivalDAOImpl implements FestivalDAO{
	
	@Inject
	private SqlSession sql;

	// 
	@Override
	public List<FestivalVO> listFestival(FestivalVO vo) throws Exception {
		return sql.selectList("festivalMapper.listfestival", vo);
	}
	
	
	
	
	
}
