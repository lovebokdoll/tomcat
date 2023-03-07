package clone;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class BoardDao7 {
    MyBatisCommonFactory mcf = new MyBatisCommonFactory();
    
    public List<Map<String, Object>> boardList( Map<String, Object> pMap ) {
        List<Map<String, Object>> boardList         = null;
        SqlSessionFactory         sqlSessionFactory = null;
        SqlSession                sqlSession        = null;
        try {
            sqlSessionFactory = mcf.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            boardList = sqlSession.selectList( "boardList", pMap );
            log.info( boardList );}
            
        catch ( Exception e ) {
            e.printStackTrace();
        }
        
        return boardList;
    }
    
}
