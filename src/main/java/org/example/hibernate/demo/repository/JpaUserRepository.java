package org.example.hibernate.demo.repository;

import org.example.hibernate.demo.entity.User;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");

        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class)
                .setHint("javax.persistence.loadgraph", entityGraph);

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
                .setHint("javax.persistence.loadgraph", entityGraph);

        User user = query.getSingleResult();

        entityManager.close();

        return user;
    }
}
