package com.forum.upvotes.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "upvotes", catalog = "")
public class PostEntity implements Serializable {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_titulo", length = 90, nullable = true)
    private String postTitulo;

    @Column(name = "post_descricao", length = 255)
    private String postDescricao;

    @Column(name = "post_curtida")
    private Integer postCurtida;

    @Column(name = "post_data")
    private Date postData;

    public PostEntity(){
        this.postData = new Date();
        this.postCurtida = 0;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitulo() {
        return postTitulo;
    }

    public void setPostTitulo(String postTitulo) {
        this.postTitulo = postTitulo;
    }

    public String getPostDescricao() {
        return postDescricao;
    }

    public void setPostDescricao(String postDescricao) {
        this.postDescricao = postDescricao;
    }

    public Integer getPostCurtida() {
        return postCurtida;
    }

    public void setPostCurtida(Integer postCurtida) {
        this.postCurtida = postCurtida;
    }

    public Date getPostData() {
        return postData;
    }

    public void setPostData(Date postData) {
        this.postData = postData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostEntity)) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(getPostId(), that.getPostId()) &&
                Objects.equals(getPostTitulo(), that.getPostTitulo()) &&
                Objects.equals(getPostDescricao(), that.getPostDescricao()) &&
                Objects.equals(getPostCurtida(), that.getPostCurtida()) &&
                Objects.equals(getPostData(), that.getPostData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getPostTitulo(), getPostDescricao(), getPostCurtida(), getPostData());
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }



}
