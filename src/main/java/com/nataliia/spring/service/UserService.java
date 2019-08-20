package com.nataliia.spring.service;


import com.nataliia.spring.model.User;
import com.nataliia.spring.model.UserPayload;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> getAll();

    void add(User user);

    void update(UserPayload user);

    void update(User user);

    Optional<User> getById(Long id);

    Optional<User> getUserByEmailAndPassword(String email, String password);

    void deleteById(Long id);
}
