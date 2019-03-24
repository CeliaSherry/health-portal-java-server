package com.example.healthportaljavaserver.model;
import java.util.List;
import com.example.healthportaljavaserver.model.Provider;
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
	private Provider provider;
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	
	private String text;
	private String date;
	
	@ManyToMany(mappedBy="favoritedArticles")
	@JsonIgnore 
	private List<Customer> favoritedCustomers;

}
