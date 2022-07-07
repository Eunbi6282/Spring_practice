package kr.co.dao;

import java.util.List;

import kr.co.vo.FestivalVO;
import kr.co.vo.SearchCriteria;

public interface FestivalDAO {
	
	// 축제 목록 조회
	public List<FestivalVO> listFestival (FestivalVO vo) throws Exception;
	
	
	
}
