package com.day2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//http://localhost:9000/day2/a로 요청을 하면 RequestScope 서블릿 클래스의 doGet메소드가 호출되고
//doGet메소드 안에서 27번의 sendRedirect대신에
//forward메소드가 호출되므로 주소는 그대로 http://localhost:9000/day2/a를 가리키고 있는데
//실제 출력페이지는 http://localhost:9000/day2/b.jsp를 찾고 있다
//이것이 sendRedirect("b.jsp")로 이동할 때와 forward로 이동할 때 차이점이다.
//sendRedirect로 페이지 이동시에는 값을 유지하려면 쿠키나 세션을 사용해야 하지만
//forward로 이동시에는 forward메소드 호출시 첫번째 파라미터인 request객체에 담아서
//넘길(유지 시킬)수 있다.
@WebServlet("/day2/a")
public class RequestScope extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 logger.info("doGet호출");
	 Map<String, Object> rmap = new HashMap<>();
	 rmap.put("mem_id", "tomato");
	 req.setAttribute("rmap", rmap);
	 RequestDispatcher view = req.getRequestDispatcher("b.jsp");
	 //day2/a로 요청한 경우 doGet메소드를 경유하게 되고 이 요청을 받았을 때 톰캣으로부터 서블릿이 생성되고
	 //그 서블릿은 요청객체와 응답객체를 갖게 되는데 b.jsp페이지에서 응답이 나갈때 파라미터에 요청객체 원본이
	 //전달되니까 화면에서도 그 값을 공유할 수 있다.
	 view.forward(req, resp);
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
