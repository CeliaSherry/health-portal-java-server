package com.example.healthportaljavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import com.example.healthportaljavaserver.model.Practice;

public interface PracticeRepository extends CrudRepository<Practice, Integer> {

	@Query("SELECT practice FROM Practice practice WHERE practice.id=:id")
	public List<Practice> findPracticeById
	(@Param("id") Integer id);

	
	@Query("SELECT practice from Practice practice")
	public List<Practice> findAllPractices();
	
	@Query("SELECT practice FROM Practice practice WHERE practice_id=:id")
	public List<Practice> findPracticeByPracticeId
	(@Param("id") String id);
	
	@Query("SELECT id FROM Practice practice WHERE practice_id=:id")
	public Integer findIdByPracticeId
	(@Param("id") String id);
	
}
