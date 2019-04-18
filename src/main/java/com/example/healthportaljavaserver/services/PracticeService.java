package com.example.healthportaljavaserver.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthportaljavaserver.model.Article;
import com.example.healthportaljavaserver.model.Practice;
import com.example.healthportaljavaserver.model.Provider;
import com.example.healthportaljavaserver.repositories.PracticeRepository;

@RestController
@CrossOrigin(origins="https://cs5610-csherry-healthportal.herokuapp.com/", allowCredentials="true",allowedHeaders="*")
//@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class PracticeService {

	@Autowired
	PracticeRepository practiceRepository;
	
	
	@PutMapping("/api/practices/{practiceId}")
	public Practice updatePractice(
			@PathVariable("practiceId") Integer id, 
			@RequestBody Practice newPractice) {
		Practice practice;
		try {
			practice = practiceRepository.findPracticeById(id).get(0);
		} catch (Exception e) {
			return null;
		}
		practice.set(newPractice);
		return practiceRepository.save(practice);
	}
	
	@DeleteMapping("/api/practices/{practiceId}")
	public void deletePractice(@PathVariable("practiceId") Integer id) {
		practiceRepository.deleteById(id);
	}
	
	@PostMapping("/api/practices")
	public Practice createPractice(@RequestBody Practice practice) {
	  	return practiceRepository.save(practice);	
	}
	
	@GetMapping("/api/practices")
	public List<Practice> findAllPractices() {
		return practiceRepository.findAllPractices();
	}
	
	@GetMapping("/api/practices/{practiceId}")
	public Practice findPracticeById(@PathVariable("practiceId") Integer id) {
		Practice practice;
		try {
			practice = practiceRepository.findPracticeById(id).get(0);
		} catch (Exception e) {
			return null;
		}
		return practice;
	}
	
	@GetMapping("/api/practiceId/{practiceId}")
	public Practice findPracticeByPracticeId(@PathVariable("practiceId") String id) {
		Practice practice;
		try {
			practice = practiceRepository.findPracticeByPracticeId(id).get(0);
		} catch (Exception e) {
			return null;
		}
		return practice;
	}
	
	
	@GetMapping("/api/practiceId/{practiceId}/practice") public Integer
	findIdByPractice(@PathVariable("practiceId") String id) { 
		Integer practiceId;
	  try { practiceId = practiceRepository.findIdByPracticeId(id); } 
	  catch (Exception e) { 
		  return null; } 
	  return practiceId; 
	  }
	 
	
}
