package com.nataliia.spring.dao;

import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Good;

import java.util.List;

public interface CartDao {

    Cart findById(long id);

    void update(Cart cart);

    List<Good> loadAllGoodsFromCart(Long id);

}
