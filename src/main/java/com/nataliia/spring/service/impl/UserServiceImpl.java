package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.UserDao;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.User;
import com.nataliia.spring.model.UserPayload;
import com.nataliia.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(userDao.findAllUsers());
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setRole("user");
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDao.add(user);
    }

    @Override
    @Transactional
    public void update(UserPayload userPayload) {
        User user = userDao.findById(userPayload.getId());

        user.setFirstName(userPayload.getFirstName());
        user.setLastName(userPayload.getLastName());
        user.setEmail(userPayload.getEmail());
        user.setPassword(userPayload.getPassword());

        userDao.update(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        User newUser = userDao.findById(user.getId());

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        userDao.update(newUser);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userDao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(userDao.findByEmail(email));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}