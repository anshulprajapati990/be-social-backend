package com.anshul.besocial.service.Impl;

import com.anshul.besocial.models.User;
import com.anshul.besocial.repository.UserRepository;
import com.anshul.besocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new Exception("User not found "+ userId);
        }


        return user.get();
    }

    @Override
    public User findUserByEmail(String email) {
        User user =userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1=findUserById(userId1);
        User user2=findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer userId) throws Exception {
        Optional<User> user1=userRepository.findById(userId);
        if(user1.isEmpty()) {
            throw new Exception("user not exist with user id "+ userId);
        }

        User oldUser =user1.get();
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setGender(user.getGender());

        User updatedUser=userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> users=userRepository.searchUser(query);
        return users;
    }

    @Override
    public List<User> findAllUser() {
        List<User> users =userRepository.findAll();
        return users;
    }

    @Override
    public String deleteUser(Integer id) throws Exception {
        userRepository.delete(findUserById(id));
        return "User with id "+id+ " is successfully deleted ";
    }
}
