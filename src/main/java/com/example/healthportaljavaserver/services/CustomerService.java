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
import com.example.healthportaljavaserver.model.User;
import com.example.healthportaljavaserver.repositories.ArticleRepository;
import com.example.healthportaljavaserver.repositories.CustomerRepository;
import com.example.healthportaljavaserver.repositories.ProviderRepository;

@RestController
@CrossOrigin(origins="https://cs5610-csherry-healthportal.herokuapp.com", allowCredentials="true",allowedHeaders="*")
//@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired 
	ArticleRepository articleRepository;
	@Autowired
	ProviderRepository providerRepository;
	
	@GetMapping("/api/customers")
	public List<Customer> findAllCustomers() {
		return (List<Customer>)customerRepository.findAll();
	}
	
	@GetMapping("/api/customers/user/{userId}")
	public Customer findCustomerById(@PathVariable("userId") Integer id) {
		Customer customer;
		try {
			customer = customerRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return customer;
	}
	
	@DeleteMapping("/api/customers/user/{userId}")
	public void deleteCustomer(@PathVariable("userId") Integer id) {
	  customerRepository.deleteById(id);
	}
	
	@PostMapping("/api/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
	  		return customerRepository.save(customer);
	}
	
	@PostMapping("/api/customers/register")
	public Customer registerCustomer(@RequestBody Customer user, HttpSession session) {
		boolean exists = false;
		try {
		Customer existingUser = customerRepository.findCustomerById(user.getId()).get(0);
		} catch (Exception e) {
			session.setAttribute("currentUser", user);
			return customerRepository.save(user);
		}
			session.setAttribute("currentUser", user);
			return user;
	}
	
	@PutMapping("/api/customers/user/{userId}")
	public Customer updateCustomer(
			@PathVariable("userId") Integer id,
			@RequestBody Customer newCustomer) {
		Customer customer;
		try {
			customer = customerRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		customer.set(newCustomer);
		return customerRepository.save(customer);
	}
	
	@PostMapping("/api/customer/{customerId}/article/{articleId}")
	public void favoriteArticle(
			@PathVariable("customerId") Integer customerId, @PathVariable("articleId") Integer articleId) {
		Article article = articleRepository.findById(articleId).get();
		Customer customer = customerRepository.findById(customerId).get();
		customer.favoriteArticle(article);
		customerRepository.save(customer);
	}
	
	@GetMapping("/api/customer/{customerId}/provider")
	public Provider findProvider(@PathVariable("customerId") Integer customerId) {
		return customerRepository.findProvider(customerId);
	}
	
	@PostMapping("api/customer/{customerId}/provider/{providerId}")
	public void saveProvider(@PathVariable("customerId") Integer customerId, @PathVariable("providerId") Integer providerId) {
		Customer customer = customerRepository.findById(customerId).get();
		Provider provider = providerRepository.findById(providerId).get();
		customer.setProvider(provider);
		customerRepository.save(customer);
	}
	
	@DeleteMapping("api/customer/{customerId}/provider")
	public void unsaveProvider(@PathVariable("customerId") Integer customerId) {
		Customer customer = customerRepository.findById(customerId).get();
		customer.removeProvider();
		customerRepository.save(customer);
	}
	
	@DeleteMapping("/api/customer/{customerId}/article/{articleId}")
	public void deleteArticleFavorite(@PathVariable("customerId") Integer customerId, @PathVariable("articleId") Integer articleId) {
		Article article = articleRepository.findById(articleId).get();
		Customer customer = customerRepository.findById(customerId).get();
		customer.unfavoriteArticle(article);
		customerRepository.save(customer);
	}
	
	
	@GetMapping("api/customer/{customerId}/article")
	public Iterable<Article> findFavoritedArticles(@PathVariable("customerId") Integer id) {
		Customer customer = customerRepository.findCustomerById(id).get(0);
		return customer.getFavoritedArticles();
	}
	
	
	
}
