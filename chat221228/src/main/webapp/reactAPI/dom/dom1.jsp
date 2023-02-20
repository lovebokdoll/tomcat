<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>html문서</title>
</head>
<body>
<div id="root"></div>
<script type="text/javascript">
const rootElement = document.querySelector("#root");
const h1Element = document.createElement("h1");
h1Element.textContent = "DOM Make";
rootElement.appendChild(h1Element);//텍스트문자열이 들어감
</script>
</body>
</html>
<!-- 
확장자는 jsp이지만 mime type이 html이므로 html문서이다.
 -->