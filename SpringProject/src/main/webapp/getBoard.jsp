<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--
	EL(Expression Language)이란?
	JSP에서 내장객체(request, session, application)에 등록된 데이터에 접근하는 표현식
	
	JSTL(JSP Standard Tag Library)이란?
	JSP 파일에서 if, for, switch 등과 같은 자바 코드를 대체하기 위한 표준 태그
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글상세</title>
</head>
<body>
<center>
<h1>게시글 상세</h1>
<font color="blue">${user.name }</font>님 환영합니다.
<hr>
<form action="updateBoard.kt" method="post">
<input type="hidden" name="seq" value="${board.seq }"/>

<table width="500" cellpadding="0" cellspacing="0" border="1">
<tr>
	<td width="100" bgcolor="orange">제목</td>
	<td><input type="text" name="title" value="${board.title }"/></td>
</tr>
<tr>
	<td bgcolor="orange">작성자</td>
	<td>${board.writer }</td>
</tr>
<tr>
	<td bgcolor="orange">내용</td>
	<td><textarea rows="5" cols="50" name="content">${board.content }</textarea></td>
</tr>
<tr>
	<td bgcolor="orange">등록일</td>
	<td>${board.regDate }</td>
</tr>
<tr>
	<td bgcolor="orange">조횟수</td>
	<td>${board.cnt }</td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="글수정"/></td>
</tr>
</table>
</form>

<hr>
<a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;&nbsp;
<c:if test="${user.role == 'ADMIN' }">
<a href="deleteBoard.kt?seq=${board.seq }">글삭제</a>&nbsp;&nbsp;&nbsp;
</c:if>
<a href="getBoardList.kt">글목록</a>


<%@ include file="footer.jsp" %>



























