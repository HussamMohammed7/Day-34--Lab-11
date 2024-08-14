package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be more than 4 characters long")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String username;



    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(255) not null ")
    private String password;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be a valid format")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @Column(columnDefinition = "date")
    private LocalDate  registration_date;



}
