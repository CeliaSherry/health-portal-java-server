package com.example.healthportaljavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.healthportaljavaserver.model.User;


public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("SELECT user FROM User user WHERE user.username=:username")
	public List<User> findUserByUsername
	(@Param("username") String username);
	
	@Query("SELECT user from User user WHERE user.username=:username AND user.password=:password")
	public List<User> findUserByCredentials(@Param("username") String username, @Param("password") String password);
}
