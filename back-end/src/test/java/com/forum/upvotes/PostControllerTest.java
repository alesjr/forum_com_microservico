package com.forum.upvotes;

import com.forum.upvotes.Controller.PostController;
import com.forum.upvotes.Entity.PostEntity;
import com.forum.upvotes.Service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PostControllerTest extends UpvotesApplicationTests{

    private final String url = "/api/upvotes";

    private MockMvc mockMvc;

    @Autowired
    private PostController postController;

    @Autowired
    private PostService postService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testPOSTAddPostController() throws Exception {
        PostEntity post = this.getPostGenerate();
        this.mockMvc.perform(MockMvcRequestBuilders.post(this.url+"/post")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(post.toJson())
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testPUTIncrementUpvotePostController() throws Exception {
        PostEntity post = this.getPostGenerate();
        post = postService.save(post);
        post.setPostCurtida(post.getPostCurtida()+1);
        this.mockMvc.perform(MockMvcRequestBuilders.put(this.url+"/post/"+post.getPostId()+"/increment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        postService.delete(post.getPostId());
    }

    @Test
    public void testPUTEditUpvotePostController() throws Exception {
        PostEntity post = this.getPostGenerate();
        post = postService.save(post);
        this.mockMvc.perform(MockMvcRequestBuilders.put(this.url+"/post")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(post.toJson())
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        postService.delete(post.getPostId());
    }

    @Test
    public void testDELETEUpvotePostController() throws Exception {
        PostEntity post = this.getPostGenerate();
        post = postService.save(post);
        this.mockMvc.perform(MockMvcRequestBuilders.delete(this.url+"/post/"+post.getPostId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGETAllPostsPostController() throws Exception {
        PostEntity post = new PostEntity();
        post.setPostId(1);
        post.setPostDescricao("Testando o teste do post.");
        post.setPostTitulo("TestTDD1");
        postService.save(post);

        post.setPostId(2);
        post.setPostDescricao("Testando o teste do post2.");
        post.setPostTitulo("TestTDD2");
        postService.save(post);

        this.mockMvc.perform(MockMvcRequestBuilders.get(this.url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    private PostEntity getPostGenerate(){
        PostEntity post = new PostEntity();
        post.setPostId(1);
        post.setPostDescricao("Testando o teste.");
        post.setPostTitulo("TestTDDPUT");
        return post;
    }

}
