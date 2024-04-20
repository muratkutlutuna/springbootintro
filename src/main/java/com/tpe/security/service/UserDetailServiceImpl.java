package com.tpe.security.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    /*
        In this class we are going to convert
            1. User entity to UserDetail
            2. Role entity to Granted Authority
     */

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //using username find user from DB
        User foundUser = userRepository.findByUserName(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found with name: " + username)
        );

        if (foundUser != null) {
            return new org.
                    springframework.
                    security.
                    core.
                    userdetails.
                    User(
                    foundUser.getUserName(),
                    foundUser.getPassword(),
                    buildGrantedAuthorities(foundUser.getRoles())
            );
        } else {
            throw new UsernameNotFoundException("User not found with name: " + username);
        }
    }

    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles){
        //Since we are getting data from SET roles/elements will not be duplicated

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }

}
