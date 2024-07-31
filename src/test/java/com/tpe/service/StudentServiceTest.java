package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //to enable Mockito's extension for JUnit5 tests
class StudentServiceTest {

    @Mock //to create a mock (fake) instance of the studentsRepository
    private StudentRepository studentRepository;

    @InjectMocks //Injects mocked dependency into this instance
    private StudentService studentService = new StudentService();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("to check getAll method is returning list of students")
    void getAllStudents() {

        //Given

        //create some sample students
        Student student1 = new Student();
        student1.setName("Burak");
        student1.setLastName("Burak");
        student1.setEmail("burak@gmail.com");
        student1.setPhoneNumber("999999");

        Student student2 = new Student();
        student2.setName("Sinan");
        student2.setLastName("Sinan");
        student2.setEmail("Sinan@gmail.com");
        student2.setPhoneNumber("888888");

        //creating expected results
        List<Student> expectedStudent = Arrays.asList(student1, student2);
        //since we have mocked StudentRepository, findAll() will return list of students
        when(studentRepository.findAll()).thenReturn(expectedStudent);

        //When --Actual

        List<Student>actualStudents = studentService.getAllStudents();

        //Then --Assert

        assertEquals(expectedStudent,actualStudents);
        //we have to verify how many times mock is running
        verify(studentRepository,times(1)).findAll();

    }

    @Disabled
    @Test
    void saveStudent() {
        //TODO: 02:07:00 from 2nd video of the unit tests
    }

    @Disabled
    @Test
    void findStudentById() {
    }

    @Disabled
    @Test
    void deleteStudentById() {
    }

    @Disabled
    @Test
    void updateStudent() {
    }

    @Disabled
    @Test
    void getAllStudentWithPage() {
    }
}