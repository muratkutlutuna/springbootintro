package com.tpe.dto;

import com.tpe.domain.Student;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {


    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be whitespace.")
    @Size(min = 2,max = 25,message="First name '${validatedValue}' must be between {min} and {max}.")
    private String firstName;

    @NotNull(message = "Last name cannot be null.")
    @NotBlank(message = "Last name cannot be whitespace.")
    @Size(min = 2,max = 25,message="Last name '${validatedValue}' must be between {min} and {max}.")
    private String lastName;

    private Integer grade;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Please provide valid email") //it will check "@" and "."
    private String email;

    private String phoneNumber;

    private LocalDateTime createdDate = LocalDateTime.now();

//    public StudentDTO(Student student) {
//        this.id = student.getId();
//        this.firstName = student.getName();
//        this.lastName = student.getLastName();
//        this.grade = student.getGrade();
//        this.phoneNumber = student.getPhoneNumber();
//        this.createdDate = student.getCreatedDate();
//    }


}
