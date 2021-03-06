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
		// board_list.jsp ?? κΈ??°κΈ°λ?? ?΄λ¦??? ? κΈ??°κΈ? λ·°ν?΄μ§? (board_write.jsp)
		// λ·°ν?΄μ§?λ‘? ?΄?
		req.getRequestDispatcher("/MyHomePage/board_write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// id sessionκ°? ?£?΄μ£ΌκΈ°
		HttpSession session = req.getSession();
		String str = (String) session.getAttribute("id");
		
		
		// Form (board_write.jsp)?? ??΄?€? λ³??? κ°μ μ²λ¦¬
		// form?? ??Ό? ? ?‘?λ―?λ‘? cos.jar ?Ό?΄λΈλ¬λ¦¬μ κ°μ²΄ ??± ? λ³??? κ°μ λ°μ?Ό ??€.
		
		// 1. ??Ό ?λ‘λ μ²λ¦¬
			//saveDirectory λ³??? ?λ‘λ?  ??Ό? ???₯?  ?λ²μ λ¬Όλ¦¬? ?Έ κ²½λ‘λ₯? ???₯
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
			
			// maxPostSize : ?λ‘λ?  μ΅λ? ?©? (web.xml?? ?€? )
			ServletContext application = getServletContext(); // ?΄κ±? λ¨Όμ? λ§λ€?΄?Ό xml?€? ? ?? λ³??κ°μ λΆλ¬?¬ ? ??€. 
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// ??Ό ?λ‘λ κ°μ²΄ ??±
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			
			// κ°μ²΄ ??± ?€?¨ ? μ²λ¦¬?  ??©
			if(mr == null) {  // ?©? ?΄?? ?΄λ―Έμ?λ₯? ?£? ?
				JSFunction.alertLocation(resp, "μ²¨λ? ?©??΄ μ΄κ³Ό???΅??€.", "../MyHomPage/board_write.do");
				return;
			}
			
		// 2. ??Ό ?λ‘λ ?Έ μ²λ¦¬ (form? λ³??κ°? μ²λ¦¬)
			// ?Ό?? ?κ²¨λ°?? κ°μ λ°μ? DTO(VO)? Setterμ£Όμ? ?κ³? DAO? Insertλ©μ?? ?? Έμ€?
			BoardDTO dto = new BoardDTO();
			dto.setName(mr.getParameter("name"));
			dto.setId(str);  // id sessionκ°? ?£?΄μ£ΌκΈ°
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setPass(mr.getParameter("pass"));
			
			//?λ³? ??Ό ?΄λ¦κ³Ό ???₯ ??Ό ?΄λ¦? ?€? 
			String fileName = mr.getFilesystemName("ofile"); // client ? μ²¨λ???Ό? λ¬Όλ¦¬?  μ£Όμ
			if (fileName != null) { // μ²¨λ???Ό?΄ λΉμ΄?μ§? ??Όλ©?
				// ?λ‘μ΄ ??Ό ?΄λ¦μΌλ‘? λ³?κ²½ν΄? ?λ²μ ???₯?¨ (?λ²μ ?΄?Ή ??Ό?΄ μ‘΄μ¬?  κ²½μ°κ°? ??Όλ―?λ‘?)
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				
				// ??₯?λ§? ??Ό? ???₯
				String ext = fileName.substring(fileName.lastIndexOf("."));
					//System.out.println("ext : " + ext);
					
				// ?λ²μ ???₯?  ??Ό?΄λ¦? ??±
				String newFileName = now + ext;
					//System.out.println(newFileName);
					
				// ??Ό λͺ? λ³?κ²?
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
					//System.out.println("oldFile : " + oldFile);
					//System.out.println("newFile : " + newFile);
				oldFile.renameTo(newFile);
				
				// DTO ? Setter μ£Όμ( μ‘°κ±΄ : ??Ό? ?λ‘λ? κ²½μ°?λ§?)
				dto.setOfile(fileName); // ?? ??Ό ?΄λ¦?
				dto.setSfile(newFileName);	// ?λ²μ ???₯?  ??Ό ?΄λ¦?
			}
			
			// DTO? κ°μ²΄λ₯? DAO? insertWrite(dto) λ©μ?λ₯? ?ΈμΆλ? DB? ???₯
			BoardDAO dao = new BoardDAO();
			int result = dao.insertWrite(dto);
			
			// κ°μ²΄ μ’λ£ λ©μ? ?ΈμΆ?
			dao.close();
			
			// κΈ??°κΈ? ?±κ³΅μΌ ? ?΄??  ??΄μ§?
			if(result == 1) {
				resp.sendRedirect("../MyHomePage/board_list.do"); // ?±κ³΅μΌ ? list??΄μ§?λ‘? ?΄?
			}
			
			// κΈ??°κΈ? ?€?¨?Ό ? ?΄??  ??΄μ§?
			if (result == 0) {
				resp.sendRedirect("../MyHomPage/board_write.do");
			}
	}
}
