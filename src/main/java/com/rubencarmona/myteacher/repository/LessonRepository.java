package com.rubencarmona.myteacher.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rubencarmona.myteacher.domain.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

  List<Lesson> findByUser(Integer id);

  List<Lesson> findByUserNotLike(Integer id);

  List<Lesson> findByTeacher(Integer id);

  List<Lesson> findByStarDate(Date date);

  List<Lesson> findByAssessment(Integer assessment);

  List<Lesson> findByAssessmentLessThan(Integer assessment);

  List<Lesson> findByAssessmentGreaterThan(Integer assessment);

  List<Lesson> findByStarDateBetween(Date startDate, Date endDate);

  List<Lesson> findByUserAndTeacher(Integer idUser, Integer idTeacher);


}
