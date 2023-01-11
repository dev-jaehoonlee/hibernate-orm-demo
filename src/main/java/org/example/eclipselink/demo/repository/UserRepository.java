package org.example.eclipselink.demo.repository;

import org.example.eclipselink.demo.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    User findUserByName(String name);
}
