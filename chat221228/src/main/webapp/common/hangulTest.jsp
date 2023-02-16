<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//localhost:9000/common/hangulTest.jsp?mem_name=김춘추
//쿼리스트링으로 넘어오는 한글처리 -> server.xml문서 ->URIEncoding -"UTF-8"
String name = request.getParameter("mem_name");
out.print(name);
%>
