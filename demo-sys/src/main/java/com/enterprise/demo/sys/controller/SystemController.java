package com.enterprise.demo.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jinzhengang
 * @date 2018-07-17
 **/
@Controller
public class SystemController {

    @GetMapping("/login")
    public String login() {
        return "system/login";
    }

}
