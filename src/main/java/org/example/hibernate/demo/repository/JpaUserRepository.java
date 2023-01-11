package org.example.hibernate.demo.repository;

import org.example.hibernate.demo.entity.User;

import javax.persistence.*;
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

        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class)
                .setHint("javax.persistence.loadgraph", entityGraph);

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
                .setHint("javax.persistence.loadgraph", entityGraph);

        User user = query.getSingleResult();

        transaction.commit();
        entityManager.close();

        return user;
    }
}
