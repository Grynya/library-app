package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.request.AddUserRequest;
import com.onlineapp.libraryapp.model.request.UpdateUserRequest;
import com.onlineapp.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addUser(AddUserRequest addUserRequest) {
        userService.addUser(addUserRequest);
        return "login";
    }

    @PostMapping("/addUser/removeUser")
    public String removeUser(@RequestParam String login, @RequestParam String password, @RequestParam String lang) {
        userService.removeUser(login, password, lang);
        return "modifyRemoveElements";
    }

    @PostMapping("/addUser/updateUser")
    public String updateUser(UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
        return "modifyRemoveElements";
    }

    @GetMapping("/addElements")
    public String addElements() {
        return "addElements";
    }

    @GetMapping("/modifyRemoveElements")
    public String modifyRemoveElements() {
        return "modifyRemoveElements";
    }

}