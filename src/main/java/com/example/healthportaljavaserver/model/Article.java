package com.example.healthportaljavaserver.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ManyToOne()
	@JsonIgnore
	private Provider author;
	private String text;
	private String date;
	
	@ManyToMany(mappedBy="favoritedArticles")
	@JsonIgnore 
	private List<Customer> favoritedCustomers;

}
