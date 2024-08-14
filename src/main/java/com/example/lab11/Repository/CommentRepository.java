package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentById(Integer id);



    List <Comment> findCommentsByPostId(Integer postId);

    List <Comment> findCommentsByContentContaining(String content);

}
