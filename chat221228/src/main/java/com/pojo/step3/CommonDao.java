package com.pojo.step3;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@Service
public class CommonDao {
    MyBatisCommonFactory mcf = new MyBatisCommonFactory();
    
    public List<Map<String, Object>> zipcodeList( Map<String, Object> pMap ) {
        log.info( "zipcodeList호출" );
        List<Map<String, Object>> zList         = null;
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            pMap.put( "dong","역삼");//못해먹겠어요 누나 //어떻게 해야 할까 이걸 //ㅠㅠㅠㅠㅠㅠ
            zList = sqlSession.selectList( "zipcodeList", pMap );
            log.info( zList );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return zList;
    }


}
