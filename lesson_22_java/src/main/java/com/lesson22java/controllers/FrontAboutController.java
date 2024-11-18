package com.lesson22java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontAboutController {
    @GetMapping("/about")
    public String about() {
        return "about-page";
    }
}
