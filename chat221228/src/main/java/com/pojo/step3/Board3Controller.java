package com.pojo.step3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.HashMapBinder;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Controller implements Controller3 {
    Board3Logic board3Logic = new Board3Logic();
    
    @Override
    public ModelAndView boardList( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardList호출" );
        List<Map<String, Object>> boardList = null;
        
        // 사용자가 조건검색을 원하는 경우 - 조건 값을 전달할 객체 생성함
        // Mybatis에서는 동적쿼리를 지원하므로 하나의 쿼리문을 가지고 2가지경우 사용가능함
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        boardList = board3Logic.boardList( pMap );
        ModelAndView mav = new ModelAndView( req );
        log.info( boardList );
        mav.setViewName( "board3/boardList" );
        mav.addObject( "boardList", boardList );
        return mav;
    }
    
    @Override
    public Object jsonBoardList( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "jsonBoardList호출" );
        List<Map<String, Object>> bList = null;
        Map<String, Object>       pMap  = new HashMap<>();
        bList = board3Logic.boardList( pMap );
        req.setAttribute( "bList", bList );
        return "forward:jsonBoardList.jsp";
    }
    
    @Override
    public Object boardDetail( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardDetail호출" );
        List<Map<String, Object>> bList = null;
        // 전체 조회에 대한 sql문 재사용가능함 - 1건조회
        Map<String, Object> pMap   = new HashMap<>();
        HashMapBinder       hmb    = new HashMapBinder( req );
        hmb.bind( pMap );
        bList = board3Logic.boardList( pMap );
        log.info("bList");
        req.setAttribute( "bList", bList );
        return "forward:board3/boardDetail";
    }
    /*
     * INSERT INTO
     * board_master_t(bm_no,bm_title,bm_writer,bm_content,bm_reg,bm_hit)
     * ,bm_group,bm_pos,bm_step)
     * VALUES(seq_board_no.nextval,#{bm_title},#{bm_writer},#{bm_content},to_char(sysdate,'YYYY-MM-DD')
     * ,0,#{bm_group},#{bm_pos},#{bm_step})
     * 
     * 화면에서 받아올 값 - bm_title,bm_writer,bm_content
     * 그렇지 않은 경우 - bm_group,bm_pos,bm_step,bm_reg
     */
    
    @Override
    public Object boardInsert( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "boardInsert호출" );
        int                 result = 0;
        Map<String, Object> pMap   = new HashMap<>();
        HashMapBinder       hmb    = new HashMapBinder( req );
        hmb.bind( pMap );
        result = board3Logic.boardInsert( pMap );
        String path = "";
        log.info( result );
        
        if ( result == 1 ) {
            path = "redirect:/board3/boardList.st3";
        }
        else {
            path = "boardInsertFail.jsp";
            res.sendRedirect( path );
        }
        return path;
    }
    
    @Override
    public Object boardUpdate( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        int result = 0;
        
        if ( result == 1 ) {
            res.sendRedirect( "boardInsertSuccess.jsp" );
            return null;
        }
        return "redirect:/board3/boardList.st3";
    }
    
    @Override
    public Object boardDelete( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        int result = 0;
        
        if ( result == 1 ) {
            res.sendRedirect( "boardInsertSuccess.jsp" );
            return null;
        }
        return "redirect:/board3/boardList.st3";
    }
}
