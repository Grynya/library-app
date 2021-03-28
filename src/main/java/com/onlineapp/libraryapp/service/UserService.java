package com.onlineapp.libraryapp.service;

import com.onlineapp.libraryapp.config.ResultConfig;
import com.onlineapp.libraryapp.model.Role;
import com.onlineapp.libraryapp.model.User;
import com.onlineapp.libraryapp.model.repo.UserRepository;
import com.onlineapp.libraryapp.model.request.AddUserRequest;
import com.onlineapp.libraryapp.model.request.UpdateUserRequest;
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

    public String addUser(AddUserRequest addUserRequest) {
        try {
            User user = new User(addUserRequest.getFirstname(), addUserRequest.getLastname(), addUserRequest.getUserName(), bCryptPasswordEncoder.encode(addUserRequest.getPassword()), true);
            user.setRole(Role.USER);
            userRepository.save(user);
            return resultConfig.result().getAddElementResultSuccessAdded().get(addUserRequest.getLang());
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            return resultConfig.result().getAddElementResultNotAdded().get(addUserRequest.getLang());
        }
    }

    public String removeUser(String login, String password, String lang) {
        if (userRepository.findById(userRepository.getUserIdByLoginPass(login, password)).isPresent()) {
            userRepository.delete(userRepository.findById(
                    userRepository.getUserIdByLoginPass(login, password)).get());
            return resultConfig.result().getRemoveElementResultSuccessRemoved().get(lang);
        }
        return resultConfig.result().getRemoveElementResultNotRemoved().get(lang);
    }

    public String updateUser(UpdateUserRequest updateUserRequest) {
        if (userRepository.findById(userRepository.getUserIdByLoginPass(updateUserRequest.getOldLogin(), updateUserRequest.getOldPassword())).isPresent()) {
            userRepository.updateUser(updateUserRequest.getOldFirstName(), updateUserRequest.getOldLastName(),
                    updateUserRequest.getOldLogin(), updateUserRequest.getOldPassword(),
                    updateUserRequest.getNewFirstName(), updateUserRequest.getNewLastName(),
                    updateUserRequest.getNewLogin(), updateUserRequest.getNewPassword());
            return resultConfig.result().getModifyElementResultSuccessModified().get(updateUserRequest.getLang());
        }
        return resultConfig.result().getModifyElementResultNotModified().get(updateUserRequest.getLang());
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}