package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists in DB");
        }
        studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        //findById(id) method will return Optional class (if there is no student with
        // provided id, it will return empty obj), so we need to customize exception
        Student student = studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student with id "+id+" is not found")
        );
        return student;
    }

    public void deleteStudentById(Long id) {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }

    public void updateStudent(Long id, StudentDTO studentDTO) {

        //check if the user entered (new email) exists in DB
        boolean existsEmail = studentRepository.existsByEmail(studentDTO.getEmail());

        //find student using id
        Student existingStudent = findStudentById(id);

        if (existsEmail && !existingStudent.getEmail().equals(studentDTO.getEmail())) {
//new email exists in DB AND old email is NOT equal to new email
            throw new ConflictException("Email already exists");
        }
        /*
            Scenario #1: existing email is : aaa@gmail.com
                if new email is: aaa@gmail.com ---> UPDATES

            Scenario #2: existing email is : aaa@gmail.com
                if new email is: bbb@gmail.com -but there is
                bbb@gmail.com in DB (someone else is using he email) ---> THROWS EXCEPTION

            Scenario #3: existing email is : aaa@gmail.com
                if new email is: bbb@gmail.com -but there is
                no such email in DB ---> UPDATES
         */

        //we are mapping studentDTO obj to student entity

        existingStudent.setName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        existingStudent.setGrade(studentDTO.getGrade());

        //by saving existingStudent we are persisting info to DB
        studentRepository.save(existingStudent);
    }

    public Page<Student> getAllStudentWithPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> findStudentByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    public List<Student> findStudentByGrade(Integer grade) {
        return studentRepository.findStudentByGradeWithSQL(grade);
    }

    public StudentDTO findStudentDTOById(Long id) {
        return studentRepository.findStudentDTOById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student with id "+id+" is not found"));
    }
}
