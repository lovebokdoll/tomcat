<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!int x = 1;//전역변수 선언하기
public String newsItem(String[]news){
	StringBuilder sb = new StringBuilder();
	for(int i=0;i<news.length;i++){
		if(i == (x-1)){
	sb.append("<table width='500px' border='1px'>");
	sb.append("<tr><td>"+news[i]+"</td></tr>");
	sb.append("</table>");
		}//end of if
	}//ebd of for
	String choice = sb.toString();
	return choice;
}
%>
<%
//스크립틀릿
String news[] = { "'도이치모터스 주가조작' 권오수 집유·벌금 3억원", "與 김기현·안철수 '내가 대세'…천하람·황교안 '이제 시작'",
		"[튀르키예 강진] 끝모를 사망자수…야외 주차장이 시신 안치소로", "K팝 시장 '메가톤급 폭풍'…하이브·SM 시너지 기대 속 우려",
		"野,'곽상도 무죄' 맹공…대장동·김건희 특검 '쌍끌이 공세'" };
String data="";//NullpointerException방지하기 위해 빈문자열로 초기화
switch (x) {
case 1:
	data = newsItem(news);
	x++;
	break;
case 2:
	data = newsItem(news);
	x++;
	break;
case 3:
	data = newsItem(news);
	x++;
	break;
case 4:
	data = newsItem(news);
	x++;
	break;
case 5:
	data = newsItem(news);
	x=1; // 이렇게 해야 계속 반복함
	break;
}//end of switch
out.clear();//기존에 있는(읽어온 기사정보) 값 비움
out.print(data);
%>