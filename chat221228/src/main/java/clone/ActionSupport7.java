package clone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@WebServlet( "*.st7" )
@Log4j2
public class ActionSupport7 extends HttpServlet {
    
    private void doService( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "doService호출" );
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
        String upmu[] = null;
        upmu = command.split( "/" );
        log.info( upmu[0] + ", " + upmu[1] );
        req.setAttribute( "upmu", upmu );
        Object obj = "";
        try {
            obj= HandlerMapping7.getController(upmu,req,res);
        }
        catch ( Exception e ) {
            log.info("Exception :" + e.toString());
        }
        if(obj!=null) {
            String pageMove[]=null;
            ModelAndView7 modelAndView7 =null;
            if(obj instanceof String) {
                if(((String)obj).contains( ":" )) {
                    log.info(":가 포함되어있닥우");
                    pageMove = obj.toString().split( ":" );
                }else {
                    log.info(":가 포함 안되어있다규");
                    pageMove = obj.toString().split( "/" );
                }
            }else if(obj instanceof ModelAndView7 ){
                modelAndView7 =(ModelAndView7)obj;
                pageMove = new String[2];
                
            }
        }
    }
    
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        doService( req, res );
    }
    
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        doService( req, res );
        
    }
    
}
