package com.example.oauthserver.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class JdbcUserDetailsCustomManager extends JdbcUserDetailsManager {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();

        userDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password FROM users WHERE username=?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM authorizations WHERE username=?");
    }
}
