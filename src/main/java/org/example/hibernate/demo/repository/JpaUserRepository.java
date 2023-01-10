package org.example.hibernate.demo.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.hibernate.demo.entity.User;

import java.util.List;

public class JpaUserRepository implements UserRepository {

    private final EntityManagerFactory entityManagerFactory;

    public JpaUserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> findAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");

        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph);

        List<User> users = query.getResultList();

        entityManager.close();

        return users;
    }

    @Override
    public User findUserByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .setHint("jakarta.persistence.loadgraph", entityGraph);

        User user = query.getSingleResult();

        entityManager.close();

        return user;
    }
}
