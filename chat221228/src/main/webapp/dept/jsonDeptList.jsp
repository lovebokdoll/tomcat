<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page trimDirectiveWhitespaces="true"%>
<%
//jsp페이지 지만 page directive에 마임타입이 application/json으로 되어있어서 
//브라우저는 이 페이지응 json포맷으로 인지함
String temp = ( String ) request.getAttribute( "jsonDoc" );
out.print( temp );
%>
