<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
</head>
<body>
<center>
<h1>게시글 목록</h1>
<font color="blue">${user.name }</font>님 환영합니다.
<a href="logout.kt">로그아웃</a>
<hr>
<form action="getBoardList.kt" method="post">
<table width="700" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td align="right">
		<select name = "searchCondition">
			<option value="TITLE" <c:if test="${search.searchCondition == 'TITLE' }">selected</c:if>>제목
			<option value="CONTENT" <c:if test="${search.searchCondition == 'CONTENT' }">selected</c:if>>내용
		</select>
		<input name="searchKeyword" type="text" value="${search.searchKeyword }"/>
		<input type="submit" value="검색"/>
	</td>
</tr>
</table>
</form>
<table width="700" cellpadding="0" cellspacing="0" border="1">
<tr>
	<th bgcolor="orange" width="50">번호</th>
	<th bgcolor="orange" width="300">제목</th>
	<th bgcolor="orange" width="100">작성자</th>
	<th bgcolor="orange" width="150">등록일</th>
	<th bgcolor="orange" width="100">조횟수</th>	
</tr>

<c:forEach var="board" items="${boardList }">
<tr>
	<td align="center">${board.seq }</td>
	<td><a href="getBoard.kt?seq=${board.seq }">${board.title }</a></td>
	<td align="center">${board.writer }</td>
	<td align="center">${board.regDate }</td>
	<td align="center">${board.cnt }</td>
</tr>
</c:forEach>

</table>
<hr>
<a href="insertBoard.jsp">글 등록</a>


<%@ include file="footer.jsp" %>




