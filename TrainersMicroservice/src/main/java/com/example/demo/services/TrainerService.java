package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.clients.AuthClient;
import com.example.demo.controllers.TrainerNotFoundException;
import com.example.demo.exceptions.TrainerAlreadyExistsException;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.Trainer;
import com.example.demo.model.TrainerSession;
import com.example.demo.repositories.TrainerRepository;

@Service
public class TrainerService {

	@Autowired
	TrainerRepository trainerRepository;
	
	@Autowired
	AuthClient authClient;
	
	@Transactional
	public List<Trainer> getTrainerListAdmin(String token) throws TrainerNotFoundException{
		// TODO Auto-generated method stub
		AuthResponse response=authClient.getValidity(token);
		if(response.isValid())
		    return (List<Trainer>) trainerRepository.findAll();
		else
			throw new TrainerNotFoundException();
	}
	
//	@Transactional
//	public List<Movies> getMoviesListCustomer(String token) throws MovieNotFoundException {
//		// TODO Auto-generated method stub
//		AuthResponse response=authClient.getValidity(token);
//		if(response.isValid())
//		    return (List<Movies>) moviesRepository.findMoviesListCustomer();
//		else
//			throw new MovieNotFoundException();
//	}
//
	public Trainer getTrainer(String token,Integer trainer_id) throws TrainerAlreadyExistsException, TrainerNotFoundException {
		// TODO Auto-generated method stub
		//AuthResponse response=authClient.getValidity(token);
//		if(response.isValid())
//		{
			Optional<Trainer> trainer=trainerRepository.findById(trainer_id);
			if(trainer.isPresent())
				return trainer.get();
			else
				throw new TrainerNotFoundException();
//		}
//		else
//			throw new UserAlreadyExistsException();
	}
//	
	@Transactional
	public boolean modifyTrainer(String token,Trainer trainer,Integer id) throws TrainerAlreadyExistsException {
//		AuthResponse response=authClient.getValidity(token);
//		if(response.isValid())
//		{
			Trainer t=trainerRepository.findById(id).get();
			   if(t==null) {
				return false;
		     	}
			   t.setName(trainer.getName());
			   trainerRepository.save(t);
			   return true;
//		}
//		else
//			throw new UserAlreadyExistsException();
	}
	
	@Transactional
	public String removeTrainer(String userId,String token) throws TrainerAlreadyExistsException {
//		AuthResponse response = authClient.getValidity(token);
//		if(response.isValid()) {
			Set<Trainer> list = trainerRepository.getTrainerDetails(userId);
//			if(list.size()==0)
//				throw new FavoriteNotFoundException();
			trainerRepository.removeTrainerDetails(userId);
			return "Deleted successfully";
//		}
//		else
//			throw new UserAlreadyExistsException();
	}

	public Boolean addTrainer(String token, Trainer trainer) {
		// TODO Auto-generated method stub
//		AuthResponse response=authClient.getValidity(token);
//		if(response.isValid()) {
//			Trainer t=movieClient.getMovies(token, movies_id);
//			if(movie!=null) {
//				Favorites fav=Favorites.builder().user(userId).movie_id(movies_id).build();
				if(trainerRepository.save(trainer) != null)
				return true;
//			}
//			else
//			{
//				throw new MovieNotFoundException();
//			}
//		}
//		else
//		{
//			throw new UserNotFoundException();
//		}
				return false;
	}

}
