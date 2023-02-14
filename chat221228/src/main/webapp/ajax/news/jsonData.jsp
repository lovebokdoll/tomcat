<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.google.gson.*"  %>
 
<%
List<Map<String,Object>> memberList=new ArrayList<>();
Map<String,Object> map = new HashMap<>();
map.put("mem_id","tomato");
memberList.add(map);
map = new HashMap<>();
map.put("mem_id","apple");
memberList.add(map);
map = new HashMap<>();
map.put("mem_id","banana");
memberList.add(map);
//구글에서는 Gson.jar이라는 라이브러리를 지원하고 있다.
Gson g = new Gson();
String temp = g.toJson(memberList);
//out.print(temp);
//아래와 같이 자료구조를 출력하면 JSON포맷이 아님 -> 자바스클립트에서 읽을수 없음-> 조회결과를 못보여줌
out.print(memberList);

%> 