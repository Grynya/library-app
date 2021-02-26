package com.onlineApp.libraryApp.model.repo;

import com.onlineApp.libraryApp.model.PublishYear;
import com.onlineApp.libraryApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    default User getUserByLoginPass(String login, String password){
        for (User user: this.findAll()){
            if(user.getLogin().equals(login) & user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
