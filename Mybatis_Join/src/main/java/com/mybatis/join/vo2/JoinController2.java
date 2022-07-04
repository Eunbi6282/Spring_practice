package com.mybatis.join.vo2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController2 {
	
	@Autowired
	private MybatisDAO2 mybatisDAO2;
	
	@RequestMapping("/joinList2.do")
	public String selectList(SjoinVO vo, Model model) {
		System.out.println("컨트롤러 호출 성공 : /joinList.do");
		List<SjoinVO> joinList = mybatisDAO2.selectListJoin(vo);
		
		SjoinVO joinVO = joinList.get(0);
		System.out.println(joinVO.toString());
		
		model.addAttribute("joinList", joinList);
		
		return "joinList.jsp";
	}

}
