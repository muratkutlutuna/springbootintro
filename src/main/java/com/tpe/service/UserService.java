package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.UserRequest;
import com.tpe.exception.ConflictException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    public void save(UserRequest userRequest) {
        if(userRepository.existsByUserName(userRequest.getUserName())){
            throw new ConflictException("Username already exists in DB");

        }

        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setUserName(userRequest.getUserName());

        //encode password and set
//        newUser.setPassword(userRequest.getPassword());
        String password = userRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);

        //set default Role (set ADMIN role)

        //check or get role types from database
        Role role = roleService.findByName(UserRole.ROLE_ADMIN); //get ROLE from database, if there is no role
        // we will get exception.
        Set<Role> roles = new HashSet<>();
//        roles.add("ROLE_ADMIN");
        roles.add(role);
        newUser.setRoles(roles);

        userRepository.save(newUser);
    }
}
