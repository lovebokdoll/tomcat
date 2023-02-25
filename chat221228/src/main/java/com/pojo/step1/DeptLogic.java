package com.pojo.step1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

// 트랜잭션 처리
public class DeptLogic {
	private static final Logger logger = LogManager.getLogger();
    
    public List<Map<String, Object>> getDeptList() {
        logger.info( "getDeptList호출 " );
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
        deptList.add( rmap );
        return deptList;
    }
    
    public int deptInsert() {
    	logger.info( "deptInsert호출 " );
    	return 0;
    }
    
    public int deptUpdate() {
    	logger.info( "deptUpdate호출 " );
    	return 0;
    }
    
    public int deptDelete() {
    	logger.info( "deptUpdate호출 " );
    	return 0;
    }
    
    public String jsonDeptList() {
        logger.info( "jsonDeptList호출 " );
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
