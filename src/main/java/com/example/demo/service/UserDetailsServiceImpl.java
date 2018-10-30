package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(s);

        if (user == null) throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true, getRoles(user));
    }

    private Set<GrantedAuthority> getRoles(User user){
        return  user.getUserRoles().
                stream().map(role -> "ROLE_".concat(role.getRole_description())).
                map(SimpleGrantedAuthority :: new).collect(Collectors.toSet());
    }

}
