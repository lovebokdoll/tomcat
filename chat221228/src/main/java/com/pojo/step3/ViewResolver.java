package com.pojo.step3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ViewResolver {
    
    public ViewResolver() {}
    
    public ViewResolver( HttpServletResponse res, HttpServletRequest req, String[] pageMove ) throws ServletException, IOException {
        String path = pageMove[1];
        
        // webapp를 바라본다
        if ( "redirect".equals( pageMove[0] ) ) {
            res.sendRedirect( path );
        }
        else if ( "forward".equals( pageMove[0] ) ) {
            RequestDispatcher view = req.getRequestDispatcher( "/" + path + ".jsp" );
            view.forward( req, res );
        }
        // setViewName(~~);에 들어가는 값
        // WEB-INF/view/~~~~.jsp를 바라본다
        else {
            
            path = pageMove[0] + "/" + pageMove[1];
            RequestDispatcher view = req.getRequestDispatcher( "/WEB-INF/views/" + path + ".jsp" );
            view.forward( req, res );
        }
    }// end of ViewResolver( res, req, pageMove );
    
}// end of viewResolver
