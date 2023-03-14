package com.pojo.step3;

import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CommonLogic {
    
    private CommonDao commonDao = new CommonDao();
    
    public List<Map<String, Object>> zipcodeList( Map<String, Object> pMap ) {
        log.info( "zipcodeList호출" );
        List<Map<String, Object>> zList = commonDao.zipcodeList( pMap );
        return zList;
        
    }
}
