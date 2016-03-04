package com.tsystems.nazukin.logiweb.controller.login;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.customexceptions.UserAllreadyExistException;
import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.model.enums.EmployeeType;
import com.tsystems.nazukin.logiweb.service.EmployeeService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 11.02.2016.
 */
public class RegistrationAction implements Action {

    private static final Logger logger = Logger.getLogger(RegistrationAction.class);

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(firstName);
        employeeEntity.setSecondName(secondName);
        employeeEntity.setLogin(login);
        employeeEntity.setPassword(password);
        employeeEntity.setEmployeeType(EmployeeType.NEW);

        String resultPage;
        if (!password.equals(password2)) {
            resultPage = "/page/registration?type=" + GetRegistrationPageAction.PASSWORD_ERROR;
        } else {
            try {
                employeeService.save(employeeEntity);
                resultPage = "/page/login?type=normal";
            } catch (UserAllreadyExistException ex) {
                logger.warn("Try to save employee with existing login.");
                resultPage = "/page/registration?type=" + GetRegistrationPageAction.LOGIN_EXIST_ERROR;
            }
        }
        response.sendRedirect(resultPage);
    }
}
