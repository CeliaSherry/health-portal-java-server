package com.example.healthportaljavaserver.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Provider extends User {
	
	@OneToMany(mappedBy="provider")
	private List<Article> authoredArticles = new ArrayList<>();
	
	@ManyToOne()
	@JsonIgnore
	private Practice practice;
	
	public Practice getPractice() {
		return practice;
	}
	
	public void setPractice(Practice practice) {
		this.practice = practice;
	}
	
	@OneToMany(mappedBy="provider")
	private List<Customer> customers = new ArrayList<>();
	
	public Provider() {}

	public Provider(List<Article> authoredArticles, Practice practice) {
		super();
		this.authoredArticles = authoredArticles;
		this.practice = practice;
	}


	public void set(Provider provider) {
		super.set(provider);
	//	this.authoredArticles = provider.authoredArticles;
	//	this.practice = provider.practice;
	}

	public List<Article> getAuthoredArticles() {
		return authoredArticles;
	}

	public void setAuthoredArticles(List<Article> authoredArticles) {
		this.authoredArticles = authoredArticles;
	}
	
	@Override
	public String getRole() {
		return "PRO";
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	

}
