package com.example.healthportaljavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String email;
	
	
	

}
