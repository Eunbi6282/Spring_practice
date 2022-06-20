package com.springbook.biz.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;


//DispatcherServlet 의 MVC M2의 단점.
   //하나의 Controller에서 Client의 모든 요청을 처리하고 있다. 
	//테이블이 늘어나거나 , Controller의 부하가 굉장히 많이 발생 
    //구문이 굉장히 길어지고 복잡해 진다. 해당 메소드를 찾기도 힘듦 

//Controller의 부하를 분산 처리 하는것이 : MVC 프레임워크, Spring MVC 




//@WebServlet(name = "action", urlPatterns = { "*.do" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트에서 Get 방식으로 들어오는 모든 요청을 process 메소드에서 처리하도록 던져줌
		process (request,response); 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트에서 Post 방식으로 들어오는 모든 요청을 process 메소드에서 처리하도록 던져줌
		request.setCharacterEncoding("EUC-KR");
		process (request,response); 
	}
	
	private void process (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//1. 클라이언트의 요청 path 정보를 추출 
		String uri = request.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/"));   
				//path : 클라이언트 요청 (*.do)
		System.out.println(path);      
		
		//2. 클라이언트 요청에 따라 적절한 처리 (Controller)
		
		if (path.equals("/login.do")) {
			System.out.println("로그인 처리");
			// 1. 사용자 입력 정보 추출
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			// 2. DB 연동 처리
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);

			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);

			// 3. 화면 네비게이션
			if (user != null) {
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}
			
			
			
		}else if ( path.equals("/logout.do")) {
			System.out.println("로그 아웃 처리");
			
			// 1. 브라우저와 연결된 세션 객체를 강제 종료한다.
			HttpSession session = request.getSession(); 
			session.invalidate();
			
			// 2. 세션 종료 후, 메인 화면으로 이동한다.
			response.sendRedirect("login.jsp");
			
			
		}else if ( path.equals("/insertBoard.do")) {
			System.out.println("글 등록 처리");
			// 1. 사용자 입력 정보 추출
						// request.setCharacterEncoding("EUC-KR");
						String title = request.getParameter("title");
						String writer = request.getParameter("writer");
						String content = request.getParameter("content");

						// 2. DB 연동 처리
						BoardVO vo = new BoardVO();
						vo.setTitle(title);
						vo.setWriter(writer);
						vo.setContent(content);

						BoardDAO boardDAO = new BoardDAO();
						boardDAO.insertBoard(vo);

						// 3. 화면 네비게이션
						response.sendRedirect("getBoardList.do");
			
			
		}else if ( path.equals("/updateBoard.do")) {
			System.out.println("글 수정 처리");
			// 1. 사용자 입력 정보 추출
						// request.setCharacterEncoding("EUC-KR");
						String title = request.getParameter("title");
						String content = request.getParameter("content");
						String seq = request.getParameter("seq");

						// 2. DB 연동 처리
						BoardVO vo = new BoardVO();
						vo.setTitle(title);
						vo.setContent(content);
						vo.setSeq(Integer.parseInt(seq));

						BoardDAO boardDAO = new BoardDAO();
						boardDAO.updateBoard(vo);

						// 3. 화면 네비게이션
						response.sendRedirect("getBoardList.do");
			
			
		}else if ( path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");
			// 1. 사용자 입력 정보 추출
						String seq = request.getParameter("seq");

						// 2. DB 연동 처리
						BoardVO vo = new BoardVO();
						vo.setSeq(Integer.parseInt(seq));
						
						BoardDAO boardDAO = new BoardDAO();
						boardDAO.deleteBoard(vo);

						// 3. 화면 네비게이션
						response.sendRedirect("getBoardList.do");
						
			
			
			
			
		}else if ( path.equals("/getBoard.do")) {
			System.out.println("글 상세 조회 처리");
			// 1. 검색할 게시글 번호 추출
						String seq = request.getParameter("seq");

						// 2. DB 연동 처리
						BoardVO vo = new BoardVO();
						vo.setSeq(Integer.parseInt(seq));

						BoardDAO boardDAO = new BoardDAO();
						BoardVO board = boardDAO.getBoard(vo);

						// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
						HttpSession session = request.getSession();
						session.setAttribute("board", board);
						response.sendRedirect("getBoard.jsp");

			
			
			
		}else if ( path.equals("/getBoardList.do")) {
			System.out.println("글 목록 조회 처리");
			
			// 1. 사용자 입력 정보 추출(검색 기능은 나중에 구현)
						// 2. DB 연동 처리
						BoardVO vo = new BoardVO();
						BoardDAO boardDAO = new BoardDAO();
						List<BoardVO> boardList = boardDAO.getBoardList(vo);

						// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
						HttpSession session = request.getSession();
						session.setAttribute("boardList", boardList);
						response.sendRedirect("getBoardList.jsp");
					}
			
		}
		
		
	}
	


