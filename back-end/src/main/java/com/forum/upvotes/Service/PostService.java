package com.forum.upvotes.Service;

import com.forum.upvotes.Entity.PostEntity;
import com.forum.upvotes.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public PostEntity save(PostEntity postEntity){
        return postRepository.save(postEntity);
    }

    public PostEntity edit(PostEntity postEntity){
        validaPostExists(postEntity.getPostId());
        return this.save(postEntity);
    }

    public PostEntity incrementPost(Integer postId){
        PostEntity post = validaPostExists(postId);
        post.setPostCurtida(post.getPostCurtida()+1);
        return this.save(post);
    }

    public void delete(Integer postId){
        PostEntity post = validaPostExists(postId);
        postRepository.delete(post);
    }

    private PostEntity validaPostExists(Integer postId){
        PostEntity post = postRepository.findById(postId).orElseThrow(
           () -> new ResourceNotFoundException("Post não encontrado!")
        );
        return post;
    }
}
