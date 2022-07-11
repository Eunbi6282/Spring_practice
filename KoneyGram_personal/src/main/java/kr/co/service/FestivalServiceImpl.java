package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.FestivalDAO;
import kr.co.vo.FestivalVO;
import kr.co.vo.SearchCriteria;

@Service
public class FestivalServiceImpl implements FestivalService{

	@Inject
	private FestivalDAO dao;

	@Override
	public List<FestivalVO> listFestival(SearchCriteria scri) throws Exception {
		return dao.listFestival(scri);
	}

	// 축제 총 개수
	@Override
	public int listFCount(SearchCriteria scri) throws Exception {
		return dao.listFCount(scri);
	}
	
	//list by admin
	@Override
	public List<FestivalVO> listByAdmin(SearchCriteria scri) throws Exception {
		return dao.listByAdmin(scri);
	}
	
	
	
	
	
}
