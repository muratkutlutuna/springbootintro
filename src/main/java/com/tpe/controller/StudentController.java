package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
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
    @PostMapping() //http://localhost:8080/students + POST + JSON
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully");
        map.put("status", "true");
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.CREATED);
    }


    //method to get Student by ID

    //Both ways will do the same thing (get values from the path)
    //if there are multiple parameters, then RequestParam is suggested because we can
    //write variable names, so it will be understandable

    /**
     *  using pathVariable
     */
    @GetMapping("/{id}") //http://localhost:8080/students/1
    public ResponseEntity<Student> getStudentByUsingPath(@PathVariable("id") Long id){
        Student student = studentService.findStudentById(id);
        return new ResponseEntity<>(student,HttpStatus.OK); //return ResponseEntity.ok(students)
    }

    /**
     * using RequestParam
     */
    @GetMapping("/query") //http://localhost:8080/students/query?id=1
    public ResponseEntity<Student> getStudentByUsingRequestParam(@RequestParam("id") Long id) {
        //if there is only one parameter, we can write RequestParam() Long id
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    //deleteStudent from DB
    @DeleteMapping("/{id}") //http://localhost:8080/students/1 + DELETE
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable() Long id) {
        studentService.deleteStudentById(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is deleted successfully");
        map.put("status", "true");
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }

    //update student

    @PutMapping("/{id}") //http://localhost:8080/students/id + PUT + JSON
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id")Long id, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(id, studentDTO);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is updated successfully");
        map.put("status", "true");
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);

    }

    //if in our DB there are 1000 s of students and if we try to fetch them at the same time
    //it will take too much time may create issues

    //TODO: 21:52

}
