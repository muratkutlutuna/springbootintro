package com.tpe.controller;

import com.tpe.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @PostMapping
    @RequestMapping("/register")  //http://localhost:8080/register
    public ResponseEntity<String>register(@RequestBody User user){
        return null;
    }

    //TODO: 7. video 2:39:21

}
