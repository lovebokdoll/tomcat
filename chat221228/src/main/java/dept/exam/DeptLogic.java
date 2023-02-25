package dept.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;
// 트랜잭션 처리
//받은 데이터 어떻게처리할지
@Log4j2
public class DeptLogic {
    
    public String getDeptList() {
        log.info( "getDeptList호출 " );
        List<DeptVO> deptList = new DeptDAO().getDeptList();
        Gson   g    = new Gson();
        String temp = g.toJson( deptList );
        return temp;
    }
    
    public int deptInsert(DeptVO deptVO) {
    int result =new DeptDAO().deptInsert(deptVO);
    	log.info( "deptInsert호출 " );
    	return result;
    }
    
    public int deptUpdate() {
    	log.info( "deptUpdate호출 " );
    	return 0;
    }
    
    public int deptDelete() {
    	log.info( "deptUpdate호출 " );
    	return 0;
    }
    
    public String jsonDeptList() {
        log.info( "jsonDeptList호출 " );
        List<Map<String, Object>> deptList = new ArrayList<>();
        Map<String, Object>       rmap     = new HashMap<>();
        rmap.put( "deptno", 10 );
        rmap.put( "dname", "개발부" );
        rmap.put( "loc", "부산 " );
        deptList.add( rmap );
        rmap = new HashMap<>();
        rmap.put( "deptno", 20 );
        rmap.put( "dname", "운영부" );
        rmap.put( "loc", "서울 " );
        deptList.add( rmap );
        rmap = new HashMap<>();
        rmap.put( "deptno", 30 );
        rmap.put( "dname", "총무부" );
        rmap.put( "loc", "대전 " );
        Gson   g    = new Gson();
        String temp = g.toJson( deptList );
        return temp;// JSOM포맷으로 전달 -리액트에서 조회시에 사용함
    }
    
    
}
