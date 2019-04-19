package com.example.healthportaljavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.healthportaljavaserver.model.Article;
import com.example.healthportaljavaserver.model.Customer;
import com.example.healthportaljavaserver.model.Provider;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	@Query("SELECT customer FROM Customer customer WHERE customer.username=:username")
	public List<Customer> findCustomerByUsername
	(@Param("username") String username);
	
	@Query("SELECT customer FROM Customer customer WHERE customer.id=:id")
	public List<Customer> findCustomerById
	(@Param("id") Integer id);
	
	@Query("SELECT customer from Customer customer WHERE customer.username=:username AND customer.password=:password")
	public List<Customer> findCustomerByCredentials(@Param("username") String username, @Param("password") String password);
	
	@Query("SELECT provider FROM Customer customer WHERE customer.id=:id")
	public Provider findProvider (@Param("id") Integer id);

	
}
