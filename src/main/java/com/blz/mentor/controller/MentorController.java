package com.blz.mentor.controller;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blz.mentor.DTO.MentorDTO;
import com.blz.mentor.model.MentorModel;
import com.blz.mentor.service.IMentorService;
import com.blz.mentor.util.Response;


/**
 * Purpose: Mentor controller to process Mentor Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V  
 */
@RestController
@RequestMapping("/mentor")
public class MentorController {

	@Autowired
	IMentorService mentorService;
	
	/**
	 * Purpose: To create Mentor
	 * @Param: mentorDTO and token
	 */
	@PostMapping("/addmentor")
	public ResponseEntity<Response> addMentor(@RequestBody MentorDTO mentorDTO, @RequestHeader String token) {
		MentorModel mentorModel = mentorService.addMentor(mentorDTO, token);
		Response response = new Response("mentor inserted successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	/**
	 * Purpose: To update Mentor details by id
	 * @Param: mentorDTO, id and token
	 */
	@PutMapping("/updatementor/{id}")
	public ResponseEntity<Response> updateMentor(@RequestBody MentorDTO mentorDTO,@PathVariable Long id, @RequestHeader String token) {
		MentorModel mentorModel = mentorService.updateMentor(mentorDTO,id, token);
		Response response = new Response("mentor updated successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Purpose: To get all Mentor details
	 * @Param: token
	 */
	@GetMapping("/getallmentors")
	public ResponseEntity<Response>  getAllMentors(@RequestHeader String token) {
		List<MentorModel> mentorModel = mentorService.getAllMentors(token);
		Response response = new Response("Getting all mentors successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Purpose: To delete Mentor details by id 
	 * @Param: mentorDTO, id and token
	 */
	@DeleteMapping("/deletementor/{id}")
	public ResponseEntity<Response>  deleteMentor(@PathVariable Long id, @RequestHeader String token) {
		MentorModel mentorModel = mentorService.deleteMentor(id, token);
		Response response = new Response("mentor deleted successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Purpose: To get Mentor data by id 
	 * @Param: id and token
	 */
	@GetMapping("/getmentorbyid/{id}")
	public ResponseEntity<Response> getMentorById(@PathVariable Long id, @RequestHeader String token) {
		Optional<MentorModel> mentorModel = mentorService.getMentorById(id, token);
		Response response = new Response("getting mentor by id successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	/**
	 * Purpose: To get Mentor data by role 
	 * @Param: mentorRole and token
	 */
	@GetMapping("/getbymentorrole/{mentorrole}")
	public ResponseEntity<Response>  getMentorByMentorRole(@RequestParam String mentorRole, @RequestHeader String token) {
		List<MentorModel> mentorModel = mentorService.getMentorByMentorRole(mentorRole, token);
		Response response = new Response("getting mentor by role successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Purpose: To get Mentorrole count 
	 * @Param: mentorRole and token
	 */
	@GetMapping("/getmentorrolecount")
	public  ResponseEntity<Response> mentorsRoleCount(@RequestParam String mentorRole, @RequestHeader String token) {
		long mentorModel = mentorService.mentorsRoleCount(mentorRole, token);
		Response response = new Response("getting mentor role count successfully", 200, mentorModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
