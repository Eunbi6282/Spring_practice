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

	// 축제 list
	@Override
	public List<FestivalVO> listFestival(SearchCriteria scri) throws Exception {
		return sql.selectList("festivalMapper.listfestival", scri);
	}

	// 축제 count
	@Override
	public int listFCount(SearchCriteria scri) throws Exception {
		return sql.selectOne("festivalMapper.flistCount", scri);
	}

	// list by admin
	@Override
	public List<FestivalVO> listByAdmin(SearchCriteria scri) throws Exception {
		return sql.selectList("festivalMapper.selectByAdmin", scri);
	}
	
	// listByCount
	@Override
	public int listByCount(SearchCriteria scri) throws Exception {
		return sql.selectOne("festivalMapper.flistCountByAdmin", scri);
	}
	
	//read
	@Override
	public FestivalVO read(int f_code) throws Exception {
		return sql.selectOne("festivalMapping.readFestival", f_code);
	}
	
	
	
	
	
	
	
	
}
