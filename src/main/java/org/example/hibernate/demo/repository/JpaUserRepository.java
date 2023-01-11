package org.example.hibernate.demo.repository;

import org.example.hibernate.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaUserRepository implements UserRepository {

    private final EntityManagerFactory entityManagerFactory;

    public JpaUserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> findAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<User> query = entityManager.createQuery("SELECT DISTINCT(u) FROM User u LEFT OUTER JOIN FETCH u.detail LEFT OUTER JOIN FETCH u.skills", User.class);

        List<User> users = query.getResultList();

        transaction.commit();
        entityManager.close();

        return users;
    }

    @Override
    public User findUserByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u LEFT OUTER JOIN FETCH u.detail LEFT OUTER JOIN FETCH u.skills WHERE u.name = :name", User.class)
                .setParameter("name", name);

        User user = query.getSingleResult();

        transaction.commit();
        entityManager.close();

        return user;
    }
}
