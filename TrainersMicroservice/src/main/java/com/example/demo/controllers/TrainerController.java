package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.clients.AuthClient;
import com.example.demo.clients.SessionClient;
import com.example.demo.exceptions.TrainerAlreadyExistsException;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.Session;
import com.example.demo.model.Trainer;
import com.example.demo.model.TrainerSession;
import com.example.demo.services.TrainerService;

@RestController
public class TrainerController {
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private AuthClient authClient;
	
	@Autowired 
	private SessionClient sessionClient;
	
	@GetMapping("/admin")
	public List<Trainer> getAdminItems(@RequestHeader("Authorization") String token) throws TrainerNotFoundException{
		return trainerService.getTrainerListAdmin(token);
	}
	
	@GetMapping("/{trainer_id}")
	public Trainer getTrainer(@RequestHeader("Authorization") String token,@PathVariable Integer trainer_id) throws  TrainerAlreadyExistsException, TrainerNotFoundException {
		return trainerService.getTrainer(token,trainer_id);
	}
	
	@PutMapping("/modify/{id}")
	public boolean modifyTrainer(@RequestHeader("Authorization") String token,@RequestBody Trainer trainer,@PathVariable("id") Integer id) throws TrainerAlreadyExistsException {
		return trainerService.modifyTrainer(token,trainer,id);
	}
	
	@PostMapping("/addTrainer")
	public Boolean addTrainer(@RequestHeader("Authorization") String token,@RequestBody Trainer trainer) throws TrainerNotFoundException, TrainerAlreadyExistsException {
		return trainerService.addTrainer(token,trainer);
	}
	
	@DeleteMapping("/{userId}")
	public String deleteTrainer(@PathVariable(name="userId") final String userId, @RequestHeader("Authorization") String token) throws TrainerAlreadyExistsException {
		return trainerService.removeTrainer(userId,token);
	}
	
	@PostMapping("/register")
	public void registerTrainerSession(@RequestBody TrainerSession trainerSessionDetails,@RequestHeader("Authorization") String token) throws TrainerNotFoundException {
		AuthResponse response=authClient.getValidity(token);
		if(response.isValid())
		{
			Session session=new Session();
			session.setSessiondate(trainerSessionDetails.getSessiondate());
			session.setSessiondesc(trainerSessionDetails.getSessiondesc());
			session.setSessionid(trainerSessionDetails.getSession_id());
			session.setSessiontime(trainerSessionDetails.getSessiontime());
			sessionClient.addSession(token, session);
			
			Trainer trainer=new Trainer();
			trainer.setContactNumber(trainerSessionDetails.getContactNumber());
			trainer.setEmail(trainerSessionDetails.getEmail());
			//trainer.setId(trainerSessionDetails.getId());
			//trainer.setName(trainerSessionDetails);
			trainer.setName(trainerSessionDetails.getName());
			trainer.setName(trainerSessionDetails.getName());
			trainer.setSkill_id(trainerSessionDetails.getSkill_id());
			trainerService.addTrainer(token, trainer);
		}
		else
			throw new TrainerNotFoundException();
	}
}


