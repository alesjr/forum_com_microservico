import React, { Component } from 'react';
import like from '../like.svg';
import axios from 'axios';
import './Post.css';

export default class Post extends Component {
  
    constructor(props) {
        super(props);
        this.state = this.props;
    }


    handleLike = async () => {
        await axios.put('http://localhost:8080/api/upvotes/post/'+this.state.post.postId+'/increment')
        .then(res => {                    
           // this.props.post.postCurtida = res.data.postCurtida;
           this.setState({post: res.data});
        });
    };

    render() {
        const { post } = this.state;
        return (
            <li className="post">
                <p> {post.postDescricao}</p>
                <button type="button" onClick={this.handleLike.bind(this)}>
                    <img src={like} alt="Like" style={{width: '20px', height: '20px'}}/>
                    {post.postCurtida}
                </button>
            </li>
        );
    }
}