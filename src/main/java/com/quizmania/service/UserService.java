package com.quizmania.service;

import com.quizmania.Repositories.UserRepository;
import com.quizmania.entity.Role;
import com.quizmania.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface UserService {

    User createUser(User user) throws Exception;
    UserDetails getUser(String userName);

    List<User> getAllUsers();

    void deleteUser(String userName);

    User updateUser(String userName, Map<Object,Object> updatedFields);
}
