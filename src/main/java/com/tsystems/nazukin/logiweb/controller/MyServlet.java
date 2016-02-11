package com.tsystems.nazukin.logiweb.controller;

import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 1 on 06.02.2016.
 */
@WebServlet("/login")
public class MyServlet extends HttpServlet {
    EmployeeService service = new EmployeeService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<EmployeeEntity> em = service.loadAllPersons();
        System.out.println(em);
        req.getSession().setAttribute("all", em);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeEntity emp = new EmployeeEntity();
        String s1 = req.getParameter("firstName");
        String s2 = req.getParameter("secondName");
        String s3 = req.getParameter("Serial");
        emp.setFirstName(s1);
        emp.setSecondName(s2);
        emp.setSerialNumber(s3);
        service.save(emp);
        resp.sendRedirect("/login");
    }
}
