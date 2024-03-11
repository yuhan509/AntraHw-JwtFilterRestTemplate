package com.example.jwtfilterdemo.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserDetailsManager udm;

    public UserDetailsServiceImpl() {
        udm = new InMemoryUserDetailsManager();
        PasswordEncoder pe = new BCryptPasswordEncoder();
        UserDetails ud1 = User.withUsername("jack")
                .password(pe.encode("1234"))
                .authorities("read", "write")
                .build();
        udm.createUser(ud1);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return udm.loadUserByUsername(username);
    }
}
