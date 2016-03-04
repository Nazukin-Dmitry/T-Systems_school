package com.tsystems.nazukin.logiweb.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 1 on 11.02.2016.
 */
public class FrontController extends HttpServlet {

    private final static Logger logger = Logger.getLogger(FrontController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(req);
            action.execute(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServletException("Executing action failed.", e);
        }
    }
}
