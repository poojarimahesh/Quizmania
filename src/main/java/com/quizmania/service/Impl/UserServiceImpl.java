package com.quizmania.service.Impl;

import com.quizmania.Repositories.RoleRepository;
import com.quizmania.Repositories.UserRepository;
import com.quizmania.entity.Role;
import com.quizmania.entity.User;
import com.quizmania.service.UserService;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserInfoUserDetailsService userInfoUserDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User createUser(User user) throws Exception {
        User local=this.userRepository.findByUserName(user.getUsername());

        if(local!=null){
            System.out.println("User Already present");

            throw new Exception("User Already present");
        }else{
            Role role=roleRepository.findById(1024).get();
//            Role role= new Role();
//            role.setRoleName("ROLE_NORMAL");
//            role.setRoleId(1024);
//            this.roleRepository.save(role);
            user.setRole(roleRepository.findById(1024).get());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User save = this.userRepository.save(user);
//            Role role =this.roleRepository.findById(1024).get();
//            role.getUsers().add(save);
//            this.roleRepository.save(role);

        }
        return user;
    }

    @Override
    public UserDetails getUser(String userName) {
//        User user= userRepository.findByUserName(userName);
//        if(user!=null){
//            System.out.println(user.getRole().getUsers());
//            return user;
//        }else{
//            System.out.println("Invalid UserName");
//        }
//
//        return user;

        return this.userInfoUserDetailsService.loadUserByUsername(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteUser(String userName) {
        User user= userRepository.findByUserName(userName);
        if(user!=null){
            System.out.println("User deleted Successfully");
            this.userRepository.deleteById(user.getUserId());
        }else{
            System.out.println("Invalid UserName");
        }

    }

    @Override
    public User updateUser(String userName, Map<Object, Object> updatedFields) {
        User user= userRepository.findByUserName(userName);
        if(user!=null){
           updatedFields.forEach((key,value) -> {
               Field field = ReflectionUtils.findField(User.class,(String) key);
               field.setAccessible(true);
               ReflectionUtils.setField(field,user,value);
           });
        }else{
            System.out.println("Invalid UserName");
        }
        this.userRepository.save(user);

        return user;
    }


}
