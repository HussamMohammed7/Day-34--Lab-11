package com.example.lab11.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "category_id cannot be null")
    @Column (columnDefinition = "int not null ")
    private Integer categoryId;

    @NotNull(message = "userId cannot be null")
    @Column (columnDefinition = "int not null ")
    private Integer userId;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 5, message = "Title must be more than 4 characters long")
    @Column(columnDefinition = "varchar(255) not null")
    private String title;

    @NotEmpty(message = "content cannot be empty")
    @Column(columnDefinition = "text not null")
    private String content;


    @Column(columnDefinition = "date")
    private LocalDate publish_date;




}
