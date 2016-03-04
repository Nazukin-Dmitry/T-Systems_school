package com.tsystems.nazukin.logiweb.controller.login;

import com.tsystems.nazukin.logiweb.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 13.02.2016.
 */
public class GetRegistrationPageAction implements Action {
    public final static String PASSWORD_ERROR = "pass";
    public final static String LOGIN_EXIST_ERROR = "loginExist";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String typeOfPage = request.getParameter("type");
        String error;
        if (typeOfPage != null) {
            switch (typeOfPage) {
                case "normal":
                    error = "";
                    break;
                case PASSWORD_ERROR:
                    error = "Passwords are not equals!!!";
                    break;
                case LOGIN_EXIST_ERROR:
                    error = "User with that login has already registered!!!";
                    break;
                default:
                    error = "";
            }
        } else {
            error = "";
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
    }
}
