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
		// board_list.jsp ?—?„œ ê¸??“°ê¸°ë?? ?´ë¦??–ˆ?„ ?–„ ê¸??“°ê¸? ë·°í˜?´ì§? (board_write.jsp)
		// ë·°í˜?´ì§?ë¡? ?´?™
		req.getRequestDispatcher("/MyHomePage/board_write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// id sessionê°? ?„£?–´ì£¼ê¸°
		HttpSession session = req.getSession();
		String str = (String) session.getAttribute("id");
		
		
		// Form (board_write.jsp)?—?„œ ?„˜?–´?˜¤?Š” ë³??ˆ˜?˜ ê°’ì„ ì²˜ë¦¬
		// form?—?„œ ?ŒŒ?¼?„ ? „?†¡?•˜ë¯?ë¡? cos.jar ?¼?´ë¸ŒëŸ¬ë¦¬ì˜ ê°ì²´ ?ƒ?„± ?›„ ë³??ˆ˜?˜ ê°’ì„ ë°›ì•„?•¼ ?•œ?‹¤.
		
		// 1. ?ŒŒ?¼ ?—…ë¡œë“œ ì²˜ë¦¬
			//saveDirectory ë³??ˆ˜?— ?—…ë¡œë“œ?•  ?ŒŒ?¼?„ ???¥?•  ?„œë²„ì˜ ë¬¼ë¦¬? ?¸ ê²½ë¡œë¥? ???¥
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
			
			// maxPostSize : ?—…ë¡œë“œ?•  ìµœë? ?š©?Ÿ‰ (web.xml?—?„œ ?„¤? •)
			ServletContext application = getServletContext(); // ?´ê±? ë¨¼ì? ë§Œë“¤?–´?•¼ xml?„¤? •?— ?ˆ?Š” ë³??ˆ˜ê°’ì„ ë¶ˆëŸ¬?˜¬ ?ˆ˜ ?ˆ?‹¤. 
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// ?ŒŒ?¼ ?—…ë¡œë“œ ê°ì²´ ?ƒ?„±
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			
			// ê°ì²´ ?ƒ?„± ?‹¤?Œ¨ ?‹œ ì²˜ë¦¬?•  ?ƒ?š©
			if(mr == null) {  // ?š©?Ÿ‰ ?´?ƒ?˜ ?´ë¯¸ì?ë¥? ?„£?„ ?–„
				JSFunction.alertLocation(resp, "ì²¨ë? ?š©?Ÿ‰?´ ì´ˆê³¼?˜?—ˆ?Šµ?‹ˆ?‹¤.", "../MyHomPage/board_write.do");
				return;
			}
			
		// 2. ?ŒŒ?¼ ?—…ë¡œë“œ ?™¸ ì²˜ë¦¬ (form?˜ ë³??ˆ˜ê°? ì²˜ë¦¬)
			// ?¼?—?„œ ?„˜ê²¨ë°›?? ê°’ì„ ë°›ì•„?„œ DTO(VO)?— Setterì£¼ì…?„ ?•˜ê³? DAO?— Insertë©”ì„œ?“œ?— ?˜? ¸ì¤?
			BoardDTO dto = new BoardDTO();
			dto.setName(mr.getParameter("name"));
			dto.setId(str);  // id sessionê°? ?„£?–´ì£¼ê¸°
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setPass(mr.getParameter("pass"));
			
			//?›ë³? ?ŒŒ?¼ ?´ë¦„ê³¼ ???¥ ?ŒŒ?¼ ?´ë¦? ?„¤? •
			String fileName = mr.getFilesystemName("ofile"); // client ?˜ ì²¨ë??ŒŒ?¼?˜ ë¬¼ë¦¬?  ì£¼ì†Œ
			if (fileName != null) { // ì²¨ë??ŒŒ?¼?´ ë¹„ì–´?ˆì§? ?•Š?œ¼ë©?
				// ?ƒˆë¡œìš´ ?ŒŒ?¼ ?´ë¦„ìœ¼ë¡? ë³?ê²½í•´?„œ ?„œë²„ì— ???¥?•¨ (?„œë²„ì˜ ?•´?‹¹ ?ŒŒ?¼?´ ì¡´ì¬?•  ê²½ìš°ê°? ?ˆ?œ¼ë¯?ë¡?)
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				
				// ?™•?¥?ë§? ?˜?¼?„œ ???¥
				String ext = fileName.substring(fileName.lastIndexOf("."));
					//System.out.println("ext : " + ext);
					
				// ?„œë²„ì— ???¥?•  ?ŒŒ?¼?´ë¦? ?ƒ?„±
				String newFileName = now + ext;
					//System.out.println(newFileName);
					
				// ?ŒŒ?¼ ëª? ë³?ê²?
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
					//System.out.println("oldFile : " + oldFile);
					//System.out.println("newFile : " + newFile);
				oldFile.renameTo(newFile);
				
				// DTO ?— Setter ì£¼ì…( ì¡°ê±´ : ?ŒŒ?¼?„ ?—…ë¡œë“œ?•œ ê²½ìš°?—ë§?)
				dto.setOfile(fileName); // ?›?˜ ?ŒŒ?¼ ?´ë¦?
				dto.setSfile(newFileName);	// ?„œë²„ì— ???¥?  ?ŒŒ?¼ ?´ë¦?
			}
			
			// DTO?˜ ê°ì²´ë¥? DAO?˜ insertWrite(dto) ë©”ì„œ?“œë¥? ?˜¸ì¶œë˜?„œ DB?— ???¥
			BoardDAO dao = new BoardDAO();
			int result = dao.insertWrite(dto);
			
			// ê°ì²´ ì¢…ë£Œ ë©”ì„œ?“œ ?˜¸ì¶?
			dao.close();
			
			// ê¸??“°ê¸? ?„±ê³µì¼ ?•Œ ?´?™?•  ?˜?´ì§?
			if(result == 1) {
				resp.sendRedirect("../MyHomePage/board_list.do"); // ?„±ê³µì¼ ?–„ list?˜?´ì§?ë¡? ?´?™
			}
			
			// ê¸??“°ê¸? ?‹¤?Œ¨?¼ ?•Œ ?´?™?•  ?˜?´ì§?
			if (result == 0) {
				resp.sendRedirect("../MyHomPage/board_write.do");
			}
	}
}
