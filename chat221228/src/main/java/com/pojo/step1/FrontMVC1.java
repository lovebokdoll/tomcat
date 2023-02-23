package com.pojo.step1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FrontMVC1 extends HttpServlet {
      Logger logger = Logger.getLogger( FrontMVC1.class );
    protected void doServiece( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        logger.info( "doService호출 " );
        String uri=req.getRequestURI();//사용자가 요청한 url가져옴
        logger.info( uri );//dept/getDeptList.st1
        String context=req.getContextPath(); // server.xml에 있는 path가져옴 =>"/"
        logger.info( context );
        //http://localhost:9000/board/boardInsert.st1
        //http://localhost:9000/board/boardUpdate.st1
        //http://localhost:9000/board/boardDelete.st1
        String command = uri.substring( context.length() + 1 );
        logger.info( command );
        int end = command.lastIndexOf( "." );//16 -st1잘라내기 위해서 사용
        logger.info( end );//16출력
        command = command.substring( 0, end );//     /dept/getDeptList
        logger.info( command );
        String upmu[] = null;//upmu[0] =업무명|폴더명,upmu[1]=요청기능이름
        upmu = command.split( "/" );//dept, getDeptList
        
        for ( String imsi : upmu ) {
            logger.info( imsi );
        }
        ActionForward af = null;
        //게으른 인스턴스화
        //아직 업무명이 결정되지 않음 => [0]번방에 들어감
        //업무명이 Controller클래스의 접두어임!!
        DeptController deptController =null;
        EmpController empController =null;
        req.setAttribute("upmu",upmu);
        if("dept".equals( upmu[0] )) {
            //request객체는 저장소이다 -저장할 때 setAttribute사용,꺼내올 때 getAttribute사용
            deptController = new DeptController();
            af=deptController.execute( req, res  );
            //dept컨트롤러는 서블릿이 아니여서 req와 res를 주입받을 수 없다.
            //왜냐면 httpServlet을 상속받지 않았으니까
            }else if("emp".equals(upmu[0])) {
                empController = new EmpController();
                af = empController.execute(req, res);
            }
        // 페이지 이동처리를 공통코드로 만들기
        // 1.res.sendRedirect("/dept/getDeptList.jsp"); => jsp ->서블릿 ->jsp
        // res.sendRedirect("/dept/getDeptList.st1"); =>jsp ->서블릿->서블릿 ->jsp
        if ( af != null ) {
            
            if ( af.isRedirect()) {
                res.sendRedirect( af.getPath() );
            }
            else {
                RequestDispatcher view = req.getRequestDispatcher( af.getPath() );
                view.forward( req, res );
            }
        }else{
           logger.info( "af is null" );
        } // end of 페이지 이동처리에 대한 공통 코드부분
        
    }
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doServiece( req, res );
    }
    
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doServiece( req, res );
    }
    
}
