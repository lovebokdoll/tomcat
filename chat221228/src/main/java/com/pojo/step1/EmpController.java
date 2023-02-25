package com.pojo.step1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmpController implements Action {
	private static final Logger logger = LogManager.getLogger();
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ActionForward af = new ActionForward();
        return af;
    }
}