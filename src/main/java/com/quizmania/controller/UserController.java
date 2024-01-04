package com.quizmania.controller;

import com.quizmania.entity.User;
import com.quizmania.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/")
    User createUser(@RequestBody User user) throws Exception {
        return this.userServiceImpl.createUser(user);

    }

    @GetMapping("/allUsers")
    List<User> getAllUsers(){
        return this.userServiceImpl.getAllUsers();
    }
    @GetMapping("/{username}")
    User getUser(@PathVariable String username){
        return this.userServiceImpl.getUser(username);
    }

    @DeleteMapping("/{username}")
    void deleteUser(@PathVariable String username){
        this.userServiceImpl.deleteUser(username);
    }

}
