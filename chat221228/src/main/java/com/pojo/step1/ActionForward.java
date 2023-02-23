package com.pojo.step1;

public class ActionForward {
    // 페이지 이동방법
    // 1.res.sendRedirect("XXX.jsp")-표준 서블릿이 요청받음 -MVC패턴이 아님(확정)
    // or res.sendRedirect("dept/getDeptList.kh")-서블릿이 요청 받아줌-MVC패턴인가?
    // jsp가 요청을 받는것이 왜 문제인가?
    // WAS마다 명명규칙이 다르다 그래서 인스턴스화 할 수 없다
    // 또한 인스턴스화를 한다 하더라도 request와 response객체를 주입받지 못하는 문제가 있다 => 마임타입을 정하지 못함
    private String path = null;// 응답페이지의 이름, 서블릿의 이름
    // sendResirect로 페이지를 이동? 아니면 forward로 페이지 이동여부 결정하기
    private boolean isRedirect = false;// true:redirect(insert,update,delete할때 쓴다),false:forward(select할때 쓴다)
    
    public String getPath() { return path; }
    
    public void setPath( String path ) { this.path = path; }
    
    public boolean isRedirect() { return isRedirect; }
    
    public void setRedirect( boolean isRedirect ) { this.isRedirect = isRedirect; }
    
}
/*
 * AcrionForward클래스를 왜 만드나?
 * 웹서비스 에서는 main메소드대신에 클라이언트가 Tomcat서버에게 url로 요청을 보낸다
 * 요청을 할 때 url을 사용한다!!
 * => 이것으로 사용자의 요구를 판단해보려고 한다
 * 
 */