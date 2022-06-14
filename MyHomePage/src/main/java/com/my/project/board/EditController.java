package com.my.project.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartRequest;

import com.my.project.fileupload.FileUtil;
import com.my.project.utils.JSFunction;


public class EditController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 비�?번호 �?�? ?�� �?증이 ?��료되�? num?�� ?��?��?��?�� ?��코드�? dtp?�� ?��?��?�� 뷰페?���?�? ?���?
		String num = req.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.selectView(num);
		
		req.setAttribute("dto", dto); //�??��?�� 값을 ?���? 뷰페?���?�? ?��겨줌
		req.getRequestDispatcher("/MyHomePage/board_edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// board_edit.jsp ?��?�� ?��?��?�� ?��?��?�� 받아?�� DB?�� ?��?��
		
		// 1. ?��?�� ?��로드 처리
			String saveDirectory = req.getServletContext().getRealPath("/uploads");
			System.out.println(saveDirectory);
			
			// (1) ?��?��?��?�� ?��?��?�� 최�??��?��(web.xml)
			ServletContext application = this.getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// (2) ?��?�� ?��로드 
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			
			if(mr == null) {
				JSFunction.alertBack(resp, "첨�??��?�� ?��?��?�� 초과?��?��?��?��?��");
				return;
			}
			
		// 2. ?��?�� ?��로드 ?�� 처리
			// ?��?��?�� ?��?��?��?��?��?���?�? request 객체�? ?��?�� MultipartRequest객체?��?�� Form?�� �??�� 값을 받는?��.
			String num = mr.getParameter("num");
			String prevOfile = mr.getParameter("prevOfile");
			String prevSfile = mr.getParameter("prevSfile");
			
			String name = mr.getParameter("name");
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			
			// 비�?번호 : Session �??��?��?�� �? �??��?���?
			HttpSession session = req.getSession();
			String pass = (String)session.getAttribute("pass");
			
			// DTO?�� ?��겨받?? �??��값을 ???�� (Client Form => DTO => DAO?�� ?��?��)
			BoardDTO dto = new BoardDTO();
			dto.setNum(num);
			dto.setName(name);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setPass(pass);
			
				// �??���? ?��?��?��?���? ?��?��
//				System.out.println("DTO 객체?�� ???��?�� �? 불러?���? ============");
//				System.out.println(dto.getNum());
//				System.out.println(dto.getName());
//				System.out.println(dto.getTitle());
//				System.out.println(dto.getContent());
//				System.out.println(dto.getPass());
			
			/// dto객체?�� Ofile, Sfile?? ?��로드 경로?�� ?��?�� ?��?��명이 존재?��?�� 경우 처리
			String fileName = mr.getFilesystemName("ofile");
			if(fileName != null) {  // 첨�??��?��?�� uploads ?��?��?�� 존재?��?�� 경우 ?��?�� ?��름을 ?��?��?��?�� ???��
				
			// ?��로운 ?��?���? ?��?��
			String now = new SimpleDateFormat("yyyyMMdd").format(new Date());  // ?��짜처�?
			String ext = fileName.substring(fileName.lastIndexOf("."));  // .?��?�� ?��?�� ?��?��?��까�?�? �?�?�? ?��
			String newFileName = now + ext;
				
//				System.out.println("now : " + now);
//				System.out.println("ext : " + ext);
//				System.out.println("newFileName : " + newFileName);
			
			// ?��?���? �?�? 
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			
			oldFile.renameTo(newFile);
			
			// �?경한 ?��?�� DTO?�� ???��
			dto.setOfile(fileName);	// ?���? ?��?�� ?���?
			dto.setSfile(newFileName); // ?��로운 ?��?�� ?���? (?��버에 ???��?�� ?��?�� ?���?)
			
			// 기존 ?��?�� ?��?��
			FileUtil.deleteFile(req, "/uploads", prevSfile);
			
			} else {
				dto.setOfile(prevOfile);
				dto.setSfile(prevSfile);
			}
			
			// DB?�� ?��?�� ?��?��?�� 반영 
			BoardDAO dao = new BoardDAO();
			int result = dao.updatePost(dto);  // :1 => ?���?, :0 => ?��?��
			dao.close();
			
			// ?��?�� ?���? vs ?��?��
			if(result == 1 ) { // ?���?
				session.removeAttribute("pass");
				resp.sendRedirect("../MyHomePage/board_view.do?num=" + num);
			} else {
				JSFunction.alertLocation(resp, "비�?번호 �?증을 ?��?�� ?��주세?��", "../MyHomePage/board_view.do?num=" + num);
			}
	}
	
}
