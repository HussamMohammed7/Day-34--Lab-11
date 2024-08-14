package com.example.lab11.Controller;

import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.getComments()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        commentService.addComment(comment);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Comment added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Comment updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.removeComment(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Comment deleted successfully"
        );
    }

    @GetMapping("/get-contains")
    public ResponseEntity commentContains (@PathVariable String content){
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.findCommentsByContentContaining(content)
        );
    }
}
