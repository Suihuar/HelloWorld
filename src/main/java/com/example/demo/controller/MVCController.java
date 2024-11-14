package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserLogin;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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
    public String showRegisterForm(Model model) {
        User user = new User();
        String[] professionList = new String[3];
        professionList[0] = "Developer";
        professionList[1] = "Designer";
        professionList[2] = "Tester";
        model.addAttribute("user", user);
        model.addAttribute("professionList", professionList);
        return "register_form";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin", userLogin);
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@Valid @ModelAttribute UserLogin userLogin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("SB");
            model.addAttribute("userLogin", userLogin);
            return "login";
        } else {
            System.out.println(userService.login(userLogin.getEmail(), userLogin.getPassword()));
            if (userService.login(userLogin.getEmail(), userLogin.getPassword())) {
                model.addAttribute("userLogin", userLogin);
                return "chatroom";
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
        }
    }

    @PostMapping("/register")
    public String submitRegisterForm(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
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
            return "login";
        }
    }
}