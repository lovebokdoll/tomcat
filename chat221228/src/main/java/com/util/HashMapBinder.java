package com.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
//Spring부트에서는 RequestParam이 대신 해줌,Model,ModelMap
//사용자가 입력한 값을 Map에 담아 준다.
//담을 map은 컨트롤계층에서 bind메소드 호출시 파라미터를 이용해서 원본주소번지를 받아온다.
//그리고 그 안에 담는다.
// Model,ModelMap
public class HashMapBinder {
    HttpServletRequest req; //전역변수
    
    public HashMapBinder() {}
    //생성자 파라미터에 요청객체(지역변수)가 필요한 이유는??
    public HashMapBinder( HttpServletRequest req ) {
        //생성자의 1역할 - 전역변수 초기화
        this.req = req;
    }
    // 어떤 페이지 어떤 상황에서 공통코드 재사용 가능하게 할것인가?
    // 업무별 요청 클래스에서 주입받을 객체를 정해서 메소드의 파라미터로
    // 전달받음, 전달받은 주소번지 원본에 값을 담아준다
    //반환타입이 void인데 파라미터로 넘어간 pMap을 어떻게 쓸 수 있을까??
    public void bind( Map<String, Object> pMap ) {
        pMap.clear();
        Enumeration<String> en = req.getParameterNames();
        while ( en.hasMoreElements() ) {
            String key = en.nextElement();
            pMap.put( key, req.getParameter( key ) );
        }
    }
}
