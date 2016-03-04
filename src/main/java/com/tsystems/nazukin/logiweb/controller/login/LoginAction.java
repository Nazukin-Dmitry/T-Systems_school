package com.tsystems.nazukin.logiweb.controller.login;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 11.02.2016.
 */
public class LoginAction implements Action {

    public static final String USER = "user";
    public static final String USER_TYPE = "userType";

    private final EmployeeService service = new EmployeeService();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        EmployeeEntity user = service.getByLoginAndPassword(login, password);

        if (user != null) {
            request.getSession().setAttribute(USER, user); // Login user.
            request.getSession().setAttribute(USER_TYPE, user.getEmployeeType());

            switch (user.getEmployeeType()) {
                case MANAGER:
                    response.sendRedirect("/page/manager/startPage");
                    break;
                case DRIVER:
                    response.sendRedirect("/page/driver/order");
                    break;
                case NEW:
                    response.sendRedirect("/page/login?type=new");
                    break;
                default:
                    break;
            }
        } else {
            response.sendRedirect("/page/login?type=notRegistr");
        }
    }
}
