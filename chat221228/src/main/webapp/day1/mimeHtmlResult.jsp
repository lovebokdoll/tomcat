<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.Map,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MimeHtmlServlet2 응답페이지</title>
</head>
<body>
<h2>MimeHtmlServlet2 응답페이지</h2>
<%
//스크립틀릿 안에서 선언한 변수는 지역변수이다.
// 왜냐하면 a.jsp ->a_jsp.java => service메소드 안에 들어간다.
	String myName ="null" ; //그래서 얘는 지역변수임
	myName=(String)session.getAttribute("myName");
	Integer age= null;
	age=(Integer)session.getAttribute("age");
	out.print(myName);
	out.print(age);
	out.print("<hr>");
	Map<String, Object>rmap =(Map<String,Object>)session.getAttribute("rmap");
	if(rmap!=null){//NullPointerException방어코드 
	Object keys[]=rmap.keySet().toArray();
	for(int i=0;i<keys.length;i++){//nullpointer원인
		out.print(rmap.get(keys[i]));
		out.print("<br/>");
		}
	}
	
	//getAttribute의 리턴타입은 Object이다.(getParameter의 리턴타입은 String이다)
	out.print("<hr>");
	List<Map<String,Object>> mList =(List<Map<String,Object>>)session.getAttribute("mList");
	if(mList!=null){
		for(int i=0;i<mList.size();i++){
			Map<String,Object> rMap = mList.get(i);
			out.print(rMap.get("mem_id")+","+rMap.get("mem_pw")+","+rMap.get("mem_name")+"<br/>");
			
		}
	}
	
	out.print("<hr>");
	%>
</body>
</html>