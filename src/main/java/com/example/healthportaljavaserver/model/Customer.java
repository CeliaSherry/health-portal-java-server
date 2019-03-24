package com.example.healthportaljavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	public Customer() {}

	public Customer(List<Article> favoritedArticles) {
		super();
		this.favoritedArticles = favoritedArticles;
	}

	public void set(Customer customer) {
		super.set(customer);
		this.favoritedArticles = customer.favoritedArticles;
	}


	public List<Article> getFavoritedArticles() {
		return favoritedArticles;
	}

	public void setFavoritedArticles(List<Article> favoritedArticles) {
		this.favoritedArticles = favoritedArticles;
	}
	
	//@OneToMany
	//private List<Practice> favoritedPractice = new ArrayList<>();

}
