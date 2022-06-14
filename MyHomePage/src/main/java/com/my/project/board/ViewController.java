package com.my.project.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ê²Œì‹œë¬? ? •ë³? ë¶ˆëŸ¬?˜¤ê¸? (1. ì¡°íšŒ?ˆ˜ ì¦ê?, 2. ê²Œì‹œë¬? ? •ë³? ê°?? ¸?˜¤ê¸?)
		BoardDAO dao = new BoardDAO();
		
		String num = req.getParameter("num");
		
		// ì¡°íšŒ?ˆ˜ ì¦ê?
		dao.updateVisitCount(num);
		
		// ê²Œì‹œë¬¼ë“¤?˜ ??„¸?•œ ? •ë³´ê°’ ê°?? ¸?˜¤ê¸?
		BoardDTO dto = dao.selectView(num);
		//dao.close(); // ê°ì²´ ë°˜ë‚©
		
		// DB?˜ contentì»¬ëŸ¼?˜ ?—”?„°ë¥? "<br />" ?ƒœê·¸ë¡œ ë³??™˜
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		//ê²Œì‹œë¬? (dto)ê°ì²´ë¥? view?˜?´ì§?ë¡? ? „?‹¬?•˜ê¸? ?œ„?•œ ë³??ˆ˜ê°? ???¥
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/MyHomePage/board_view.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
