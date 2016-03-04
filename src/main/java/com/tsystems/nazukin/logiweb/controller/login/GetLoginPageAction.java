package com.tsystems.nazukin.logiweb.controller.login;

import com.tsystems.nazukin.logiweb.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 13.02.2016.
 */
public class GetLoginPageAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String typeOfPage = request.getParameter("type");
        String error;
        if (typeOfPage != null) {
            switch (typeOfPage) {
                case "normal":
                    error = "";
                    break;
                case "new":
                    error = "Administrator didn't register you. Please try later";
                    break;
                case "notRegistr":
                    error = "Wrong login/passsword.";
                    break;
                default:
                    error = "";
            }
        } else {
            error = "";
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
