package com.forum.upvotes.Controller;

import com.forum.upvotes.Entity.PostEntity;
import com.forum.upvotes.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/upvotes")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Pega todos os upvotes
     * @return List<PostEntity> retorna todos os upvotes
     */
    @GetMapping(value = "", produces = {"application/json", "application/xml"})
    public List<PostEntity> getAllPosts(){
        return postService.getPostRepository().findAll();
    }

    /**
     * pega um upvote específico e caso não tenha nenhum retorna um exceção de not found
     */
    @GetMapping(value = "/post/{postId}", produces = {"application/json", "application/xml"})
    public PostEntity getPost(@PathVariable("postId") Integer postId){
        return postService.getPostRepository().findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post não encontrado!")
        );
    }

    @PostMapping("/post")
    public PostEntity addPost(@Valid @RequestBody PostEntity upvote){
        return postService.save(upvote);
    }

    @PutMapping(value = "/post", produces = {"application/json", "application/xml"})
    public PostEntity updatePost(@Valid @RequestBody PostEntity upvote){
       return postService.edit(upvote);
    }

    @PutMapping(value = "/post/{postId}/increment", produces = {"application/json", "application/xml"})
    public PostEntity incrementCurtidaPost(@PathVariable("postId") Integer postId){
        return postService.incrementPost(postId);
    }

    @DeleteMapping(value = "/post/{postId}", produces = {"application/json", "application/xml"})
    public ResponseEntity<PostEntity> deletePost(@PathVariable("postId") Integer postId){
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

}
