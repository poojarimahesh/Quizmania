//package com.quizmania.service.Impl;
//
//import com.quizmania.Repositories.UserRepository;
//import com.quizmania.entity.User;
//import com.quizmania.entity.UserInfoUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserInfoUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = this.userRepository.findByUserName(username);
//        return new UserInfoUserDetails(user);
//
//    }
//}
