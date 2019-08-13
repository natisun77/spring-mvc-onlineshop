package com.nataliia.spring.dao.impl;

import com.nataliia.spring.dao.CartDao;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Good;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Cart findById(long id) {
        return sessionFactory.getCurrentSession().find(Cart.class, id);
    }

    @Override
    public void update(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
    }

    @Override
    public List<Good> loadAllGoodsFromCart(Long cartId) {
        Query<Good> query = sessionFactory.getCurrentSession().createQuery("select g from Cart c join c.goodsInCart g where c.id = :cartId", Good.class);
        query.setParameter("cartId", cartId);
        return query.getResultList();

//        Query<Cart> query = sessionFactory.getCurrentSession()
//                .createQuery("from Cart c join fetch c.goodsInCart where c.id = :cartId", Cart.class);
//        query.setParameter("cartId", cartId);
//        Cart cart = query.getSingleResult();
//        return cart.getGoodsInCart();
    }

}
