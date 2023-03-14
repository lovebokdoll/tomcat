<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List, java.util.Map"%>
<%
List<Map<String, Object>> zipcodeList = ( List<Map<String, Object>> ) request.getAttribute( "zList" );
if ( zipcodeList != null ) {
    out.print( zipcodeList );
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[Web-INF] 우편번호검색기</title>
</head>
<body>
우편번호검색기
</body>
</html>