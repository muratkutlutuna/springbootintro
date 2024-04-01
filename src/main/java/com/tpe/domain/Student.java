package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.nio.file.AccessMode;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_student")
@Getter //Add getter methods
@Setter //Add setter methods
@AllArgsConstructor //creates constructor with all fields
//@RequiredArgsConstructor
@NoArgsConstructor //creates empty constructor
@ToString //creates to string method
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) //this field cannot be set
    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be whitespace.")
    @Size(min = 2,max = 25,message="First name '${validatedValue}' must be between {min} and {max}.")
    @Column(nullable = false, length = 25)
    //@Setter //it will set only this field
    private /*final*/ String name;

    @Column(nullable = false, length = 25)
    private /*final*/ String lastName;

    private Integer grade;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Please provide valid email") //it will check "@" and "."
    private /*final*/ String email;

    private /*final*/ String phoneNumber;

    @Setter(AccessLevel.NONE) //this field cannot be set
    private LocalDateTime createdDate = LocalDateTime.now();



}
