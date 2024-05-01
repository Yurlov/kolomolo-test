package com.github.kolomolo.service.openaiclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String chatPage() {
        return "home";
    }
}
