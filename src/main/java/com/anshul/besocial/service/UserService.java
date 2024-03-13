package com.anshul.besocial.service;

import com.anshul.besocial.models.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    User findUserById(Integer userId) throws Exception;
    User findUserByEmail(String email);
    User followUser(Integer userId1 , Integer userId2) throws Exception;
    User updateUser(User user,Integer userId) throws Exception;
    List<User> searchUser(String query);
    List<User> findAllUser();

    String deleteUser(Integer id) throws Exception;


}
