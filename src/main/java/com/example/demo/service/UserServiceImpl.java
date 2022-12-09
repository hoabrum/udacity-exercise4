package com.example.demo.service;

import com.example.demo.model.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.example.demo.model.persistence.User userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) throw new UsernameNotFoundException(userName);
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.EMPTY_LIST);
    }


    public com.example.demo.model.persistence.User getUserDetailsByUserName(String userName) {
        com.example.demo.model.persistence.User userDetails = userRepository.findByUsername(userName);
        if (userDetails == null) throw new UsernameNotFoundException(userName);
        return userDetails;
    }

}
