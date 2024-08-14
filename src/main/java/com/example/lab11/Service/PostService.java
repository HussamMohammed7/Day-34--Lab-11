package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Post;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getPosts() {

        return postRepository.findAll();
    }

    public void addPost(Post post) {
        if (userRepository.findUserById(post.getUserId()) == null){
            throw new ApiException("User not found");
        }
        if (categoryRepository.findCategoryById(post.getCategoryId()) == null){
            throw new ApiException("Category not found");
        }

        post.setPublish_date(LocalDate.now());
        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post) {
        Post updatedPost = postRepository.findPostById(id);



        if (updatedPost == null) {
            throw new ApiException("Post not found");
        }

        if (userRepository.findUserById(post.getUserId()) == null){
            throw new ApiException("User not found");
        }
        if (categoryRepository.findCategoryById(post.getCategoryId()) == null){
            throw new ApiException("Category not found");
        }


        updatedPost.setCategoryId(post.getCategoryId());
        updatedPost.setUserId(post.getUserId());
        updatedPost.setTitle(post.getTitle());
        updatedPost.setContent(post.getContent());
        updatedPost.setPublish_date(LocalDate.now());

        postRepository.save(updatedPost);
    }

    public void removePost(Integer id) {
        if (!postRepository.existsById(id)) {
            throw new ApiException("Post not found");
        }

        postRepository.deleteById(id);
    }
    public List<Post> getPostsByUserId(Integer userId) {

        List posts = postRepository.findPostsByUserId(userId);
        if (posts == null) {
            throw new ApiException("This user does not have any posts");
        }

        return posts ;
    }

    public List<Post> getPostsByCategoryId(Integer categoryId) {
        List posts = postRepository.findPostsByCategoryId(categoryId);
        if (posts == null) {
            throw new ApiException("This category does not have any posts");
        }
        return posts;
    }

    public List<Post> getPostsByPublishDateBefore(LocalDate date) {

        List posts = postRepository.findPostsByPublishDateBefore(date);

        if (posts == null) {
            throw new ApiException("This category does not have any posts");
        }
        return posts;
    }
}
