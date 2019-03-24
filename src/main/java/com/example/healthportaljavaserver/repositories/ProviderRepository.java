package com.example.healthportaljavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import com.example.healthportaljavaserver.model.Provider;


public interface ProviderRepository extends CrudRepository<Provider, Integer> {

	@Query("SELECT provider FROM Provider provider WHERE provider.username=:username")
	public List<Provider> findProviderByUsername
	(@Param("username") String username);
	
	@Query("SELECT provider from Provider provider WHERE provider.username=:username AND provider.password=:password")
	public List<Provider> findProviderByCredentials(@Param("username") String username, @Param("password") String password);

	
}
