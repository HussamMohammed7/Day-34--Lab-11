package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "postId cannot be null")
    @Column(columnDefinition = "int not null ")
    private Integer postId;

    @NotNull(message = "userId cannot be null")
    @Column (columnDefinition = "int not null ")
    private Integer userId;

    @NotEmpty(message = "content cannot be empty")
    @Column(columnDefinition = "text not null")
    private String content;

    @Column(columnDefinition = "date")
    private LocalDate comment_date;
}
