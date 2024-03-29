package com.anshul.besocial.service.Impl;

import com.anshul.besocial.models.Post;
import com.anshul.besocial.models.User;
import com.anshul.besocial.repository.PostRepository;
import com.anshul.besocial.repository.UserRepository;
import com.anshul.besocial.service.PostService;
import com.anshul.besocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        Post newPost=new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(userService.findUserById(userId));
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);

        if(post.getUser().getId()!=user.getId())
        {
            throw new Exception("You can not delete other user post");
        }
        postRepository.delete(post);
        return "Post deleted successfully with id "+ postId;
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        if(user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        }
        else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likedPost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        if(post.getLiked().contains(user))
        {
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post=postRepository.findById(postId);

        if(post.isEmpty()) {
            throw new Exception("Post not found with id "+ postId);
        }
        return post.get();
    }
}
