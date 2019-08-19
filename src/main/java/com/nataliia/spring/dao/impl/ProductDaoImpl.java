package com.nataliia.spring.dao.impl;

import com.nataliia.spring.dao.ProductDao;
import com.nataliia.spring.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAllProducts() {
        @SuppressWarnings("unchecked")
        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery("FROM Product");
        return query.getResultList();
    }

    @Override
    public Product findById(long id) {
        return sessionFactory.getCurrentSession().find(Product.class, id);
    }

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().persist(product);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().merge(product);
    }

    @Override
    public void deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Product p WHERE p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
