package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //to enable Mockito's extension for JUnit5 tests
class StudentServiceTest {

    @Mock //to create a mock (fake) instance of the studentsRepository
    private StudentRepository studentRepository;

    @InjectMocks //Injects mocked dependency into this instance
    private StudentService studentService = new StudentService();

    Student student1;

    @BeforeEach //before each of my test methods new object will be created
    // name, lastName, email, phoneNumber will be set with data inside of setUp
    void setUp() {

        student1 = new Student();
        student1.setId(1L);
        student1.setName("Burak");
        student1.setLastName("Burak");
        student1.setEmail("burak@gmail.com");
        student1.setPhoneNumber("999999");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("to check getAll method is returning list of students")
    void getAllStudents() {

        //Given

        //create some sample students

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

    @Nested //to group related tests together for better organization and readability
    public class classTestSaveMethod{
        @DisplayName("Test when there is unique email")
        @Test
        void saveStudentWhenThereIsNoRegisteredEmail() {
            //given
            //There student1.getEmail() is not found in DB
            when(studentRepository.existsByEmail(student1.getEmail())).thenReturn(false);
            //when
            studentService.saveStudent(student1);
            //assert
            //when there is no the same email, save() method should run once
            verify(studentRepository, times(1)).save(student1);
        }

        @DisplayName("Test when email is already in database")
        @Test
        void saveStudentWhenThereIsRegisteredEmail() {
            //given
            //as the same email already registered in DB
            when(studentRepository.existsByEmail(student1.getEmail())).thenReturn(true);
            //when
            Exception exception = assertThrows(ConflictException.class,()->{
                studentService.saveStudent(student1);
            });
            //assert
            //
            assertEquals("Email already exists in DB", exception.getMessage());
            verify(studentRepository, never()).save(student1);
        }
    }

    @Nested
    public class FindStudentById{
        @DisplayName("FindStudentById method should return student by id when there is ID in Database")
        @Test
        void findStudentById() {

            //given
            Long studentId = 1L;
            when(studentRepository.findById(studentId)).thenReturn(Optional.of(student1));
            //when
            Student result = studentService.findStudentById(studentId);
            //then--assert
            assertNotNull(result);//object should not be null
            assertEquals(studentId, result.getId());
            assertEquals("Burak",result.getName());
            assertEquals("Burak", result.getLastName());
            assertEquals("burak@gmail.com", result.getEmail());
            verify(studentRepository, times(1)).findById(studentId);
        }

        @DisplayName("FindStudentById method should return exception")
        @Test
        void findStudentByIdForException() {
            //given
            Long studentId = 1L;
            //we are getting empty object from DB/ when requested id is not found in DB
            when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
            //when
            Exception exception = assertThrows(ResourceNotFoundException.class,()->{
                studentService.findStudentById(studentId);
            });
            //assert
            assertEquals("Student with id " + studentId + " is not found", exception.getMessage());
            verify(studentRepository, times(1)).findById(studentId);

        }
    }

    @Test
    @DisplayName("Test deleteStudentById method assuming given ID is already in DB")
    void deleteStudentById() {
        //given
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student1));
        //when
        //we are checking if no exceptions thrown when we run studentService.deleteStudentById(studentId)
        assertDoesNotThrow(() -> studentService.deleteStudentById(studentId));
        //assert
        verify(studentRepository).delete(student1); //if delete() method is running
        verify(studentRepository, times(1)).delete(student1); //how many times delete method is running

    }

    @Test
    @DisplayName("UpdateStudent when new email is not exist in DB and we are updating data")
    void updateStudent() {

        //given
        //dummy object DTO which will be provided by user/client
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setFirstName("Sinan");
        studentDTO.setLastName("Bey");
        studentDTO.setGrade(90);
        studentDTO.setEmail("sinan@gmail.com");
        studentDTO.setPhoneNumber("8888888");
        //we are mocking studentRepository.findById(1L) will return us student1 obj
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));
        //we are assuming that new email does not exist in DB
        when(studentRepository.existsByEmail(studentDTO.getEmail())).thenReturn(false);
        //assuming that we save existing student(student1) it should return the same object
        when(studentRepository.save(student1)).thenReturn(student1);
        //when
        //we are testing updateStudent() method from Service class
        studentService.updateStudent(1L, studentDTO);
        //then--assert
        //we are verifying mock methods running properly
        verify(studentRepository).existsByEmail("sinan@gmail.com"); //verifying existsByEmail is running with new email
        verify(studentRepository).findById(1L);//verifying findById method is running with provided id or not
        verify(studentRepository).save(student1);//verifying save method is running

        assertEquals("sinan@gmail.com", student1.getEmail());
        assertEquals("Sinan", student1.getName());
        assertEquals("Bey", student1.getLastName());
        //we can compare rest of fields
        //we can use parameterized test here for assertEquals

    }

    @Test
    @DisplayName("test if students are coming by page")
    void getAllStudentWithPage() {
        //given
        //create pageable object
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        //we need to have list of students in database to get pageable
        List<Student> students = createMockStudents();
        //created dummy page by passing student list
        Page<Student> expectedPage = new PageImpl<>(students, pageable, students.size());
        //mocking the behavior of the studentRepository.findAll(pageable)
        when(studentRepository.findAll(pageable)).thenReturn(expectedPage);
        //when
        Page<Student> actualPage = studentService.getAllStudentWithPage(pageable);
        //then
        assertEquals(expectedPage, actualPage);
        verify(studentRepository, times(1)).findAll(pageable);


    }

    /**
     * Helper method to create a list of mock students
     */
    private List<Student>createMockStudents(){
        Student student2 = new Student();
        student2.setName("Sinan");
        student2.setLastName("Sinan");
        student2.setEmail("Sinan@gmail.com");
        student2.setPhoneNumber("888888");
        List<Student> students = new ArrayList<>();
        students.add(student2);
        students.add(student1);
        return students;
    }
}