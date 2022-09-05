package com.blz.mentor.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blz.mentor.model.MentorModel;


public interface MentorRepository extends JpaRepository<MentorModel, Long> {
	List<MentorModel> getMentorByMentorRole(String status);
}
