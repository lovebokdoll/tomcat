<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List,java.util.Map"%>
<%
List<Map<String, Object>> boardList =(List<Map<String, Object>> )request.getAttribute( "boardList" );
out.print(boardList);
// req.setAttribute( "boardList", boardList );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h3>게시글 목록</h3>
</body>
</html>