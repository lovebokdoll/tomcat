package com.pojo.step3;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Controller implements Controller3 {
    Board3Logic board3Logic = new Board3Logic();
    @Override
    public ModelAndView boardList( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardList호출" );
        List<Map<String, Object>> bList = null;
        bList = board3Logic.boardList();
        return null;
    }
    
    @Override
    public Object boardDetail( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardDetail호출" );
        return null;
    }
    
    @Override
    public Object boardInsert( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardInsert호출" );
        return null;
    }
    
    @Override
    public Object boardUpdate( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardUpdate호출" );
        return null;
    }
    
    @Override
    public Object boardDelete( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardDelete호출" );
        return null;
    }
    
}
