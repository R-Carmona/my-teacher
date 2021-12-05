package com.rubencarmona.myteacher.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.rubencarmona.myteacher.dao.MyTeacherDAO;
import com.rubencarmona.myteacher.domain.Lesson;
import com.rubencarmona.myteacher.domain.User;

@Service
public class MyTeacherDAOService extends MyTeacherDAO {
	
	@SuppressWarnings("unused")
	public boolean compareAndSaveLesson(Lesson lesson) {
		
		int userID = lesson.getUser();
		Optional<User> userOptional = userRepository.findById(userID);
		User user = userOptional.get();
		Date date = new Date();
		//userStudyPlanRepository.findByUserAndEndingdateGreaterThanEqualOrderByRemainingsessionsAsc(user, date);
		
		userStudyPlanRepository.findByUserAndEndingdateGreaterThanEqualAndRemainingsessionsGreaterThanOrderByRemainingsessionsAsc(user, date, 0);
		
		if(true) {
			
			System.out.println("Existe el usuario y tiene PLAN");
			//lessonRepository.save(lesson);
			return true;
		} else {
			
			System.out.println("FUERA DEL PLAN");
			return false;
		}
	}
	
	
	
	
}