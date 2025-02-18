package csd230.lecture27.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "csd230, Lecture 2.7 MVC and REST");
        return "index";
    }
}
