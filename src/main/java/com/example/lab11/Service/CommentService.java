package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        if (userRepository.findUserById(comment.getUserId()) == null){
            throw new ApiException("User not found");
        }
        if(postRepository.findPostById(comment.getPostId()) == null){
            throw new ApiException("Post not found");
        }
        comment.setComment_date(LocalDate.now());
        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment) {
        Comment updatedComment = commentRepository.findCommentById(id);
        if (updatedComment == null) {
            throw new ApiException("Comment not found");
        }
        if (userRepository.findUserById(comment.getUserId()) == null){
            throw new ApiException("User not found");
        }
        if(postRepository.findPostById(comment.getPostId()) == null){
            throw new ApiException("Post not found");
        }
        updatedComment.setPostId(comment.getPostId());
        updatedComment.setUserId(comment.getUserId());
        updatedComment.setContent(comment.getContent());
        updatedComment.setComment_date(LocalDate.now());

        commentRepository.save(updatedComment);
    }

    public void removeComment(Integer id) {
        if (!commentRepository.existsById(id)) {
            throw new ApiException("Comment not found");
        }

        commentRepository.deleteById(id);
    }

    public List<Comment> findCommentsByContentContaining(String content) {

        List c = commentRepository.findCommentsByContentContaining(content);
        if (c == null) {
            throw new ApiException("No comment contains this");
        }
        return c;

    }

    public List<Comment> findCommentsByPostId(Integer postId) {
        List c =  commentRepository.findCommentsByPostId(postId);
        if (c == null) {
            throw new ApiException("No comment have this postId");
        }
        return c;

    }
}
