package com.pojo.step3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.step2.Board2Controller;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet( "*.st3" )
public class ActionSupport extends HttpServlet {
    protected void doService( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "doService호출" );
        String uri = req.getRequestURI(); //
        log.info( uri ); //
        String context = req.getContextPath();//
        log.info( context );
        String command = uri.substring( context.length() + 1 ); //
        log.info( command );
        int end = command.lastIndexOf( "." );//
        log.info( end );
        command = command.substring( 0, end );
        log.info( command );
        String upmu[] = null;
        upmu = command.split( "/" );
        log.info( upmu[0] + ", " + upmu[1] );
        req.setAttribute( "upmu", upmu );
        Object obj = "";
        
        try {
            obj = HandlerMapping.getController( upmu, req, res );
        }
        catch ( Exception e ) {
            log.info( "Exception :" + e.toString() );
        }
        
        if ( obj != null ) { // redirect:XXX.jsp or forward:XXX.jsp
            String[]     pageMove     = null;
            ModelAndView modelAndView = null;
            
            if ( obj instanceof String ) {
                
                if ( ( ( String ) obj ).contains( ":" ) ) {
                    log.info( " : 포함되어 있어요." );
                    pageMove = obj.toString().split( ":" );
                }
                else if ( ( ( String ) obj ).contains( "/" ) ) {
                    log.info( " / 포함되어 있어요." );
                    pageMove = obj.toString().split( "/" );
                }
                else {
                    // spring boot -> @RestController, spring 4버전은 ResponseBody 사용
                    log.info( " ':' , '/' Not Included" );
                    pageMove = new String[1];
                    pageMove[0] = obj.toString();
                    log.info( pageMove[0] );
                }
            }
            else if ( obj instanceof ModelAndView ) {
                modelAndView = ( ModelAndView ) obj;
                pageMove = new String[2];
                pageMove[0] = ""; // forward -> ViewResolver else if() 타게된다. -> webapp
                pageMove[1] = modelAndView.getViewName();
                log.info( "pageMove[0] = {} , pageMove[1] = {}", pageMove[0], pageMove[1] );
            }
            log.info("Object가 String일때와 MAV일때가 끝난 지점");
      
             if ( pageMove != null && pageMove.length == 2 ) {
                new ViewResolver( res, req, pageMove );
            }else if(pageMove != null && pageMove.length == 1) {
                res.setContentType( "text/plain;charset=UTF-8" );
                PrintWriter out = res.getWriter();
                out.print(pageMove[0]);
            }
        }
    }
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "doGet호출" );
        doService( req, res );
    }
    
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "doPost호출" );
        doService( req, res );
    }
}