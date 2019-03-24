package com.example.healthportaljavaserver.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

public class Provider extends User {
	
	@OneToMany(mappedBy="author")
	private List<Article> authoredArticles = new ArrayList<>();

}
