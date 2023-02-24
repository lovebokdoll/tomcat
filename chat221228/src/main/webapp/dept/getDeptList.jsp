<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    List<Map<String,Object>> deptList=
    (List<Map<String,Object>>)request.getAttribute( "deptList" );
        out.print(deptList);
    %>
