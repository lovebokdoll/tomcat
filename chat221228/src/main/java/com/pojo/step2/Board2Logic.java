package com.pojo.step2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board2Logic {
    Board2Dao boardDao = new Board2Dao();
    
    public List<Map<String, Object>> boardList() {
        log.info( "boardList호출" );
        List<Map<String, Object>> boardList = new ArrayList<>();
        boardList = boardDao.boardList();
        return boardList;
    }
}