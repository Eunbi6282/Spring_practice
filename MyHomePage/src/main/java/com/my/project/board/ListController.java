package com.my.project.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.project.utils.BoardPage;



public class ListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. DAO 객체 ?��?��
			BoardDAO dao = new BoardDAO();  //객체 만드?�� ?���? ?��결성�?
		
		// 2. 뷰에 ?��?��?�� 매개�??�� ???��?�� �? ?��?�� (Key, Value)
			Map<String, Object> map = new HashMap<String, Object>();
			String searchFiled = req.getParameter("searchField");
			String searchWord = req.getParameter("searchWord");
			
			if(searchWord != null) {	// �??��?���? 존재?��?���? map?�� 값을 ???��
				map.put("searchField", searchFiled);
				map.put("searchWord", searchWord);
			}
			
			// 게시�? 개수 ?��?��?���?(DAO?�� selectCount
			int totalCount = dao.selectCount(map);
			// System.out.println("?���? ?��코드 ?�� : " + totalCount);
			// System.out.println("List.do ?���??�� Controller ?��?���? ?�� ?��?��");
				
		
		/* ?��?���? 처리 �?�? start*/
			// web.xml?��?�� ?��?��?�� �??�� 값게?��링해?���?
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
			System.out.println(pageSize);
			System.out.println(blockPage);
		
			//?��?�� ?��?���? ?��?��
			int pageNum = 1;
			String pageTemp = req.getParameter("pageNum"); // Parameter�? ?��?��?��?�� 값�? 모두 String, 계산?��?���? int�? �??��?��?�� 
			if (pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp); // 값이 비어?���? ?��?�� ?�� ?��?��?�� ?��?���? �??���? ?��?���? �??��?��?�� �??��?�� ?��?��?��. 
			}
			
			// 목록?�� 출력?�� 게시�? 범위 계산
			int start = (pageNum - 1) * pageSize + 1;	// �? 게시�? 번호
			int end = pageNum * pageSize; //마�?�? 게시�? 번호
			
			// �? ?��?���??�� 값을 ?��?���?
			map.put("start", start);	// ("�??���?", ?��?���? �?)
			map.put("end", end);
		
		/* ?��?���? 처리 �?�? end*/
		
			// 게시�? 목록 받아?���?(DAO 객체?�� ?��?��?�� ?��?��)
				// DAO?�� selectListPage()?���? => return board ?���?�? DTO까�? 불러?�� => boardLists?�� 결과�? ?���?
					//DTO?��?�� DB?��?�� 값들?�� ?��?��?��?��. board?�� DTO객체�? ?���? ?��?��. 
			List <BoardDTO> boardLists = dao.selectListPage(map);  //map?��start, end?�� 값이 ?��?��?��?��
			dao.close(); //DB?���? ?���?(DBConnPool?�� ?��?��?���? ?��?��
			
			// 뷰페?���??�� ?��?��?�� 매개�??��?��?�� 추�?
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../MyHomePage/board_list.do"); // 바로�?�? HTML문자?��
			
			// 뷰페?���?�? �??��?�� 값을 ?��?��
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			// 뷰페?���?�? ?��?��?�� ?��?��, request ?��?��?�� ?��?��?�� ?��?��?���? ???�� ?�� board_list.jsp(뷰페?���?)�? ?��?��?��
			req.setAttribute("boardLists", boardLists); // ("�??���?", �?) //DB?��?�� Select?�� 결과�?
			req.setAttribute("map", map);
			req.getRequestDispatcher("/MyHomePage/board_list.jsp").forward(req, resp);	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
