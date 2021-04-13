package com.onlineapp.libraryapp.security;

import com.onlineapp.libraryapp.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class SecurityUser implements UserDetails {

    private final String firstname;
    private final String lastname;
    private final String username;
    private final String password;
    private final boolean isActive;

    public SecurityUser(String username, String password, String firstname, String lastname, boolean isActive) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.getRole().getAuthorities()
        );
    }
}