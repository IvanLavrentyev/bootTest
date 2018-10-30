package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    User saveUser(User user);
    User getUserByName(String name);
    User getUserByLogin(String login);
    void deleteUSer(User user);
    boolean checkUser(String login);
}
