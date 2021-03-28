package com.onlineapp.libraryapp.model.repo;

import com.onlineapp.libraryapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "" +
            "UPDATE users SET first_name=:oldFirstName\n" +
            "           WHERE first_name=:newFirstName;" +
            "UPDATE users SET last_name=:oldLastName \n" +
            "           WHERE last_name=:newLastName;" +
            "UPDATE users SET login=:oldLogin\n" +
            "            WHERE login=:newLogin;" +
            "UPDATE users SET password=:oldPassword+\n" +
            "            WHERE password=:newPassword;", nativeQuery = true)
    void updateUser(@Param("oldFirstName") String oldFirstName, @Param("oldLastName") String oldLastName, @Param("oldLogin") String oldLogin, @Param("oldPassword") String oldPassword,
                    @Param("newFirstName") String newFirstName, @Param("newLastName") String newLastName, @Param("newLogin") String newLogin, @Param("newPassword") String newPassword);

    default int getUserIdByLoginPass(String login, String password) {
        for (User user : this.findAll()) {
            if (user.getUserName().equals(login) && user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return -1;
    }

    Optional<User> findByUserName(String userName);

}
