
<%@ page import = "com.springbook.biz.impl.BoardDAO" %>
<%@ page import = "com.springbook.biz.BoardVO" %>
<%
	// 1. 검색할 게시글 번호 추출
	String seq = request.getParameter("seq");

	// 2. DB연동 처리 
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	BoardDAO boardDAO = new BoardDAO();
	BoardVO board = boardDAO.getBoard(vo);
	
	// 3. 응답 화면 구성
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>글 상세</title>
</head>
<body>
	<center>
		<h1>글상세</h1>
		<a href = "logout_proc.jsp">로그아웃</a>
		<hr>
		<form action ="updateBoard_proc.jsp" method = "post">
			<input name = "seq" type ="hidden" value ="<%= board.getSeq() %>" />
			<table border = "1" cellpadding = "0" cellspacing = "0">
				<tr>
					<td bgcolor = "orange" width = "70%" >제목</td>
					<td align = "left"><input name = "title" type = "text" value = "<%= board.getTitle() %>" /> </td>
				</tr>
				<tr>
					<td bgcolor = "orange" >작성자</td>
					<td align = "left"><input name = "writer" type = "text" value = "<%= board.getWriter() %>" /> </td>
				</tr>
				<tr>
					<td bgcolor = "orange">내용</td>
					<td align = "left"><textarea name = "content" cols ="40"><%= board.getContent() %></textarea> </td>
				</tr>
				<tr>
					<td bgcolor = "orange">등록일 </td>
					<td align = "left"><%= vo.getRegdate() %></td>
				</tr>
				<tr>
					<td bgcolor = "orange">조회수 </td>
					<td align = "left"><%= vo.getCnt() %></td>
				</tr>
				<tr>
					<td colspan = "2" align ="center"><input type = "submit" value ="글수정" /> </td>
				</tr>
			</table>
		</form>
		<hr>
		<a href = "insertBoard.jsp">글등록</a>
		<a href = "deleteBoard_proc.jsp?seq=<%= board.getSeq() %>">글삭제</a>
		&nbsp;&nbsp;&nbsp;
		<a href = "getBoardList.jsp">글목록</a>
	</center>
</body>
</html>