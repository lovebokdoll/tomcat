package com.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mvc.dao.TestDao;

public class TestServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
Logger logger = Logger.getLogger(TestServlet.class);

public void doService(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException
	{
	logger.fatal("doService호출");
	logger.debug("doService호출");
	logger.info("doService호출");
	logger.warn("doService호출");
	logger.error("doService호출");
	TestDao tDao = new TestDao();
    List<Map<String,Object>>mList=tDao.getBookMember();
    
	//페이지 이동
	//페이지 이름 getMemberList.jsp
	//페이지의 물리적인 경로는 어디를 바라보고 있는가? -web.xml->servlet-mapping->url-pattetn
	//경로명/요청을 처리하는 이름.do[뒤에 온 확장가는 의미없다-why?해당요청을 인터셉트해서 
	// 해당업무를 담당하는 클래스에 연결(FrontController.java설계 - 각 업무별 xxxController필요)을 해야 함
	//페이지 처리는 JSP에게 맡김 
	//서블릿은 요청을 받아서 엄무 담당자에세 연결(매칭/매핑)]
	//이것을 어떻게 나눌것인가? 요청=> 업무 담당자 사용자 선택에 따라 결정
	// 결정에 대응하는 클래스를 FrontController연결
	//FrontController에서 실제 업무를 담당하는 xxxController에 전달할 때
	// 요청객페와 응답객체 또한 전달이 되어야 한다.
	//요청객체로 무엇을 노릴 수 있나
	//사용자가 입력한 값을 듣기 위해 필요
	//getParameter("")==> String
	//getAttirbute("")==> object 
	//request.getParameter
	//response.setContenrType() 마입타입을 결정한다.
	//request.getParameter("XXX");mem_id,mem_pw,mem_name
	//HttpSeccion session = request.getSession(); //세션객체 생성
	//http프로토콜이 비상태프로토콜 이므로 상태값을 관리하는 별도의 메카니즘 필요
	// 쿠키와 세션(시간을 지배한다)<-List,Map<-<객체배열[{}]<-배열<-원시형 타입(변수)
	//아래 코드를 만나기 전까지는 http://localHost:9000/test/test.do
    //를 보여주다가 아래 문자를 만나면 그때 http://localHost:9000/test/getMemberList.jsp를 요청함
    //테스트시나리오
    //방법1 :TestServlet.java소스에서 오른쪽버튼 눌러 실행시킨다
    //방법2 : 브라우저 주소찰레 http://localhost:9000/test/test.do라고 요청한다.
    res.sendRedirect("/test/getMemberList.jsp");
//	String cTime=tDao.testDate();
//	logger.info("today:"+cTime);
	}
@Override
public void doGet(HttpServletRequest req,HttpServletResponse res)
		throws ServletException,IOException
{
	doService(req,res);
}
@Override
public void doPut(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException
	{
	doService(req,res);
	}
@Override
public void doDelete(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException
	{
	doService(req,res);
	}
}
