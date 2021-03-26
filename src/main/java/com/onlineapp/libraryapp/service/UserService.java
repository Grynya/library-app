package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Role;
import com.onlineapp.libraryapp.model.User;
import com.onlineapp.libraryapp.model.repo.UserRepository;
import com.onlineapp.libraryapp.security.SecurityUser;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ResultConfig resultConfig;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, ResultConfig resultConfig) {
        this.userRepository = userRepository;
        this.resultConfig = resultConfig;
    }

    public String addUser(String firstname, String lastname, String userName, String password, String lang){
    try {
        User user = new User(firstname, lastname, userName, bCryptPasswordEncoder.encode(password), true);
        user.setRole(Role.USER);
        userRepository.save(user);
        return resultConfig.result().getAddElementResultSuccessAdded().get(lang);
    }
    catch (DataIntegrityViolationException | ConstraintViolationException exception){
        return resultConfig.result().getAddElementResultNotAdded().get(lang);
    }
    }

    public String  removeUser(String login, String password, String lang) {
        if (userRepository.findById(userRepository.getUserIdByLoginPass(login, password)).isPresent()){
            userRepository.delete(userRepository.findById(
                    userRepository.getUserIdByLoginPass(login, password)).get());
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);
    }

    public String updateUser(String oldFirstName, String oldLastName, String oldLogin, String oldPassword,
                             String newFirstName, String newLastName, String newLogin, String newPassword, String lang) {
        if (userRepository.findById(userRepository.getUserIdByLoginPass(oldLogin, oldPassword)).isPresent()){
            userRepository.updateUser(oldFirstName, oldLastName, oldLogin, oldPassword, newFirstName, newLastName, newLogin, newPassword);
            return resultConfig.result().getModifyElementResultSuccessModified().get(lang);
        }
        return resultConfig.result().getModifyElementResultNotModified().get(lang);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
