package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.UserDao;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Order;
import com.nataliia.spring.model.User;
import com.nataliia.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserDao userDao;

    @Autowired
    public OrderServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean addOrder(Order order, long userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            return false;
        }
        Cart cart = user.getCart();

        order.getGoodsInOrder().addAll(cart.getGoodsInCart());

        order.setUser(user);
        user.getOrders().add(order);

        cart.resetCart();
        userDao.update(user);

        return true;
    }
}
