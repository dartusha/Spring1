package com.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String hello(Model uiModel) {
        uiModel.addAttribute("name", "World1");
        return "home";
    }
}
