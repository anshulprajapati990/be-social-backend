package com.anshul.besocial.service;

import com.anshul.besocial.models.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post,Integer userId) throws Exception;
    String deletePost(Integer postId,Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId);
    List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId) throws Exception;
    Post likedPost(Integer postId,Integer userId) throws Exception;

    Post findPostById(Integer postId) throws Exception;
}
