package com.example.healthportaljavaserver.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Provider extends User {
	
	@OneToMany(mappedBy="provider")
	private List<Article> authoredArticles = new ArrayList<>();

}
