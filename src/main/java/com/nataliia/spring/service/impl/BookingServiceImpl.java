package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.UserDao;
import com.nataliia.spring.model.Booking;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.User;
import com.nataliia.spring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final UserDao userDao;

    @Autowired
    public BookingServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean addBooking(Booking booking, long userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            return false;
        }
        Cart cart = user.getCart();

        booking.getProductsInBooking().addAll(cart.getProductsInCart());

        booking.setUser(user);
        user.getBookings().add(booking);

        cart.resetCart();
        userDao.update(user);

        return true;
    }
}
