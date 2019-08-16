package com.nataliia.spring.service.impl;

import com.nataliia.spring.model.User;
import com.nataliia.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = findUserByUsername(userName);

        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private Optional<User> findUserByUsername(String username) {
        return userService.getByEmail(username);
    }

}
