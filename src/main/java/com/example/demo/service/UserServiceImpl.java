package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkUser(String login) {
        List<User> users = getAllUsers();

        Set<String> userTokens = users.
                stream().map(User::getLogin).collect(Collectors.toSet());

        return userTokens.contains(login);
    }

    @Override
    public User getUserByName(String name) {
      return userRepository.getUserByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User saveUser(User user) {

        if (!user.getPassword().startsWith("$2a$10$"))
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        return  userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public void deleteUSer(User user) {
        userRepository.delete(user);
    }
}
