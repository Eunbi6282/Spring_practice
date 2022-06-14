package com.my.project.board;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.project.fileupload.FileUtil;
import com.my.project.utils.JSFunction;


public class PassController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//view ?��?���? (board_pass.jsp)�? �? ?���?
		//mode:edit <= �??��?��, mode:delte <= �??��?��
		req.setAttribute("mode",req.getParameter("mode"));  // get방식?���? ?��?��?�� mode?�� 값을 "mode"�??��?�� ?��?��?��. 
		req.getRequestDispatcher("../MyHomePage/board_pass.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form?��?�� post 방식?���? ?��?��?��?�� �??�� �? 3�?
		String num = req.getParameter("num");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		String sfile = req.getParameter("sfile");  // ?��?��?�� ?��?��?�� 같이 ?��?��
		
		// 비�?번호 ?��?�� (DAO?�� ?��?��)
		BoardDAO dao = new BoardDAO();
		boolean confirmed = dao.confirmPass(pass, num);
		dao.close();  // close()?��?��, 41?��?�� ?���? 만듬
		
		if(confirmed) {
			// 비�?번호�? ?��치할 ?�� (mode�??���? ?��?��?��?�� edit�? ?��?��?��?���?�?, delete�? ?��?�� ?��?���?�? �??���?)
			if(mode.equals("edit")) {
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);  //pass�? session �??�� "pass"?�� ?��?��
				resp.sendRedirect("../MyHomePage/board_edit.do?num=" + num);
			}else if (mode.equals("delete")) {
				dao = new BoardDAO();  //dao객체 ?���? 만듬
				
				//dao delete객체 만들�?
				BoardDTO dto = dao.selectView(num);
				int result = dao.deletePost(num);  //게시�? ?��?��
				dao.close();
				// 게시�? ?��?��?�� 첨�??��?��?�� 같이 ?��?�� 
				FileUtil.deleteFile(req, "/uploads", sfile);
				
				
				// ?��?�� ?��?�� ?��?���? ?��?�� (js)
				JSFunction.alertLocation(resp, "?��?��?��?��?��?��?��", "../MyHomePage/board_list.do");
			}
			
		} else {  // 비�?번호�? 맞�? ?��?�� ?�� js?��?��?�� ?��?�� ?��?���?�? ?��?���??���?
			JSFunction.alertBack(resp, "비�?번호 �?증에 ?��?��?��?��?��?��.");
			
		}
	}
}