package com.pojo.step3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;

/*
 * spring4에서 제공되던 ModelAndView를 흉내내보자
 * 1)scope가 request이다
 * 2)화면 이름을 정해준다
 */
@Log4j2
public class ModelAndView {
    HttpServletRequest req ;
    // 캡슐화 코드는 반드시 getter와 setter가 필요하다 ->Lombok
    private String            viewName ;
    List<Map<String, Object>> reqList  = new ArrayList<>();
    
    public ModelAndView() {}
    
    public ModelAndView( HttpServletRequest req ) {
        this.req = req;
    }
    
    public void addObject( String name, Object obj ) {
        
        req.setAttribute( name, obj );
       
        Map<String, Object> pMap = new HashMap<>();
        pMap.put( name, obj );
        reqList.add( pMap );
    }
    
    public String getViewName() { return viewName; }
    
    public void setViewName( String viewName ) { this.viewName = viewName; }
}
