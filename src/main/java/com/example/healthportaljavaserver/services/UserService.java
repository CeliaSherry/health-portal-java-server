package com.example.healthportaljavaserver.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthportaljavaserver.model.User;
import com.example.healthportaljavaserver.repositories.UserRepository;

@RestController
//@CrossOrigin(origins="https://polar-dawn-38329.herokuapp.com", allowCredentials="true",allowedHeaders="*")
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/login")
	public User login(@RequestBody User loginUser, HttpSession session) {
		User user;
		try {
			user = userRepository.findUserByCredentials(loginUser.getUsername(),loginUser.getPassword()).get(0);
		} catch (Exception e) {
			return null;
		}
				session.setAttribute("currentUser",  user);
				return user;
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		boolean exists = false;
		try {
		User existingUser = userRepository.findUserByUsername(user.getUsername()).get(0);
		} catch (Exception e) {
			session.setAttribute("currentUser", user);
			return userRepository.save(user);
		}
			session.setAttribute("currentUser", user);
			return user;
	}
	
	@PostMapping("/api/loggedin")
	public User loggedin(HttpSession session) {
		return (User)session.getAttribute("currentUser");
	}
	
	@PostMapping("/api/logout")
	public void logout (HttpSession session) {
		session.removeAttribute("currentUser");
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		return (User)session.getAttribute("currentUser");
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUser() {
	  return (List<User>) userRepository.findAll();
	}

	
	@GetMapping("/api/user/{userId}")
	public User findUserById
	(@PathVariable("userId") Integer id) {
	  return userRepository.findById(id).get();
	}

	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
	  		return userRepository.save(user);
	}

	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") Integer id) {
	  userRepository.deleteById(id);
	}

	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") Integer id, @RequestBody User newUser) {
	User user = userRepository.findById(id).get();
	user.set(newUser);
	return userRepository.save(user);
	}

}
