package com.pojo.step3;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Dao {
    MyBatisCommonFactory mcf = new MyBatisCommonFactory();
    
    public List<Map<String, Object>> boardList( Map<String, Object> pMap ) {
        log.info( "boardList호출" );
        List<Map<String, Object>> boardList         = null;
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            boardList = sqlSession.selectList( "boardList", pMap );
            log.info( boardList );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return boardList;
    }
    
    public int boardInsert( Map<String, Object> pMap ) {
        log.info( "boardInsert호출" );
        int               result            = 0;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession        sqlSession        = null;
        
        // insert이지만 update로 하는 이유는 리턴타입이 object이기 때문이다
        // 메소드 이름은 상관없이 해당 쿼리문은 id로 찾기 때문이다.
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            result = sqlSession.update( "boardMInsert", pMap );
            
            if ( result == 1 ) {
                
                sqlSession.commit();
            }
            log.info( result );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int getBGroup() {
        int result = 0;
        log.info( "getBGroup호출" );
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            result = sqlSession.selectOne( "getBGroup", "" );
            log.info( result );// 채번한 글그룹번호
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int getBNo() {
        int result = 0;
        log.info( "getBNo호출" );
        List<Map<String, Object>> boardList         = null;
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            result = sqlSession.selectOne( "getBNo", "" );
            log.info( result );// 채번한 글번호
        } 
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void bStepUpdate( Map<String, Object> pMap ) {
        int result = 0;
        log.info( "getBNo호출" );
        List<Map<String, Object>> boardList         = null;
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            result = sqlSession.update( "bStepUpdate", pMap );
            
            if ( result == 1 ) {
                sqlSession.commit();
            }
            log.info( result );// 채번한 글번호
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
