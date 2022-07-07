package kr.co.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.service.FestivalService;
import kr.co.vo.FestivalVO;

@Controller
@RequestMapping("/festival")
public class FestivalController {
	private static final Logger logger = LoggerFactory.getLogger(FestivalController.class);
	
	@Inject
	FestivalService fservice;
	
	// 게시판 목록
	@RequestMapping(value = "/listf", method = RequestMethod.GET)
	public String listFestival(Model model, FestivalVO vo) throws Exception{
		logger.info("listFestival");
		
		model.addAttribute("list",fservice.listFestival(vo));
		
		return "festival/listf";
		
	}
}
