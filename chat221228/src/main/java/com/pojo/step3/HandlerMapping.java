package com.pojo.step3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HandlerMapping {
    /*************************************************
     * @param upmu[](upmu[0]-업무명[0],upmu[1]-메소드명,기능명,페이지이름,분기])
     * @param request                                           1-1,1-2와는 다르게 인터페이스를 implements를 하지 않는다.
     * @param response
     *                                                          질문:어디서 받아오지?
     * @return Object 
     * 
     * 
     * 
     * 
     *************************************************/
    public static Object getController( String[] upmu, HttpServletRequest req, HttpServletResponse res )
                    throws ServletException, IOException {
        log.info("upmu[0]:"+","+upmu[0]+","+"upmu[1]:"+upmu[1]);
        Controller3 controller3 = null;
        String path=null;
        ModelAndView modelAndView = null;
        return null;
    }
}
