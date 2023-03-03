package com.pojo.step3;

import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Logic {
    Board3Dao board3Dao = new Board3Dao();
    
    public List<Map<String, Object>> boardList() {
        log.info( "boardList호출" );
        List<Map<String, Object>> bList =  board3Dao.boardList();
        return bList;
    }
    
}