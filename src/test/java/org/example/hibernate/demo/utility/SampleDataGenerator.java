package org.example.hibernate.demo.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.example.hibernate.demo.entity.User;
import org.example.hibernate.demo.entity.UserDetail;
import org.example.hibernate.demo.entity.UserSkill;

public class SampleDataGenerator {

    public static void insertSampleData(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        User m1 = new User();
        m1.setName("Alice");
        entityManager.persist(m1);

        User m2 = new User();
        m2.setName("Bob");
        entityManager.persist(m2);

        UserDetail d2 = new UserDetail();
        d2.setCity("New York");
        d2.setActive(false);
        d2.setUserId(m2.getId());
        entityManager.persist(d2);

        User m3 = new User();
        m3.setName("Charlie");
        entityManager.persist(m3);

        UserDetail d3 = new UserDetail();
        d3.setCity("Paris");
        d3.setActive(true);
        d3.setUserId(m3.getId());
        entityManager.persist(d3);

        UserSkill s3 = new UserSkill();
        s3.setSkillName("Java");
        s3.setDeleted(true);
        s3.setUser(m3);
        entityManager.persist(s3);

        User m4 = new User();
        m4.setName("David");
        entityManager.persist(m4);

        UserDetail d41 = new UserDetail();
        d41.setCity("London");
        d41.setActive(false);
        d41.setUserId(m4.getId());
        entityManager.persist(d41);

        UserDetail d42 = new UserDetail();
        d42.setCity("Rome");
        d42.setActive(true);
        d42.setUserId(m4.getId());
        entityManager.persist(d42);

        UserSkill s4 = new UserSkill();
        s4.setSkillName("Kotlin");
        s4.setDeleted(false);
        s4.setUser(m4);
        entityManager.persist(s4);

        User m5 = new User();
        m5.setName("Eve");
        entityManager.persist(m5);

        UserDetail d51 = new UserDetail();
        d51.setCity("Moscow");
        d51.setActive(false);
        d51.setUserId(m5.getId());
        entityManager.persist(d51);

        UserDetail d52 = new UserDetail();
        d52.setCity("Istanbul");
        d52.setActive(false);
        d52.setUserId(m5.getId());
        entityManager.persist(d52);

        UserDetail d53 = new UserDetail();
        d53.setCity("Berlin");
        d53.setActive(true);
        d53.setUserId(m5.getId());
        entityManager.persist(d53);

        UserSkill s51 = new UserSkill();
        s51.setSkillName("Python");
        s51.setDeleted(true);
        s51.setUser(m5);
        entityManager.persist(s51);

        UserSkill s52 = new UserSkill();
        s52.setSkillName("Ruby");
        s52.setDeleted(false);
        s52.setUser(m5);
        entityManager.persist(s52);

        User m6 = new User();
        m6.setName("Frank");
        entityManager.persist(m6);

        UserDetail d6 = new UserDetail();
        d6.setCity("Madrid");
        d6.setActive(true);
        d6.setUserId(m6.getId());
        entityManager.persist(d6);

        UserSkill s61 = new UserSkill();
        s61.setSkillName("Rust");
        s61.setDeleted(true);
        s61.setUser(m6);
        entityManager.persist(s61);

        UserSkill s62 = new UserSkill();
        s62.setSkillName("Erlang");
        s62.setDeleted(false);
        s62.setUser(m6);
        entityManager.persist(s62);

        UserSkill s63 = new UserSkill();
        s63.setSkillName("Go");
        s63.setDeleted(false);
        s63.setUser(m6);
        entityManager.persist(s63);

        UserSkill s64 = new UserSkill();
        s64.setSkillName("C");
        s64.setDeleted(true);
        s64.setUser(m6);
        entityManager.persist(s64);

        User m7 = new User();
        m7.setName("Grace");
        entityManager.persist(m7);

        UserDetail d71 = new UserDetail();
        d71.setCity("Vienna");
        d71.setActive(false);
        d71.setUserId(m7.getId());
        entityManager.persist(d71);

        UserDetail d72 = new UserDetail();
        d72.setCity("Barcelona");
        d72.setActive(true);
        d72.setUserId(m7.getId());
        entityManager.persist(d72);

        UserSkill s71 = new UserSkill();
        s71.setSkillName("PHP");
        s71.setDeleted(false);
        s71.setUser(m7);
        entityManager.persist(s71);

        UserSkill s72 = new UserSkill();
        s72.setSkillName("Swift");
        s72.setDeleted(false);
        s72.setUser(m7);
        entityManager.persist(s72);

        UserSkill s73 = new UserSkill();
        s73.setSkillName("Dart");
        s73.setDeleted(false);
        s73.setUser(m7);
        entityManager.persist(s73);

        UserSkill s74 = new UserSkill();
        s74.setSkillName("Scala");
        s74.setDeleted(false);
        s74.setUser(m7);
        entityManager.persist(s74);

        transaction.commit();
        entityManager.close();
    }
}
