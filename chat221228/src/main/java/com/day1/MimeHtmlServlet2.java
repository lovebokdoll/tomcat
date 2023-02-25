package com.day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//서블릿은 java인데 브라우저에 출력할 수 있다.-화면의 역할,화면을 그리는데 서블릿을 사용한다.=> 마임타입 때문에 가능
//테스트 시나리오 :서블릿을 경유하여 응답페이지를 jsp로 가져가는 실습니다.
//최초 mimeHtmlResult.jsp를 직접 호출하는 것이 아니라
//23번에 있는 url ("/day1/html2.do")로 요청했을때 아래 코드 
//res.sendRedirect("./mimeHtmlResult.jsp"); 를 만나서 mimeHtmlResult.jsp로 
//주소창이 변경되는 것을 관찰한 뒤 서블릿에서 세션에 담은 정보를 mimeHtmlResult.jsp페이지에서 
// 유지되는지 확인하는 실습니다.
@WebServlet("/day1/html2.do") // 웹에서 접근 가능한 맵핑 주소 설정
public class MimeHtmlServlet2 extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doGet호출");
		HttpSession session = req.getSession();
		String myName = new String("이순신");
		int age = 35;
		Map<String, Object> rmap = new HashMap<>();
		rmap.put("mem_id", "tomato");
		rmap.put("mem_pw", "111");
		rmap.put("mem_name", "토마토");
		session.setAttribute("myName", myName);
		session.setAttribute("age", age);
		session.setAttribute("rmap", rmap);
		res.sendRedirect("./mimeHtmlResult.jsp");
		List<Map<String, Object>> mList = new ArrayList<>();
		if (rmap != null) {
			rmap.clear();// rmap비우기
		}
		rmap = new HashMap<>();
		rmap.put("mem_id", "tomato");
		rmap.put("mem_pw", "123");
		rmap.put("mem_name", "토마토");
		mList.add(rmap);
		rmap = new HashMap<>();
		rmap.put("mem_id", "kiwi");
		rmap.put("mem_pw", "123");
		rmap.put("mem_name", "키위");
		mList.add(rmap);
		rmap = new HashMap<>();
		rmap.put("mem_id", "banana");
		rmap.put("mem_pw", "123");
		rmap.put("mem_name", "바나나");
		mList.add(rmap);
		session.setAttribute("rmap", rmap);
		session.setAttribute("mList", mList);
	}
}