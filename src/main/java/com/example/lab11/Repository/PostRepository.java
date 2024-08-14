package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Integer id);


    @Query("select p from Post p where p.userId = ?1")
    List<Post> findPostsByUserId(Integer userId);

    @Query("select p from Post p where p.categoryId = ?1")
    List<Post> findPostsByCategoryId(Integer categoryId);

    @Query("select p from Post p where p.publish_date < ?1")
    List<Post> findPostsByPublishDateBefore(LocalDate date);
}
