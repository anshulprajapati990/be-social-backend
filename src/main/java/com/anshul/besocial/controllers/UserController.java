package com.anshul.besocial.controllers;

import com.anshul.besocial.models.User;
import com.anshul.besocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User savedUser =userService.registerUser(user);
        return savedUser;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users=userService.findAllUser();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        return userService.findUserById(id);
    }

    @PutMapping("/users/{userId}")
    public User updateUserById(@RequestBody User user,
                                   @PathVariable("userId") Integer id) throws Exception {
        return userService.updateUser(user,id);
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws Exception {
        return userService.followUser(userId1,userId2);
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query)
    {
        List<User> users=userService.searchUser(query);
        return users;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable("userId") Integer id) throws Exception {
        return userService.deleteUser(id);
    }

}
