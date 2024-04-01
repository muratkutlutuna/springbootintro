package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController //used to crete restful API
@RequestMapping("/students") //http://localhost:8080/students
public class StudentController {
    @Autowired
    private StudentService studentService;

    //method to bring all students
    @GetMapping //http://localhost:8080/students + GET
    public ResponseEntity<List<Student>> getAll(){ //HTTP status code + Students list
        List <Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students); //OK is for 200 status code
    }

    //method to create/add
    //@RequestBody --> we will send JSON data and that data should be mapped to Student object
    //@Valid --> validates the fields
    @PostMapping() //http://localhost:8080/students + POST
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully");
        map.put("status", "true");
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.CREATED);
    }

}
