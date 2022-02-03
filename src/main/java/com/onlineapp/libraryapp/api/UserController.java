package com.onlineapp.libraryapp.api;

import com.onlineapp.libraryapp.model.request.AddUserRequest;
import com.onlineapp.libraryapp.model.request.UpdateUserRequest;
import com.onlineapp.libraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String addUser(AddUserRequest addUserRequest) {
        return userService.addUser(addUserRequest);
    }

    @DeleteMapping("/user")
    public String removeUser(@RequestParam String login, @RequestParam String password, @RequestParam String lang) {
        return userService.removeUser(login, password, lang);
    }

    @PutMapping("/user")
    public String updateUser(UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }
}