package com.blz.mentor.service;
import java.util.List;
import java.util.Optional;

import com.blz.mentor.DTO.MentorDTO;
import com.blz.mentor.model.MentorModel;


public interface IMentorService {

	MentorModel addMentor(MentorDTO mentorDTO,String token);
	
	MentorModel updateMentor(MentorDTO mentorDTO,Long id,String token);
	
	List<MentorModel> getAllMentors(String token);
	
	MentorModel deleteMentor(Long id,String token);
	
	Optional<MentorModel> getMentorById(Long id,String token);
	
	List<MentorModel> getMentorByMentorRole(String mentorRole,String token);

	long mentorsRoleCount(String mentorRole, String token);
	
	


}
