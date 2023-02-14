//비동기통신객체 생성해서 담을 변수 선언
let xhrObject = null; // 변수선언
//비동기 통신객체 생성하는 함수 구현 - 단 리턴예약어(void)가 없어서 반환받을 수 없음
function createRequest() {
  //자바스크립트에도 예외처리가 가능하다.
  try {
    xhrObject = new XMLHttpRequest();
  } catch (trymicrosoft) {
    try {
      //MS의  IE사용자 위한 객체생성
      xhrObject = new ActiveXObject("Msaml2.XMLHTTP");
    } catch (error) {
      xhrObject = null;
    }
  }
  if (xhrObject == null) {
    alert("비동기 통신 객체 생성 에러");
  }
} //end of createRequest

//span태그가 가지고 있는 텍스트 노드값을 읽어오기
//<td>100</td>태그이름이 없다 -> 속성을 정의하지 못한다. -> 아이디나 클래스값을 정의 못한다. -> 접근이 불가하다
//해결방법 : 텍스트 노드에 span으로 감싸서 id값을 주었다.
//document.querySelector("#id")
//document.querySelector(".class")
//document.querySelector("tag name") => 이것이 el
//const costEL = document.
function getText(el) {
  let text = ""; //ES6문법 -> 적용 안되는 브라우저이면 babel(자바스크립트 컴파일러)필요 or parcel(번들러)필요
  if (el != null) {
    if (el.childNodes) {
      for (let i = 0; i < el.childNodes.length; i++) {
        let childNode = el.childNodes[i]; //el.childNodes[0],el.childNodes[1]
        //너 텍스트 노드니?
        if (childNode.nodeValue != null) {
          text = text + childNode.nodeValue;
        }
      }
    }
  }
  return text;
}
//기존 TextNode의 값을 다른 문자열로 바꾸기
/***********************************************
param1 :document.getElementById("boardSold")
param1 :document.querySelector("#boardSold")
param2 :xhrObject. 
************************************************/
function replaceText(el, value) {
  //el -> boardSoldEL(보드판매량),cashEL(마진)
  if (el != null) {
    //span
    clearText(el); //기존에 있던 10을 지워주세요.
    //새로운 텍스트 노드 15를 생성하기
    var newNode = document.createTextNode(value); //15
    //위에서 생성한 텍스트 노드 값을 el에 붙이는 함수 호출하기
    el.appendChild(newNode); //el boardSoldEl -><span id -"boardSold or cash">10</span>
  }
}
//기존 태그안에 문자열 지우는 함수 구현
function clearText(el) {
  if (el != null) {
    if (el.childNodes) {
      for (let i = 0; i < el.childNodes.length; i++) {
        let childNode = el.childNodes[i];
        el.removeChild(childNode); //해당 el삭제하기 -DOM API -> 직관적이지 않다
      }
    }
  }
}
//리턴값을 받음
function createRequest2() {
  try {
    xhrObject = new XMLHttpRequest(); //constructor
  } catch (trymicrosoft) {
    try {
      //MS의  IE사용자 위한 객체생성
      xhrObject = new ActiveXObject("Msaml2.XMLHTTP");
    } catch (error) {
      xhrObject = null;
    }
  }
  if (xhrObject == null) {
    alert("비동기 통신 객체 생성 에러");
  }
  return xhrObject;
} //end of createRequest
