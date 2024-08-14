package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.getPosts()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        postService.addPost(post);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Post added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Post updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.removePost(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Post deleted successfully"
        );
    }
    @GetMapping("/get-user/{userId}")
    public ResponseEntity getPostsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.getPostsByUserId(userId)
        );
    }

    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity getPostsByCategoryId(@PathVariable Integer categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.getPostsByCategoryId(categoryId)
        );
    }
    @GetMapping("/getByDateBefore/{date}")
    public ResponseEntity getPostsByPublishDateBefore(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        return ResponseEntity.status(HttpStatus.OK).body(
                postService.getPostsByPublishDateBefore(localDate)
        );
    }

}
