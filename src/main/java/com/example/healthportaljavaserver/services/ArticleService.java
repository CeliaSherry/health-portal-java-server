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
@CrossOrigin(origins="https://cs5610-csherry-healthportal.herokuapp.com/", allowCredentials="true",allowedHeaders="*")
//@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired 
	CustomerRepository customerRepository;
	@Autowired 
	ProviderRepository providerRepository;
	
	@PutMapping("/api/articles/{articleId}")
	public Article updateArticle(
			@PathVariable("articleId") Integer id, 
			@RequestBody Article newArticle) {
		Article article;
		try {
			article = articleRepository.findArticleById(id).get(0);
		} catch (Exception e) {
			return null;
		}
		article.set(newArticle);
		return articleRepository.save(article);
	}
	
	@DeleteMapping("/api/articles/{articleId}")
	public void deleteArticle(@PathVariable("articleId") Integer id) {
	  articleRepository.deleteById(id);
	}
	
	/*@PostMapping("/api/articles")
	public Article createArticle(@RequestBody Article article, HttpSession session) {
		Provider loggedInUser = (Provider)session.getAttribute("currentUser");
		article.setProvider(loggedInUser);
	  	return articleRepository.save(article);	
	}*/
	
	@PostMapping("/api/provider/{providerId}/articles")
	public Article createArticle(
			@PathVariable("providerId") Integer providerId, @RequestBody Article article) {
		Provider provider = providerRepository.findById(providerId).get();
		article.setProvider(provider);
		return articleRepository.save(article);
	}
	
	@GetMapping("/api/articles")
	public List<Article> findAllArticles() {
		return articleRepository.findAllArticles();
	}
	
	@GetMapping("/api/articles/{articleId}/provider")
	public Provider findAuthor(@PathVariable("articleId") Integer articleId) {
		Article article;
		Provider provider;
		try {
			article = articleRepository.findById(articleId).get();
			provider = article.getProvider();
		} catch (Exception e) {
			return null;
		}
		return provider;
	}
	
	@GetMapping("/api/articles/provider/{providerId}")
	public List<Article> findAllArticlesForProvider(@PathVariable("providerId") Integer id){
		return articleRepository.findAllArticlesForProvider(id);
	}
	
	@GetMapping("/api/articles/{articleId}")
	public Article findArticleById(@PathVariable("articleId") Integer id) {
		Article article;
		try {
			article = articleRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return article;
	}
	
	@PostMapping("/api/articles/{articleId}/customer/{customerId}")
	public void favoriteCustomer(
			@PathVariable("articleId") Integer articleId, @PathVariable("customerId") Integer customerId) {
		Article article = articleRepository.findById(articleId).get();
		Customer customer = customerRepository.findById(customerId).get();
		article.favoriteCustomer(customer);
		articleRepository.save(article);
	}
	
	@GetMapping("api/articles/{articleId}/customer")
	public Iterable<Customer> findFavoritedCustomers(@PathVariable("articleId") Integer id) {
		Article article = articleRepository.findArticleById(id).get(0);
		return article.getFavoritedCustomers();
	}
	
	

}
