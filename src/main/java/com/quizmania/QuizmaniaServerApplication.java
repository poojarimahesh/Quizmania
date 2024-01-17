package com.quizmania;

import com.quizmania.Repositories.RoleRepository;
import com.quizmania.Repositories.UserRepository;
import com.quizmania.entity.Role;
import com.quizmania.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class QuizmaniaServerApplication {


	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(QuizmaniaServerApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//
//		User user1 = new User();
//		user1.setEmail("email");
//		user1.setEnable(true);
//		user1.setUserName("maheshpoojari");
//		user1.setFirstName("Mahesh");
//		user1.setLastName("Poojari");
//		user1.setPassword("Gaur");
//		user1.setPhone(7738220485L);
//
//		User user2 = new User();
//		user2.setEmail("email");
//		user2.setEnable(true);
//		user2.setUserName("nareshpoojari");
//		user2.setFirstName("Naresh");
//		user2.setLastName("Poojari");
//		user2.setPassword("Gaur");
//		user2.setPhone(7738220539L);
//
//		Role role = new Role();
//		role.setRoleId(1024);
//		role.setRoleName("Normal");
//
//		user1.setRole(role);
//		user2.setRole(role);
//		Set<User> users = new HashSet<>();
//		users.add(user1);
//		users.add(user2);
//		role.setUsers(users);
//		Role roleSaved = roleRepository.save(role);

//		logger.info("Accessing User from role table {}",roleSaved.getUsers().toString());


//		System.out.println(this.roleRepository.findById(1024).get().getUsers().size());
//
//	}
}
