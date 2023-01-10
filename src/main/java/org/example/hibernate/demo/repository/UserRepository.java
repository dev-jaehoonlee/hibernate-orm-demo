package org.example.hibernate.demo.repository;

import org.example.hibernate.demo.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    User findUserByName(String name);
}
