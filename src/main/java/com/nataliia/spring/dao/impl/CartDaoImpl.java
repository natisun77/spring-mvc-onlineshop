package com.nataliia.spring.dao.impl;

import com.nataliia.spring.dao.CartDao;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cart findById(long id) {
        return sessionFactory.getCurrentSession().find(Cart.class, id);
    }

    @Override
    public void update(Cart cart) {
        sessionFactory.getCurrentSession().merge(cart);
    }

    @Override
    public List<Product> loadAllProductsFromCart(Long cartId) {
        Query<Product> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Cart c JOIN c.productsInCart p " +
                        "WHERE c.id = :cartId", Product.class);
        query.setParameter("cartId", cartId);
        return query.getResultList();
    }
}
