<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   String deptList=
    (String)request.getAttribute( "deptList" );
        out.print(deptList);
    %>
