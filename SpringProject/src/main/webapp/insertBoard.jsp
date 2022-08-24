<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글등록</title>
</head>
<body>
<center>
<h1>게시글 등록</h1>
<hr>
<form action="insertBoard.kt" method="post">
<table width="500" cellpadding="0" cellspacing="0" border="1">
<tr>
	<td width="100" bgcolor="orange">제목</td>
	<td><input type="text" name="title"/></td>
</tr>
<tr>
	<td bgcolor="orange">작성자</td>
	<td><input type="text" name="writer"/></td>
</tr>
<tr>
	<td bgcolor="orange">내용</td>
	<td><textarea rows="5" cols="50" name="content"></textarea></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="글등록"/></td>
</tr>
</table>
</form>


<%@ include file="footer.jsp" %>


