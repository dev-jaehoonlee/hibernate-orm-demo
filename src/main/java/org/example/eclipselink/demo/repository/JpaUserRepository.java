package org.example.eclipselink.demo.repository;

import jakarta.persistence.*;
import org.example.eclipselink.demo.entity.User;

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

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph);

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

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .setHint("jakarta.persistence.loadgraph", entityGraph);

        User user = query.getSingleResult();

        transaction.commit();
        entityManager.close();

        return user;
    }
}
