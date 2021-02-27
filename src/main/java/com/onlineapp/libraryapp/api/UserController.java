package com.onlineApp.libraryApp.api;

import com.onlineApp.libraryApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup/addUser")
    public String addBook(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String login, @RequestParam String password){
        userService.addUser(firstname, lastname, login, password);
        return "mainPage";
    }

    @PostMapping("/sign-in")
    public RedirectView signIn(@RequestParam String login, @RequestParam String password) {
        return new RedirectView(userService.signIn(login, password));
    }

    @RequestMapping("/mainPage")
    public String addBook(){
        return "mainPage";
    }



}














































































































































































































































































