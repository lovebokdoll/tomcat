package com.pojo.step3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.step2.Board2Controller;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("*.st3")
public class ActionSupport extends HttpServlet {
    protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("doService호출");
        String uri = req.getRequestURI(); //
        log.info(uri); //
        String context = req.getContextPath();//
        log.info(context);
        String command = uri.substring(context.length() + 1); //
        log.info(command);
        int end = command.lastIndexOf(".");//
        log.info(end);
        command = command.substring(0, end);
        log.info(command);
        String upmu[] = null;
        upmu = command.split("/");
        log.info(upmu[0] + ", " + upmu[1]);
        req.setAttribute("upmu", upmu);
        Object obj = "";
        try {
            obj = HandlerMapping.getController(upmu, req, res);
        } catch (Exception e) {
            log.info("Exception :" + e.toString());
        }
        if (obj != null) { // 응답시
            String pageMove[] = null;// redirect:XXX.jsp or forward:XXX.jsp
            ModelAndView mav = null;
            if (obj instanceof String) {
                if (((String) obj).contains(":")) {
                    log.info(":포함되어있어유");
                    pageMove = obj.toString().split(":");
                    // pageMove[0] = forward
                   //  pageMove[1] = board3/boardDetail
                } else {
                    log.info(": 포함되어있지 않아유");
                    pageMove = obj.toString().split("/");
                }
            } else if (obj instanceof ModelAndView) {
                mav = (ModelAndView) obj;
                pageMove = new String[2];
                pageMove[0] = "";//foward -> ViewResolver else if()타게됨 ->webapp
                pageMove[1] = mav.getViewName();
            }
            if (pageMove != null) {
                // pageMove[0] = redirect or forward
                // pageMove[1] = XXX.jsp
               new ViewResolver( res, req, pageMove );
                
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("doGet호출");
        doService(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("doPost호출");
        doService(req, res);
    }
}