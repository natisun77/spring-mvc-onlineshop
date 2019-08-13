package com.nataliia.spring.service;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    boolean addOrder(Order order, long userId);
}

