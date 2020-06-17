package com.ffirechess.ui.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")    // http://localhost:8080/friendly-fire-chess
public class HomeController {
    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
