package com.pojo.step3;

import java.util.List;
import java.util.Map;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Logic {
    Board3Dao board3Dao = new Board3Dao();
    
    public List<Map<String, Object>> boardList( Map<String, Object> pMap ) {
        log.info( "boardList호출" );
        List<Map<String, Object>> boardList = board3Dao.boardList( pMap );
        return boardList;
    }
    
    public List<Map<String, Object>> boardDetail( Map<String, Object> pMap ) {
        log.info( "boardDetail호출" );
        List<Map<String, Object>> boardList = board3Dao.boardList( pMap );
        int bm_no = Integer.parseInt( pMap.get( "bm_no").toString() );
        board3Dao.hitCount(bm_no);
        return boardList;
    }
    
    public int boardInsert( Map<String, Object> pMap ) {
        log.info( "boardInsert호출:" + pMap );
        int result   = 0;
        int bm_no    = 0;
        int bm_group = 0;
        bm_no = board3Dao.getBNo();
        pMap.put( "bm_no", bm_no );
        // Map안에서 꺼낸다는 건 화면에서 넘어온 값이라는 뜻
        
        if ( pMap.get( "bm_group" ) != null ) {
            bm_group = Integer.parseInt( pMap.get( "bm_group" ).toString() );
        }
        
        // 댓글쓰기야?
        if ( bm_group > 0 ) {
            board3Dao.bStepUpdate( pMap );
            pMap.put( "bm_pos", Integer.parseInt( pMap.get( "bm_pos" ).toString() ) + 1 );
            pMap.put( "bm_step", Integer.parseInt( pMap.get( "bm_step" ).toString() ) + 1 );
        }
        // 새글쓰기야? -그룹번호 채번 포함
        else {
            bm_group = board3Dao.getBGroup();
            log.info( "새글쓰기 로직 호출=>" + bm_group );
            pMap.put( "bm_group", bm_group );
            pMap.put( "bm_pos", 0 );
            pMap.put( "bm_step", 0 );
        }
        result = board3Dao.boardInsert( pMap );
        //첨부파일이 존재하니?
        if(pMap.get("bs_file")!=null &&pMap.get( "bs_file" ).toString().length()>1) {
            pMap.put("bm_no",bm_no);
            //현재 첨부파일은 하나만 담는 것으로 가정하고 처리함
            pMap.put("bs_seq",1);
            int result2=0;
            result2=board3Dao.boardSInsert( pMap );
            log.info(result2);
        }
        return result;
    }
      public int boardMUpdate( Map<String, Object> pMap ) {
        int result = board3Dao.boardMUpdate( pMap );
        
        return result;
        
    }
    public int boardMDelete( Map<String, Object> pMap ) {
        int result = board3Dao.boardMDelete( pMap );
        return result;
        
    }
    
}