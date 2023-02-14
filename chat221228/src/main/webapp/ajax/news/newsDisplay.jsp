<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
const autoReload=()=>{
	console.log('autoReload호출')
	//ajax함수는 jquery.min.js가 제공하는 apidlek.
	//ajax함수는 결국 XMLHttpRequest();
	//ajax.open("GET",url,true)
	//ajax.send()
	 $.ajax({
         type: "GET",
         url: "newsList.jsp",
         success: function (data) { //성공했을 때
             //console.log(data); 
             $("#d_news").html(data)
         },
         error:function(request,status,error){
        	 console.log('error')
        	 console.log('error'+request.status)
        	 console.log('error'+request.responseText)
         }
       });
}
</script>
</head>
<body>
<script type="text/javascript">
//호출하지 않아도 자동실행
//jquery(document)

$(document).ready(()=>{
	start =()=>{
		setInterval(autoReload,2000);
	}
	start()
})//end of ready
</script>
<div id="d_news">뉴스 준비중</div>
<%-- <%out.print("<font color='red'size=18>안녕</font>");%> --%>
</body>
</html>
<!-- 
html(단방향,변수선언이나 제어문 지원 안됨)은 순차적으로 실행
자바스크립트 코드의 위치에 따라서 document.querySelector(id or class or elementname)
:선언이 먼저 되어야 한다
자바스크립트 위치는??
1)head안에 - 전역변수 선언, 함수선언할 때(호출해야 실행됨)
만일 이것을 지연하고 싶을 때 -defer
2)body안에 - 호출하지 않아도 실행됨 단, 함수로 선언된 부분 제외

write는 브라우저에 쓰는것이다

java는 브라우저에 쓸 수 없다.


 -->