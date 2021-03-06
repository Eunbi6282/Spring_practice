package com.my.project.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// κ²μλ¬? ? λ³? λΆλ¬?€κΈ? (1. μ‘°ν? μ¦κ?, 2. κ²μλ¬? ? λ³? κ°?? Έ?€κΈ?)
		BoardDAO dao = new BoardDAO();
		
		String num = req.getParameter("num");
		
		// μ‘°ν? μ¦κ?
		dao.updateVisitCount(num);
		
		// κ²μλ¬Όλ€? ??Έ? ? λ³΄κ° κ°?? Έ?€κΈ?
		BoardDTO dto = dao.selectView(num);
		//dao.close(); // κ°μ²΄ λ°λ©
		
		// DB? contentμ»¬λΌ? ??°λ₯? "<br />" ?κ·Έλ‘ λ³??
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		//κ²μλ¬? (dto)κ°μ²΄λ₯? view??΄μ§?λ‘? ? ?¬?κΈ? ?? λ³??κ°? ???₯
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/MyHomePage/board_view.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
