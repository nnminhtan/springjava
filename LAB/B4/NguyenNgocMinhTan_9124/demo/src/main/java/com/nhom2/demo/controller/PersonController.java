package com.nhom2.demo.controller;

import com.nhom2.demo.model.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private static List<Person> persons = new ArrayList<Person>();
    static {
        persons.add(new Person("Tan", "Nguyen"));
        persons.add(new Person("Khoa", "Ngo"));
        persons.add(new Person("An", "Truong"));
        persons.add(new Person("Bang", "Le"));
        persons.add(new Person("Luong", "Chau"));
        persons.add(new Person("Dat", "Luu"));

    }
    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("persons", persons);
        return "/person/result-person";
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("persons", new Person());
        return "/person/form-person";
    }

    @PostMapping("/personList")
    public String submitForm(@Valid Person person, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "/person/form-person";
        }
        model.addAttribute("message", "Sinh viên đã được thêm thành công!");
        return "/person/result-person";
    }

//    @GetMapping("/{param}")
//    public String welcome(@PathVariable String param, Model model) {
//        model.addAttribute("message", "hello " + param);
//        return "index";



//    @RequestMapping("/hello/{param}")
//    @ResponseBody
}