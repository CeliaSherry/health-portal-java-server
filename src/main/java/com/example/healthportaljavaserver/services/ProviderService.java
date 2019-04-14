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

import com.example.healthportaljavaserver.model.Article;
import com.example.healthportaljavaserver.model.Customer;
import com.example.healthportaljavaserver.model.Provider;
import com.example.healthportaljavaserver.repositories.ProviderRepository;

@RestController
//@CrossOrigin(origins="https://polar-dawn-38329.herokuapp.com", allowCredentials="true",allowedHeaders="*")
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class ProviderService {
	
	@Autowired
	ProviderRepository providerRepository;
	
	@GetMapping("/api/providers")
	public List<Provider> findAllProviders() {
		return (List<Provider>)providerRepository.findAll();
	}
	
	@GetMapping("/api/providers/user/{userId}")
	public Provider findProviderById(@PathVariable("userId") Integer id) {
		Provider provider;
		try {
			provider = providerRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return provider;
	}
	
	@DeleteMapping("/api/providers/user/{userId}")
	public void deleteProvider(@PathVariable("userId") Integer id) {
	  providerRepository.deleteById(id);
	}
	
	@PostMapping("/api/providers")
	public Provider createUser(@RequestBody Provider provider) {
	  		return providerRepository.save(provider);
	}
	
	@PostMapping("/api/providers/register")
	public Provider registerProvider(@RequestBody Provider user, HttpSession session) {
		boolean exists = false;
		try {
		Provider existingUser = providerRepository.findById(user.getId()).get();
		} catch (Exception e) {
			session.setAttribute("currentUser", user);
			return providerRepository.save(user);
		}
			session.setAttribute("currentUser", user);
			return user;
	}
	
	@PutMapping("/api/providers/user/{userId}")
	public Provider updateProvider(
			@PathVariable("userId") Integer id,
			@RequestBody Provider newProvider) {
		Provider provider;
		try {
			provider = providerRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		provider.set(newProvider);
		return providerRepository.save(provider);
	}
	
	@GetMapping("/api/practice/{practiceId}")
	public List<Provider> findAllProvidersForPractice(@PathVariable("practiceId") Integer id){
		return providerRepository.findAllProvidersForPractice(id);
	}

}
