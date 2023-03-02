package com.pojo.step2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board2Controller implements Controller {
    Board2Logic boardLogic = new Board2Logic();
    
    @Override
    public String execute( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        log.info( "execute호출" );
        String[] upmu = ( String[] ) req.getAttribute( "upmu" );
        String   page = null;
        
        if ( "boardList".equals( upmu[1] ) ) {
            log.info( "게시글목록보기" );
            List<Map<String, Object>> boardList = null;
            boardList = boardLogic.boardList();
            req.setAttribute( "boardList", boardList );
            page = "forward:board2/boardList"; // -> /board2/boardList.jsp
        }
        return page;
    }
    
}
