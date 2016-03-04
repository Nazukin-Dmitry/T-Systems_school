package com.tsystems.nazukin.logiweb.controller.login;

import com.tsystems.nazukin.logiweb.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 14.02.2016.
 */
public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect("/page/home");
    }
}
