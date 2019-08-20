package com.nataliia.spring.service;

import com.nataliia.spring.model.Order;

public interface OrderService {

    boolean addOrder(Order order, long userId);
}

