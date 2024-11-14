package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {
    @Autowired
    private UserService userService;
    @PostMapping(path="/add") // Map ONLY POST Requests
    public String addNewUser(@Valid @ModelAttribute User user) {
        userService.save(user);
        return "Saved";
    }

    @RequestMapping("/")
    public String home() {
        System.out.println("Going home..."); // appear in the console
        return "index";
    }


    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        String[] professionList = new String[3];
        professionList[0] = "Developer";
        professionList[1] = "Designer";
        professionList[2] = "Tester";
        model.addAttribute("user", user);
        model.addAttribute("professionList", professionList);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute User user, BindingResult
            bindingResult, Model model) {
        System.out.println(user); // the user information is displayed in the console
        if (bindingResult.hasErrors()) {
            String[] professionList = new String[3];
            professionList[0] = "Developer";
            professionList[1] = "Designer";
            professionList[2] = "Tester";
            model.addAttribute("user", user);
            model.addAttribute("professionList", professionList);
            return "register_form";
        } else {
            model.addAttribute("user", user);
            userService.save(user);
            return "register_success";
        }
    }
}