package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query ("select u from User u where u.name =?1")
    User getUserByName(String name);

    @Query ("select u from User u where u.login =?1")
    User getUserByLogin(String login);
}
