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
	public List<FestivalVO> listFestival(FestivalVO vo) throws Exception {
		return dao.listFestival(vo);
	}
	
	
}
