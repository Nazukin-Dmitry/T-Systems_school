package com.tsystems.nazukin.logiweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 14.02.2016.
 */
public class GetStartManagerPageAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/manager/startPage.jsp").forward(request, response);
    }
}
