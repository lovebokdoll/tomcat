<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 확장자는 jsp이지만 마입타입은html이다 
	브라우저는 이 파일을 html문서로 판단한다.
	왜냐면 1.page다이렉티브에 contentType에 설정된 마입타입 때문이다.
	실행하면 (url주소에 해당페이지를 요청하면)
	c_jsp->(jsp-api.jar:jsp엔진,jsp컨테이너===> 톰캣에 있음)
	->c_jap.java로 변경됨 ->(servlet-api.jar:서블릿엔진)
	->c_jsp.class로 변경됨	->out.print(-out은 jsp가 제공하는 내장객체)-dos창이 아닌 브라우저에 쓴다
	->document.write와 같은일이 벌어진다. 자밧크립트와 같은 원리인데 자바브라우저에서 동작함	
	-->
<script>
document.write("<b>굵은글씨</b>");
/*
 
 */
</script>
