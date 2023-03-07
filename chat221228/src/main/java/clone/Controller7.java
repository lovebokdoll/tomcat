package clone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller7 {

    Object boardList( HttpServletRequest req, HttpServletResponse res );

    Object boardDetail( HttpServletRequest req, HttpServletResponse res );

    Object boardInsert( HttpServletRequest req, HttpServletResponse res );

    Object boardUpdate( HttpServletRequest req, HttpServletResponse res );

    Object boardDelete( HttpServletRequest req, HttpServletResponse res );

    Object jsonBoardList( HttpServletRequest req, HttpServletResponse res );
    
}
