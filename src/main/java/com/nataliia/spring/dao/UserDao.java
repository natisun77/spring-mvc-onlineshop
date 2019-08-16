package com.nataliia.spring.dao;

import com.nataliia.spring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAllUsers();

    User findById(long id);

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    void add(User user);

    void update(User user);

    void deleteById(Long id);
}
