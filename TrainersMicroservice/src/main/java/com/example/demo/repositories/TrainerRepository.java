package com.example.demo.repositories;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

//	@Query("select c from Trainer c where c.Active=1")
//	List<Movies> findMoviesListCustomer();
//	@Query("SELECT m FROM Movies m WHERE m.active=true AND m.dateOfLaunch< current_date")
//	public Set<Movies> getMoviesCustomer();
	
	@Query("SELECT m FROM Trainer m WHERE m.Name=?1")
	Set<Trainer> getTrainerDetails(String user);
	
	@Modifying
	@Query(value="delete from Trainer c where c.Name=?1 ")
	void removeTrainerDetails(String userId);
}
