package com.springbook.biz.view.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	/*
	   @SessionAttributes("board") 
	      BoardVO에서 이전에 설정값을 Session에다가 저장해 두고 새롭게 변경된 항목만 수정 
	      기존의 저장된 값들은 그대로 유지	      
	 */	
	 //기능별의 Controller를 통합 : 
	// 유지 보수를 쉽게 하기 위해서 DAO 객체를 직접 호출하면 안된다. 
	// 인터페이스를 객체 주입 해서 구현을 해놓아야 유지 보수를 쉽게 할 수 있다. 
	
	@Autowired
	private BoardService boardService;   //인터페이스로 DAO를 호출 
		//타입은 인터페이스 , 
	
	
	//1. 글등록 
	@RequestMapping(value="/insertBoard.do")   // 클라이언트 요청 
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("글 등록 처리- Spring MVC 어노테이션 작동 ");
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/upload/" + fileName));
		}

		boardService.insertBoard(vo);
		return "getBoardList.do";  //Forward 방식으로 뷰 페이지 전송 
	}
	
	
	//2.글 수정 
	
	@RequestMapping ("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo ) {
		System.out.println("글 수정 처리 - Spring MVC 호출 - Controller 분리");
		
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter());  //update에서 넘기는 변수가 설정안됨. 
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegdate());
		System.out.println("조회수 : " + vo.getCnt());
		System.out.println("==============================");
		
		
		boardService.updateBoard(vo);     
		
		// 3. 화면 네비게이션
		
		return "redirect:getBoardList.do"; 	
	}
	
	//3. 글삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo ) {
		System.out.println("글 삭제 처리- Spring MVC 어노테이션 - Controller 통합");
		
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";  //redirect 로 이동 
		//return "getBoardList.do"; 		//Forward 로 이동
	}
	
	//검색 조건 목록 설정 (Model 객체에 값을 더 추가합니다.Model 객체를 호출하기 전에 먼저 작동되어서 Model 객체에 값을 할당한다. ) 
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>(); 
		conditionMap.put("제목", "TITLE"); 
		conditionMap.put("내용", "CONTENT"); 
		conditionMap.put("이름", "NAME"); 
		conditionMap.put("날짜", "REGDATE"); 
		
		return conditionMap; 
	}
	
		//searchConditionMap() 메소드는 @ModelAttribute 어노테이션이 적용되어 있기 때문에 
		// getBoardList() 보다 먼저 실행 된다.  
	
	//4. 글 상세 검색
	@RequestMapping ("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("글 상세 조회 처리- Spring MVC 호출 - Controller 통합");		
				 		 
		 model.addAttribute("board", boardService.getBoard(vo)); 
		
		 return "getBoard.jsp"; 
		 
		//ModelAndView : Model (뷰페이지로 변수의 값을 넘길때) + View (뷰페이지를 저장)
		//Model        : Model (뷰페이지로 변수의 값을 넘길때)
		 
		 
		 
	}
	
	//5. 글 목록 검색 
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println("글 목록 검색 처리 -- Spring MVC 어노테이션 작동  Controller 통합 ");
		
		// Null에 대한 기본 처리
		if(vo.getSearchCondition() == null) {  // 검색을 하지 않았을 떄 기본 처리
			vo.setSearchCondition("TITLE");
		}
		if(vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		
		model.addAttribute("boardList", boardService.getBoardList(vo)); 
		
		return "getBoardList.jsp";	 
	}
	
	/* @ModelAttibute : 
	 	1. Command 객체로 전송되는 객체이름을 다른이름으로 변경해서 사용 할때 
	 	2. View에서 사용할 데이터를 설정하는 용도로 사용됨. 
	 		@ModelAttribute 가 설정된 메소드는 @RequestMapping 어노테이션이 설정된 메소드 보다 먼저 호출됨. 
	 		@ModelAttribute 메소드 실행 결과로 리턴되는 객체는 자동으로 Model에 저장이된다. 
	 		@ModelAttribute 메소드 실행 결과로 리턴한 객체를 View  페이지에서 사용 할 수 없다. 
	 	
	*/
	
}
