package com.onlineApp.libraryApp.services;

import com.onlineApp.libraryApp.model.User;
import com.onlineApp.libraryApp.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(String firstname, String lastname, String login, String password){
        userRepository.save(new User(firstname, lastname, login, password));
    }

    public String signIn(String login, String password){
        if (userRepository.getUserByLoginPass(login, password)!=null){
            return "/mainPage";
        }
        return "/";
    }


}
