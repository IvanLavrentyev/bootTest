package com.example.demo.datasource;

import com.example.demo.handler.AuthenticationSuccessRedirect;
import com.example.demo.handler.CustomAccessDeniedHandler;
import com.example.demo.repository.RoleService;
import com.example.demo.repository.UserService;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(myPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().
                    loginPage("/login").
                    loginProcessingUrl("/login").
                    successHandler(myAuthSuccessHandler()).
                    usernameParameter("login").
                    passwordParameter("password").permitAll().
                and().
                    authorizeRequests().
                    antMatchers("/admin", "/user").access("hasRole('ADMIN')").
                and().
                    authorizeRequests().
                    antMatchers("/user").access("hasRole('USER')").
                and().
                    rememberMe().tokenValiditySeconds(600).key("boot").
                and().
                    exceptionHandling().accessDeniedHandler(getAccessDeniedHamdler());
    }

    @Bean
    public AuthenticationSuccessHandler myAuthSuccessHandler(){
        return new AuthenticationSuccessRedirect();
    }

    @Bean
    public PasswordEncoder myPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHamdler(){
        return new CustomAccessDeniedHandler();
    }

}
