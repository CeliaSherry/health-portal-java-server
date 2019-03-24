package com.example.healthportaljavaserver.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Provider extends User {
	
	@OneToMany(mappedBy="provider")
	private List<Article> authoredArticles = new ArrayList<>();
	
	public Provider() {}

	
	
	public Provider(List<Article> authoredArticles) {
		super();
		this.authoredArticles = authoredArticles;
	}


	public void set(Provider provider) {
		super.set(provider);
		this.authoredArticles = provider.authoredArticles;
	}

	public List<Article> getAuthoredArticles() {
		return authoredArticles;
	}

	public void setAuthoredArticles(List<Article> authoredArticles) {
		this.authoredArticles = authoredArticles;
	}

}
