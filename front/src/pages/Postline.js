import React, { Component } from 'react';
import './Postline.css';
import Post from '../components/Post'
import axios from 'axios';
import socket from 'socket.io-client';

export default class Postline extends Component {
    
    state = {
        posts: []
    }
    
    baseURL = 'http://localhost:8080/api/upvotes';

    
    async componentDidMount() {
        this.subscribeToEvents();
        const allPosts = await axios.get(this.baseURL).then(res => {
            return res.data;
        });
        this.setState( { posts: allPosts} );
    };
   
    subscribeToEvents = () => {
        //colocar aqui um socket
    };

    handleSubmit = e => {
        axios.post(this.baseURL+"/post", {
            postDescricao: document.getElementsByName("postDescricao")[0].value
        })
        this.componentDidMount();
    }

    render() {
        return (
            <div className="postline-wrapper"> 
                <form onSubmit={this.handleSubmit}>
                    <textarea name="postDescricao"
                        placeholder="No que você está pensando?"
                    /> 
                    <button type="submit">
                        Enviar
                    </button>                   
                </form>
                <ul className="post-list">
                    {this.state.posts.map(post => (
                        <Post key={post.postId} post={post} />
                    ))}
                </ul>
            </div>
        );
    }
}