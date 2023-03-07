package clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ModelAndView7 {
    HttpServletRequest req;
    // 캡슐화 코드는 반드시 getter와 setter가 필요하다 ->Lombok
    private String            viewName;
    List<Map<String, Object>> reqList = new ArrayList<>();
    
    public ModelAndView7() {}
    
    public ModelAndView7( HttpServletRequest req ) {
        this.req = req;
    }
    //데이터를 보낼 때 사용
    public void addObject( String name, Object obj ) {
        
        req.setAttribute( name, obj );
        
        Map<String, Object> pMap = new HashMap<>();
        pMap.put( name, obj );
        reqList.add( pMap );
    }
    
    public String getViewName() { return viewName; }
    // 뷰의 이름을 설정해준다
    public void setViewName( String viewName ) { this.viewName = viewName; }
}
