package com.self.learnjava.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.self.learnjava.entity.User;
import com.self.learnjava.repository.UserRepository;

/*
 * SpirngBoot整合JPA
 */
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id")Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	@GetMapping("/user")
	public User insertUser(User user) {
		User save = userRepository.save(user);
		return save;
	}
	
}
