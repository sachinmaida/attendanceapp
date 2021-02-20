package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TrainersDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="trainer_name")
	private String user;
	
	@Column(name="skill_id")
	private Integer skill;
	
	@Column(name="session_id")
	private Integer session;
}
