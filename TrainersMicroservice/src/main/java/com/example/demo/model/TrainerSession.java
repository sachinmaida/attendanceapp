package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainerSession {
	private String name;
	private Long contactNumber;
	private String email;
	private Integer skill_id;
	private int session_id;
	private String sessiondesc;
	private String sessiondate;
	private String sessiontime;
}
