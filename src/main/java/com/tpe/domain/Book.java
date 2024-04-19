package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_book")
@Setter
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("bookName")//this name is used for json data
//    @Column(name = "book_name")//this name is for column name in table
    private String name;
    @ManyToOne //Many is book side
    @JoinColumn(name = "student_id")
    @JsonIgnore() // to ignore infinite calling
    /*
    @JsonIgnore is a Java annotation that tells the jackson library
    to ignore a property during JSON data conversion.
     */
    private Student student;

//    //getter
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
}
