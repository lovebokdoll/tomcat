package com.pojo.step1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

// 개발자가 정의한 서블릿 - 표준서블릿이 아니다
//doServise메소드는 요청처리에 대한 창구를 일원화 하기 위해 개발자가 정의한 메소드
//따라서 request객체와 response객체를 톰캣서버로부터 주입받을 수 없다.
//이 문제 해결을 위해서 메소드 파라미터 자리를 이용하여 doGet이나 doPost메소드에서 주입받은
//request객체와 response객체를 넘겨받아서 사용하는 컨셉으로 클래스 구현함
public class FrontMVC1 extends HttpServlet {
    // 요청을 들어줄 수 있다
    // doGet,doPost
    
    Logger logger = Logger.getLogger( FrontMVC1.class );
    // 이 메소드는 톰캣서버로 부터 직접 요청객체와 응답객체 주입받을 수 없다
    //따라서 doGet메소드와 doPost메소드 안에서 doServise메소드 호출할 때 파라미터로 넘겨받는다
    protected void doServiece( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        logger.info( "doService호출 " );
        String uri = req.getRequestURI();// 주소창에 입력된 값중 도메인과 포트번호가 제외된 값만 받음
        logger.info( uri );// dept/getDeptList.st1
        String context = req.getContextPath(); // server.xml에 있는 path가져옴 =>"/"
        logger.info( context );
        // http://localhost:9000/board/boardInsert.st1
        // http://localhost:9000/board/boardUpdate.st1
        // http://localhost:9000/board/boardDelete.st1
        String command = uri.substring( context.length() + 1 );//context정보만 제외된 나머지 경로정보 담음
        logger.info( command );
        int end = command.lastIndexOf( "." );// 16 -st1잘라내기 위해서 사용
        logger.info( end );// 16출력
        command = command.substring( 0, end );// /dept/getDeptList
        logger.info( command );
        String upmu[] = null;// upmu[0] =업무명|폴더명,upmu[1]=요청기능이름
        upmu = command.split( "/" );// dept, getDeptList
        
        for ( String imsi : upmu ) {
            logger.info( imsi );
        }
        ActionForward af = null;
        // 게으른 인스턴스화
        // 아직 업무명이 결정되지 않음 => [0]번방에 들어감
        // 업무명이 Controller클래스의 접두어임!!
        DeptController deptController = null;
        EmpController  empController  = null;
        req.setAttribute( "upmu", upmu );
        
        if ( "dept".equals( upmu[0] ) ) {
            // request객체는 저장소이다 -저장할 때 setAttribute사용,꺼내올 때 getAttribute사용
            //아래 코드는 request저장소에 upmu배열에 주소번지 원본을 저장하는 구문
            deptController = new DeptController();
            //왜 deptController는 req와 res주입받을 수 없나? 서블릿이 아니라서
            //그래서 파라미터로 doGet메소드가 주입받은 req와 res주소번지 원본을 넘겨줌
            // 이렇게 넘기지 않으면 DeptController에서는 req,res사용 할 수 없음
            //이런 이유로 메소드를 하나만 가질 수 있다.
            //다른 메소드를 정의하는 것은 자유이지만 req와 res는 주입받을수 없음
            //입력,수정,삭제,조회 4가지 메소드가 필요함
            //DeptLogic메소드 4가지를 호출해야 하는데 upmu[1]방에 정보가 필요함
            af = deptController.execute( req, res );
            // dept컨트롤러는 서블릿이 아니여서 req와 res를 주입받을 수 없다.
            // 왜냐면 httpServlet을 상속받지 않았으니까
        }
        else if ( "emp".equals( upmu[0] ) ) {
            empController = new EmpController();
            af = empController.execute( req, res );
        }
        
        // 페이지 이동처리를 공통코드로 만들기
        // 1.res.sendRedirect("/dept/getDeptList.jsp"); => jsp ->서블릿 ->jsp
        // res.sendRedirect("/dept/getDeptList.st1"); =>jsp ->서블릿->서블릿 ->jsp
        // 사용자에게 들어갈 jsp이름을 갖고 있음
        if ( af != null ) {
            
            if ( af.isRedirect() ) {
                res.sendRedirect( af.getPath() );
            }
            else {
                RequestDispatcher view = req.getRequestDispatcher( af.getPath() );
                view.forward( req, res );
            }
        }// end of 페이지 이동처리에 대한 공통 코드부분
        
    }
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        logger.info( "doGet호출" );// 주소창을 통해 호출하는건 모두 get방식이다. -doGet메소드 호출
        doServiece( req, res );
    }
    
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        logger.info( "doPost호출" );
        doServiece( req, res );
    }
    
}
