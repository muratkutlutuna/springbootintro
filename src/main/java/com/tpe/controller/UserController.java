package com.tpe.controller;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.dto.UserRequest;
import com.tpe.service.UserService;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/register")  //http://localhost:8080/register
    public ResponseEntity<String>register(@Valid @RequestBody UserRequest userRequest){

        userService.save(userRequest);
        String message = "User has created successfully";
        return new ResponseEntity<>(message,HttpStatus.CREATED);


    }

    //TODO: 1.security video 1:15:17

}
