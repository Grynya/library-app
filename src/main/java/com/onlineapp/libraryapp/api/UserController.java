package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String username, @RequestParam String password, @RequestParam String lang){
        userService.addUser(firstname, lastname, username, password, lang);
        return "login";
    }

    @PostMapping("/addUser/removeUser")
    public String removeUser(@RequestParam String login, @RequestParam String password, @RequestParam String lang){
        userService.removeUser(login, password, lang);
        return "modifyRemoveElements";
    }

    @PostMapping("/addUser/updateUser")
    public String updateUser(@Param("oldFirstName") String oldFirstName, @Param("oldLastName") String oldLastName, @Param("oldLogin") String oldLogin, @Param("oldPassword") String oldPassword,
                             @Param("newFirstName") String newFirstName, @Param("newLastName") String newLastName, @Param("newLogin") String newLogin, @Param("newPassword") String newPassword, @RequestParam String lang){
        userService.updateUser(oldFirstName, oldLastName, oldLogin, oldPassword, newFirstName, newLastName, newLogin, newPassword, lang);
        return "modifyRemoveElements";
    }

    @GetMapping("/addElements")
    public String addElements(){
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements")
    public String modifyRemoveElements(){
        return "modifyRemoveElements";
    }


}
