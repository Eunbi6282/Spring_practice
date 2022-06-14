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

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// board_list.jsp ?��?�� �??��기�?? ?���??��?�� ?�� �??���? 뷰페?���? (board_write.jsp)
		// 뷰페?���?�? ?��?��
		req.getRequestDispatcher("/MyHomePage/board_write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// id session�? ?��?��주기
		HttpSession session = req.getSession();
		String str = (String) session.getAttribute("id");
		
		
		// Form (board_write.jsp)?��?�� ?��?��?��?�� �??��?�� 값을 처리
		// form?��?�� ?��?��?�� ?��?��?���?�? cos.jar ?��?��브러리의 객체 ?��?�� ?�� �??��?�� 값을 받아?�� ?��?��.
		
		// 1. ?��?�� ?��로드 처리
			//saveDirectory �??��?�� ?��로드?�� ?��?��?�� ???��?�� ?��버의 물리?��?�� 경로�? ???��
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
			
			// maxPostSize : ?��로드?�� 최�? ?��?�� (web.xml?��?�� ?��?��)
			ServletContext application = getServletContext(); // ?���? 먼�? 만들?��?�� xml?��?��?�� ?��?�� �??��값을 불러?�� ?�� ?��?��. 
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// ?��?�� ?��로드 객체 ?��?��
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			
			// 객체 ?��?�� ?��?�� ?�� 처리?�� ?��?��
			if(mr == null) {  // ?��?�� ?��?��?�� ?��미�?�? ?��?�� ?��
				JSFunction.alertLocation(resp, "첨�? ?��?��?�� 초과?��?��?��?��?��.", "../MyHomPage/board_write.do");
				return;
			}
			
		// 2. ?��?�� ?��로드 ?�� 처리 (form?�� �??���? 처리)
			// ?��?��?�� ?��겨받?? 값을 받아?�� DTO(VO)?�� Setter주입?�� ?���? DAO?�� Insert메서?��?�� ?��?���?
			BoardDTO dto = new BoardDTO();
			dto.setName(mr.getParameter("name"));
			dto.setId(str);  // id session�? ?��?��주기
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setPass(mr.getParameter("pass"));
			
			//?���? ?��?�� ?��름과 ???�� ?��?�� ?���? ?��?��
			String fileName = mr.getFilesystemName("ofile"); // client ?�� 첨�??��?��?�� 물리?�� 주소
			if (fileName != null) { // 첨�??��?��?�� 비어?���? ?��?���?
				// ?��로운 ?��?�� ?��름으�? �?경해?�� ?��버에 ???��?�� (?��버의 ?��?�� ?��?��?�� 존재?�� 경우�? ?��?���?�?)
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				
				// ?��?��?���? ?��?��?�� ???��
				String ext = fileName.substring(fileName.lastIndexOf("."));
					//System.out.println("ext : " + ext);
					
				// ?��버에 ???��?�� ?��?��?���? ?��?��
				String newFileName = now + ext;
					//System.out.println(newFileName);
					
				// ?��?�� �? �?�?
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
					//System.out.println("oldFile : " + oldFile);
					//System.out.println("newFile : " + newFile);
				oldFile.renameTo(newFile);
				
				// DTO ?�� Setter 주입( 조건 : ?��?��?�� ?��로드?�� 경우?���?)
				dto.setOfile(fileName); // ?��?�� ?��?�� ?���?
				dto.setSfile(newFileName);	// ?��버에 ???��?�� ?��?�� ?���?
			}
			
			// DTO?�� 객체�? DAO?�� insertWrite(dto) 메서?���? ?��출래?�� DB?�� ???��
			BoardDAO dao = new BoardDAO();
			int result = dao.insertWrite(dto);
			
			// 객체 종료 메서?�� ?���?
			dao.close();
			
			// �??���? ?��공일 ?�� ?��?��?�� ?��?���?
			if(result == 1) {
				resp.sendRedirect("../MyHomePage/board_list.do"); // ?��공일 ?�� list?��?���?�? ?��?��
			}
			
			// �??���? ?��?��?�� ?�� ?��?��?�� ?��?���?
			if (result == 0) {
				resp.sendRedirect("../MyHomPage/board_write.do");
			}
	}
}
