<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String code = request.getParameter( "code" );// 앞에서 선택한 code라는 값이 넘어옴
String url="";

if(code.equals("A")){
    url= "/$forward/a.jsp";
}else if(code.equals("B")){
    url= "/$forward/b.jsp";
}else if(code.equals("C")){
    url= "/$forward/c.jsp";
}
//forward
//RequestDispatcher dispatcher= request.getRequestDispatcher( url );
//dispatcher.forward(request,response);
//getRequestDispatcher(): 브라우저에서 요청을 제어할 수 있는 객체
 //request.getRequestDispatcher( url ).forward(request,response);
%>
<jsp:forward page="<%= url %>" />
