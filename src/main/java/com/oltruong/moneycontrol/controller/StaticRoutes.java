package com.oltruong.moneycontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Olivier Truong
 */
@Controller
public class StaticRoutes {
    @RequestMapping({
            "/fileupload",
            "/home",
            "/rules",
            "/alloperations",
            "/operations/{year:\\w+}/{month:\\w+}",
            "/operations/{year:\\w+}"
    })
    public String index() {

        System.out.println("hello index");
        return "forward:/index2.html";
    }
}
