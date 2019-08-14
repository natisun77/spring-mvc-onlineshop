package com.nataliia.spring.dao.impl;

import com.nataliia.spring.dao.UserDao;
import com.nataliia.spring.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        String sql = "FROM User u WHERE u.email = :email AND u.password = :password";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(sql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User singleResult = query.getSingleResult();
        return Optional.ofNullable(singleResult);
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public void deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE User u WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
