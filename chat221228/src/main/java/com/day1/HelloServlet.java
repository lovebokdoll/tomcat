package com.day1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//form전송시 클라이언트측의 요청을 서블릿이 듣는다
//method="get"이면 doGet호출
//post dopost호출
//자바가 서블릿이 되기위한 조건은 반드시 HttpServlet을 상속받는 것이다
//상속을 받으면 doGet과 doPoast오버라이딩 할 수 있는데
//이 함수릐 파라미터를 통해서 request요청 객체와 response응답객체를 주입받는다.
//톰캣버서에서 주입해줌
//웹 서비스를 위해서는 url등록 필수
// 프로젝으레 필요한 URL등록은 annotation과 web.xml문서를 통해서 가능
//annotation은 자동이고 편하기는 하지만 수동처리와 비교해서 추가작업이 불가능한 단점이 있다
//xml문서를 통해서 객체를 등록하고 주입받는다.
//이유는 spring 사용시 메이븐레포를 이용한 프로젝트 생성인 경우에 xml문서로 환경을 설정함
//클래스 사이의 객체주입관계도 xml문서로 처리 가능함
public class HelloServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException ,IOException
	{
			//System.out.println("doGet호출성공");
			logger.info("doGet호출성공");
			res.setContentType("text/html;charset = UTF-8");
			
	PrintWriter out =res.getWriter();
	out.print("<font size = 28px color=red>주의</font>");
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException ,IOException
	{
			logger.info("doPost호출성공");
	}
}
