package com.day1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 java인데 브라우저에 출력할 수 있다.-화면의 역할,화면을 그리는데 서블릿을 사용한다.=> 마임타입 때문에 가능
@WebServlet("/login.do")//웹에서 접근 가능한 맵핑 주소 설정
public class MimePlainServlet extends HttpServlet {
@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException ,IOException
		{
		res.setContentType("text/plain;charset=UTF-8");
		//메소드를 통해서 객체생성 하는 코드 
		PrintWriter out = res.getWriter();
		out.print("<h2>안녕</h2>");
		}
}