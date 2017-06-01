package com.oltruong.moneycontrol.staticroute;

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
        return "forward:/index2.html";
    }
}