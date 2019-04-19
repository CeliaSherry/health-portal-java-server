package com.example.healthportaljavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends User{
	
	@ManyToMany
	@JoinTable(name="FAVORITED", joinColumns=@JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name="ARTICLE_ID", referencedColumnName="ID"))
	@JsonIgnore
	private List<Article> favoritedArticles = new ArrayList<>();
	
	
	@ManyToOne()
	@JsonIgnore
	private Provider provider;
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public void removeProvider() {
		this.provider = null;
	}
	
	public Customer() {}

	public Customer(List<Article> favoritedArticles, Provider provider) {
		super();
		this.favoritedArticles = favoritedArticles;
		this.provider = provider;
	}

	public void set(Customer customer) {
		super.set(customer);
		//this.favoritedArticles = customer.favoritedArticles;
	}
	
	public void favoriteArticle(Article article) {
		this.favoritedArticles.add(article);
		if(!article.getFavoritedCustomers()
				.contains(this)) {
			article.getFavoritedCustomers().add(this);
		}
	}
	
	public void unfavoriteArticle(Article article) {
		this.favoritedArticles.remove(article);
		if(article.getFavoritedCustomers().contains(this)) {
			article.getFavoritedCustomers().remove(this);
		}
	}
	
	public List<Article> getFavoritedArticles() {
		return favoritedArticles;
	}

	public void setFavoritedArticles(List<Article> favoritedArticles) {
		this.favoritedArticles = favoritedArticles;
	}
	
	@Override
	public String getRole() {
		return "CUS";
	}
	
	//@OneToMany
	//private List<Practice> favoritedPractice = new ArrayList<>();

}
