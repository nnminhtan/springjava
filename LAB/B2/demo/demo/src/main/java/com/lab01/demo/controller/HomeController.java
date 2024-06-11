package com.lab01.demo.controller;

import com.lab01.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/")

public class HomeController{
    private static final ArrayList<User> users= new ArrayList<>();

    @GetMapping
    public String home(Model model){
        model.addAttribute("user",new User());
        return  "home/index";
    }

    @GetMapping("/contact")
    public  String contact(){
        return "home/auth";
    }
}