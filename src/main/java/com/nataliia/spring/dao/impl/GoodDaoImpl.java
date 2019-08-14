package com.nataliia.spring.dao.impl;

import com.nataliia.spring.dao.GoodDao;
import com.nataliia.spring.model.Good;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GoodDaoImpl implements GoodDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public GoodDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Good> findAllGoods() {
        @SuppressWarnings("unchecked")
        TypedQuery<Good> query = sessionFactory.getCurrentSession().createQuery("FROM Good");
        return query.getResultList();
    }

    @Override
    public Good findById(long id) {
        return sessionFactory.getCurrentSession().find(Good.class, id);
    }

    @Override
    public void save(Good good) {
        sessionFactory.getCurrentSession().persist(good);
    }

    @Override
    public void update(Good good) {
        sessionFactory.getCurrentSession().merge(good);
    }

    @Override
    public void deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Good g WHERE g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
