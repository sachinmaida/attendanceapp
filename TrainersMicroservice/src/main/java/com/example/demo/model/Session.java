package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Session {
	private int sessionid;
	private String sessiondesc;
	private String sessiondate;
	private String sessiontime;
}
