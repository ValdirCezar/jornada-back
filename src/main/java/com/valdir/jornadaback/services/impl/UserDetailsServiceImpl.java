package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.repositories.UserRepository;
import com.valdir.jornadaback.security.UserSS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("UserDetailsServiceImpl - CHEGOU NO USER DETAILS SERVICE");
        User obj = repository.findByEmail(email);

        if (obj == null) {
            logger.info("UserDetailsServiceImpl - USUÁRIO OU SENHA INVÁLIDOS");
            throw new UsernameNotFoundException(email);
        }

        logger.info("UserDetailsServiceImpl - PASSOU NO USER DETAILS SERVICE");
        return new UserSS(obj.getId(), obj.getCpf(), obj.getPassword(), Collections.singleton(obj.getProfile()));
    }

}