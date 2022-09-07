package com.blz.mentor.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.blz.mentor.DTO.MentorDTO;
import com.blz.mentor.exception.CustomNotFoundException;
import com.blz.mentor.model.MentorModel;
import com.blz.mentor.repository.MentorRepository;


@Service
public class MentorService implements IMentorService {

	@Autowired
	MentorRepository mentorRepository;

	//	@Autowired
	//	AdminRepository adminRepository;

	@Autowired
	MailService mailService;

	//	@Autowired
	//	TokenUtil tokenUtil;

	@Autowired
	RestTemplate restTemplate;



	@Override
	public MentorModel addMentor(MentorDTO mentorDTO,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			MentorModel model = new MentorModel(mentorDTO);
			mentorRepository.save(model);
			String body = "Mentor added successfully with mentor Id"+model.getId();
			String subject = "Mentor Registration Successfull";
			mailService.send(model.getEmail(), subject, body);
			return model;
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public MentorModel updateMentor(MentorDTO mentorDTO, Long id,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<MentorModel> isMentorPresent = mentorRepository.findById(id);
			if(isMentorPresent.isPresent()) {
				isMentorPresent.get().setEmployeeId(mentorDTO.getEmployeeId());
				isMentorPresent.get().setFirstName(mentorDTO.getFirstName());
				isMentorPresent.get().setLastName(mentorDTO.getLastName());
				isMentorPresent.get().setMentorType(mentorDTO.getMentorType());
				isMentorPresent.get().setMentorRole(mentorDTO.getMentorRole());
				isMentorPresent.get().setMobileNumber(mentorDTO.getMobileNumber());
				isMentorPresent.get().setEmail(mentorDTO.getEmail());
				isMentorPresent.get().setExperienceYears(mentorDTO.getExperienceYears());
				isMentorPresent.get().setPreferredTime(mentorDTO.getPreferredTime());
				isMentorPresent.get().setStartDate(mentorDTO.getStartDate());
				isMentorPresent.get().setStatus(mentorDTO.getStatus());
				isMentorPresent.get().setMentorDescription(mentorDTO.getMentorDescription());
				isMentorPresent.get().setProfileImageURL(mentorDTO.getProfileImageURL());
				isMentorPresent.get().setCreatorUser(mentorDTO.getCreatorUser());
				isMentorPresent.get().setSupervisorId(mentorDTO.getSupervisorId());
				isMentorPresent.get().setUpdatedTimeStamp(mentorDTO.getUpdatedTimeStamp().now());
				mentorRepository.save(isMentorPresent.get());
				String body = "Mentor updated successfully with Mentor Id"+isMentorPresent.get().getId();
				String subject = "Mentor updated Successfully";
				mailService.send(isMentorPresent.get().getEmail(), subject, body);
				return isMentorPresent.get();
			}
			throw new CustomNotFoundException(400,"Mentor not present");
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public List<MentorModel> getAllMentors(String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<MentorModel> getAllMentors = mentorRepository.findAll();
			if(getAllMentors.size() > 0) {
				return getAllMentors;
			}else {
				throw new CustomNotFoundException(400,"Mentors not present");
			}
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public MentorModel deleteMentor(Long id,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<MentorModel> isMentorPresent = mentorRepository.findById(id);
			if(isMentorPresent.isPresent()) {
				mentorRepository.delete(isMentorPresent.get());
				String body = "Mentor deleted successfully with Mentor Id"+isMentorPresent.get().getId();
				String subject = "Mentor deleted Successfully";
				mailService.send(isMentorPresent.get().getEmail(), subject, body);
				return isMentorPresent.get();
			}
			throw new CustomNotFoundException(400,"Mentor not present");
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public Optional<MentorModel> getMentorById(Long id,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			return mentorRepository.findById(id);
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public List<MentorModel> getMentorByMentorRole(String mentorRole,String token ) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<MentorModel> isMentorRolePresent = mentorRepository.getMentorByMentorRole(mentorRole);
			if(isMentorRolePresent.size()>0) {
				return isMentorRolePresent;
			}
			throw new CustomNotFoundException(400,"no mentor present with that role");	
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public long mentorsRoleCount(String mentorRole,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-Admin:8067/admin/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<MentorModel> isMentorRolePresent = mentorRepository.getMentorByMentorRole(mentorRole);
			if(isMentorRolePresent.size() > 0) {
				return isMentorRolePresent.stream().count();
			}
			throw new CustomNotFoundException(400,"no mentor present with that role");	
		}
		throw new CustomNotFoundException(400,"Token not present");
	}
}
