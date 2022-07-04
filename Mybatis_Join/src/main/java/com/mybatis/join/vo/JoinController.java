package com.mybatis.join.vo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {
	
	@Autowired
	private MybatisDAO mybatisDAO;
	
	@RequestMapping("/joinList.do")
	public String selectList(JoinVO vo, Model model) {
		System.out.println("컨트롤러 호출 성공 : /joinList.do");
		List<JoinVO> joinList = mybatisDAO.selectListJoin(vo);
		System.out.println("마이바티스를 통한 List객체 저장 성공");
		
		JoinVO joinVO = joinList.get(0);
		System.out.println(joinVO.toString());
		
		model.addAttribute("joinVO", joinVO);
		
		return "joinList.jsp";
	}
	
	@RequestMapping("/joinList3.do")
	public String selectList3(AllJoinvo vo, Model model) {
		System.out.println("컨트롤러 호출 성공 : /joinList3.do");
		List<AllJoinvo> joinList3 = mybatisDAO.selectListJoin3(vo);
		
		model.addAttribute("joinList3", joinList3);
		return "joinList3.jsp";
	}
	
	@RequestMapping("/joinList4.do")
	public String selectList4(AllJoinvo2 vo, Model model) {
		System.out.println("컨트롤러 호출 성공 : /joinList3.do");
		List<AllJoinvo2> joinList4 = mybatisDAO.selectListJoin4(vo);
		model.addAttribute("joinList4", joinList4);
		return "joinList4.jsp";
		
 	}
	
	

}
