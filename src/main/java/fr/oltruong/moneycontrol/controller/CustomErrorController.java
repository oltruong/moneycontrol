package fr.oltruong.moneycontrol.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Olivier Truong
 */
@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping(value = "/error")
    public void error(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Error Mapping " + req);
        resp.sendRedirect("/index.html");

    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}