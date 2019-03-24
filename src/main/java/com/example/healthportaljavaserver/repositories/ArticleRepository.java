package com.example.healthportaljavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.healthportaljavaserver.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

	@Query("SELECT article FROM Article article WHERE article.id=:id")
	public List<Article> findArticleById
	(@Param("id") Integer id);
	
	@Query("SELECT article from Article article WHERE provider_id=:id")
	public List<Article> findAllArticlesForProvider(@Param("id") Integer id);
	
	@Query("SELECT article from Article article")
	public List<Article> findAllArticles();
	
}
