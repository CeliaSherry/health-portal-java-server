package com.example.healthportaljavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
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
	private String practiceId;
	
	@OneToMany(mappedBy="practice")
	private List<Provider> providers = new ArrayList<>();
	
	public Practice() {}
	
	public Practice(Integer id, String title, String address, String city, String state, Integer zipCode,
			String specialty, List<Provider> providers, String practiceId) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.specialty = specialty;
		this.providers = providers;
		this.practiceId = practiceId;
	}
	
	public void set(Practice practice) {
		this.title = practice.title;
		this.address = practice.address;
		this.city = practice.city;
		this.state = practice.state;
		this.zipCode = practice.zipCode;
		this.specialty = practice.specialty;
		this.providers = practice.providers;
		this.practiceId = practice.practiceId;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public String getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(String practiceId) {
		this.practiceId = practiceId;
	}
	
	

}
