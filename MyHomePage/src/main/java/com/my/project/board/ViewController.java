package com.my.project.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시�? ?���? 불러?���? (1. 조회?�� 증�?, 2. 게시�? ?���? �??��?���?)
		BoardDAO dao = new BoardDAO();
		
		String num = req.getParameter("num");
		
		// 조회?�� 증�?
		dao.updateVisitCount(num);
		
		// 게시물들?�� ?��?��?�� ?��보값 �??��?���?
		BoardDTO dto = dao.selectView(num);
		//dao.close(); // 객체 반납
		
		// DB?�� content컬럼?�� ?��?���? "<br />" ?��그로 �??��
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		//게시�? (dto)객체�? view?��?���?�? ?��?��?���? ?��?�� �??���? ???��
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/MyHomePage/board_view.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
