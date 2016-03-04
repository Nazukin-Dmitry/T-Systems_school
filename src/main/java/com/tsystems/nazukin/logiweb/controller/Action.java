package com.tsystems.nazukin.logiweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 11.02.2016.
 */
public interface Action {
    void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
