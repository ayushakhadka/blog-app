package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * Constructor injection
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Handles a request to create a post
      */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /**
     * Handles a request to get all posts
     */
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    /**
     * Handles a request to get post by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /**
     * Handles a request to update a post by id
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name="id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    /**
     * Handles a request to delete a post by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted sucessfully!", HttpStatus.OK);

    }


}