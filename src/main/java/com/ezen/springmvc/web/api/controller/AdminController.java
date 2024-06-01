package com.ezen.springmvc.web.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"", "/dashboard", "/member", "/team"})
    public String forwardToReactRouter() {
        return "forward:/admin/index.html";
    }
}