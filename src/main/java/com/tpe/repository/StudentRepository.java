package com.tpe.repository;


import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Optional, since we are extending from JpaRepository,
// spring will understand it is a repo class
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);//if there is email in DB it returns true, else false
}
