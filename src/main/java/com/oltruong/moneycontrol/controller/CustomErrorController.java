package com.oltruong.moneycontrol.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Olivier Truong
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public void error(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.html");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}