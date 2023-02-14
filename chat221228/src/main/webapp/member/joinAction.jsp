<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//1번 2번라인은 page directive라고 함
//스크립틀릿 선언 - 자바영역
//<input type = "text" name="mem_id">
String mem_id = request.getParameter("mem_id");//여기에 사용되는 속성이 name이다
out.print("사용자가 입력한 아이디=>"+mem_id);//tomato이것은 로컬이 아니라 브라우저에 출력됨(다른점-여기의 자바는 서블릿이다.)

String mem_pw = request.getParameter("mem_pw");
out.print("사용자가 입력한 pw=>"+mem_pw);
%>