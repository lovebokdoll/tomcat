<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리액트 코드로 만든 페이지</title>
</head>
<body>
    <div id ="root"></div>
     <script
      crossorigin
      src="https://unpkg.com/react@18/umd/react.development.js"
    ></script>
    <script
      crossorigin
      src="https://unpkg.com/react-dom@18/umd/"
    ></script>
    <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
    <script type = "text/javascript">
        const rootElement = document.querySelector("#root");
        //h1Element.textContent = "DOM Make"; 이 코드와 같은 효과
        //React는 react.development.jsdptj 참조하는 객체
        const h1Element = React.createElement("h1",{children:"DOM Make"});
		//Client side rendering시에 앞에가 주입할 대상이고 뒤에가 root의 위치임
        //rootElement.appendChild(h1Element);
		//react-dom.development.js에서 참조라는 객체
		ReactDOM.createRoot(rootElement).render(h1Element);
    </script>
</body>
</html>
<!-- 
바닐라 스크립트는 특정한 프레임워크나 라이브러리를 사용하지 않는 
순수한 자바스크립트

샌드박스 - playground
코드샌드박스 : React등 다양한 환경에 대한 기본 설정이 다 되어있다.
 -->