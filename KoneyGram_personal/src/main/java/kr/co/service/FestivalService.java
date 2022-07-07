package kr.co.service;

import java.util.List;

import kr.co.vo.FestivalVO;
import kr.co.vo.SearchCriteria;

public interface FestivalService {
	//축제 조회
	public List<FestivalVO> listFestival (FestivalVO vo) throws Exception;
}
