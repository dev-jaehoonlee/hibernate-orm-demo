package org.example.hibernate.demo.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.hibernate.demo.entity.User;
import org.example.hibernate.demo.entity.UserSkill;
import org.example.hibernate.demo.utility.SampleDataGenerator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;

import static jakarta.persistence.Persistence.createEntityManagerFactory;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

class JpaUserRepositoryTest {

    private static final Logger log = getLogger(JpaUserRepositoryTest.class);

    private static final EntityManagerFactory entityManagerFactory = createEntityManagerFactory("hibernate-orm-demo");

    private final UserRepository userRepository;

    public JpaUserRepositoryTest() {
        this.userRepository = new JpaUserRepository(entityManagerFactory);
    }

    @BeforeAll
    static void setUp() {
        SampleDataGenerator.insertSampleData(entityManagerFactory);
    }

    @AfterAll
    static void tearDown() {
        SampleDataGenerator.removeSampleData(entityManagerFactory);
    }

    @Test
    void findAllTest() {
        List<User> users = userRepository.findAllUsers();
        assertFalse(users.isEmpty());

        log.debug("Found {} users.", users.size());
        assertEquals(7, users.size());
    }

    @Test
    void checkAlice() {
        User user = userRepository.findUserByName("Alice");
        assertNotNull(user);

        log.debug("Alice = {}", user);
        assertEquals("Alice", user.getName());

        assertNull(user.getDetail());

        assertTrue(user.getSkills().isEmpty());
    }

    @Test
    void checkBob() {
        User user = userRepository.findUserByName("Bob");
        assertNotNull(user);

        log.debug("Bob = {}", user);
        assertEquals("Bob", user.getName());

        assertNull(user.getDetail());

        assertTrue(user.getSkills().isEmpty());
    }

    @Test
    void checkCharlie() {
        User user = userRepository.findUserByName("Charlie");
        assertNotNull(user);

        log.debug("Charlie = {}", user);
        assertEquals("Charlie", user.getName());

        assertNotNull(user.getDetail());
        assertTrue(user.getDetail().getActive());
        assertEquals("Paris", user.getDetail().getCity());

        assertTrue(user.getSkills().isEmpty());
    }

    @Test
    void checkDavid() {
        User user = userRepository.findUserByName("David");
        assertNotNull(user);

        log.debug("David = {}", user);
        assertEquals("David", user.getName());

        assertNotNull(user.getDetail());
        assertTrue(user.getDetail().getActive());
        assertEquals("Rome", user.getDetail().getCity());

        assertFalse(user.getSkills().isEmpty());
        assertTrue(user.getSkills().stream().noneMatch(UserSkill::getDeleted));
        assertEquals(1, user.getSkills().size());
    }

    @Test
    void checkEve() {
        User user = userRepository.findUserByName("Eve");
        assertNotNull(user);

        log.debug("Eve = {}", user);
        assertEquals("Eve", user.getName());

        assertNotNull(user.getDetail());
        assertTrue(user.getDetail().getActive());
        assertEquals("Berlin", user.getDetail().getCity());

        assertFalse(user.getSkills().isEmpty());
        assertTrue(user.getSkills().stream().noneMatch(UserSkill::getDeleted));
        assertEquals(1, user.getSkills().size());
    }

    @Test
    void checkFrank() {
        User user = userRepository.findUserByName("Frank");
        assertNotNull(user);

        log.debug("Frank = {}", user);
        assertEquals("Frank", user.getName());

        assertNotNull(user.getDetail());
        assertTrue(user.getDetail().getActive());
        assertEquals("Madrid", user.getDetail().getCity());

        assertFalse(user.getSkills().isEmpty());
        assertTrue(user.getSkills().stream().noneMatch(UserSkill::getDeleted));
        assertEquals(2, user.getSkills().size());
    }

    @Test
    void checkGrace() {
        User user = userRepository.findUserByName("Grace");
        assertNotNull(user);

        log.debug("Grace = {}", user);
        assertEquals("Grace", user.getName());

        assertNotNull(user.getDetail());
        assertTrue(user.getDetail().getActive());
        assertEquals("Barcelona", user.getDetail().getCity());

        assertFalse(user.getSkills().isEmpty());
        assertTrue(user.getSkills().stream().noneMatch(UserSkill::getDeleted));
        assertEquals(4, user.getSkills().size());
    }
}