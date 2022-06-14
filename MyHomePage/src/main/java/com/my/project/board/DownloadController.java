package com.my.project.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.project.fileupload.FileUtil;


public class DownloadController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		String num = req.getParameter("num");
		
		//?뙆?씪 ?떎?슫濡쒕뱶 泥섎━ (FileUtil?쓽 download硫붿꽌?뱶 ?샇異?)
		FileUtil.download(req, resp, "/uploads", sfile, ofile);
		
		// 寃뚯떆臾? ?떎?슫濡쒕뱶 ?닔 1利앷?
		BoardDAO dao = new BoardDAO();
		dao.downCountPlus(num);
		dao.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
