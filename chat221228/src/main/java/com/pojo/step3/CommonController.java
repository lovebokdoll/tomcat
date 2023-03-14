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

public class CommonController implements Controller3 {
    
    private CommonLogic commonLogic = new CommonLogic();
    
    @Override
    public ModelAndView zipcodeList( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "zipcodeList호출" );
        List<Map<String, Object>> zList = null;
        
        // 사용자가 조건검색을 원하는 경우 - 조건 값을 전달할 객체 생성함
        // Mybatis에서는 동적쿼리를 지원하므로 하나의 쿼리문을 가지고 2가지경우 사용가능함
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        zList = commonLogic.zipcodeList( pMap );
        log.info( zList );
        ModelAndView mav = new ModelAndView( req );
        mav.setViewName( "common/zipcodeList" );
        mav.addObject( "zList", zList );
        return mav;
    }
    
    @Override
    public Object jsonBoardList( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object boardList( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object imageUpload( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object imageDownload( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object imageGet( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object boardDetail( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object boardInsert( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object boardUpdate( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Object boardDelete( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
