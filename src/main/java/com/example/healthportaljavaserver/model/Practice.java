package com.example.healthportaljavaserver.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Practice {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	private String address;
	private String city;
	private String state;
	private Integer zipCode;
	private String specialty;
	
	

}
