package com.pojo.step2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board2Dao {
    // insert here - 이종간인 MyBatis연동에 필요한 공통클래스에대한 객체주입이 필요한 지점
    MyBatisCommonFactory mcf = new MyBatisCommonFactory();
    
    public List<Map<String, Object>> boardList() {
        log.info( "boardList호출" );
        List<Map<String, Object>> bList             = null;
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            Map<String, Object> pMap = new HashMap<>();
//            pMap.put( "mem_id", "tomato" );
//            pMap.put( "mem_pw", "123" );
            bList = sqlSession.selectList( "boardList", pMap );
            log.info( bList );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return bList;
    }
}

/*
 * ActionServlet(페이지 이동처리) -xxxController-XXXLogic - XXXDao -MyBatisLayer
 * 
 */
