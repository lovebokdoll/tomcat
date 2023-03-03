package com.pojo.step3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.step2.Board2Controller;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ActionSupport extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    protected void doServiece( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "doService호출 " );
        String uri = req.getRequestURI();
        log.info( uri );
        String context = req.getContextPath();
        log.info( context );
        
        String command = uri.substring( context.length() + 1 );
        log.info( command );
        int end = command.lastIndexOf( "." );
        log.info( end );
        command = command.substring( 0, end );
        log.info( command );
        String upmu[] = null;// upmu[0] =업무명|폴더명,upmu[1]=요청기능이름
        upmu = command.split( "/" );
        log.info( upmu[0] + "," + upmu[1] );
        req.setAttribute( "upmu", upmu );
        Object obj = "";
        
        try {
            obj = HandlerMapping.getController( upmu, req, res );
        }
        catch ( Exception e ) {
            log.error( "{}", e );
        }
        
        if ( obj != null ) {
            String       pageMove[] = null;
            ModelAndView mav        = null;
            
            if ( obj instanceof String ) {
                
                if ( ( ( String ) obj ).contains( ":" ) ) {
                    log.info( ": 포함되어 있어요" );
                    pageMove = obj.toString().split( ":" );
                }
                else {
                    log.info( ": 포함되어 있지 않아요" );
                    pageMove = obj.toString().split( "/" );
                }
                log.info( pageMove[0] + ", " + pageMove[1] );
            }
            else if ( obj instanceof ModelAndView ) {
                mav = ( ModelAndView ) obj;
                pageMove = new String[2];
                pageMove[0] = "forward";
                pageMove[1] = mav.getViewName();
                
            }
            
            if ( pageMove != null ) {
                String path = pageMove[1];
                
                if ( "redirect".equals( pageMove[0] ) ) {
                    res.sendRedirect( path ); // 이동할 페이지 이름이 담김
                }
                else if ( "forward".equals( pageMove[0] ) ) {
                    RequestDispatcher view = req.getRequestDispatcher( "/" + path + ".jsp" );
                    // /board2/boardList.jsp
                    view.forward( req, res );
                }
                else {
                    path = pageMove[0] + "/" + pageMove[1];
                    RequestDispatcher view = req.getRequestDispatcher( "/WEB-INF/view/" + path + ".jsp" );
                    view.forward( req, res );
                }
            }
            
        }// end of 페이지 이동처리에 대한 공통 코드부분
    }
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "doGet호출" );// 주소창을 통해 호출하는건 모두 get방식이다. -doGet메소드 호출
        doServiece( req, res );
    }
    
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "doPost호출" );
        doServiece( req, res );
    }
}
