package com.rubencarmona.myteacher.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rubencarmona.myteacher.domain.StudyPlan;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.domain.UserStudyPlan;

@Repository
public interface UserStudyPlanRepository extends JpaRepository<UserStudyPlan, Integer> {

  List<UserStudyPlan> findByStudyPlan(StudyPlan studyPlan);

  List<UserStudyPlan> findByUser(User user);

  List<UserStudyPlan> findByUserNotLike(User user);

  List<UserStudyPlan> findByStudyPlan(User user);

  List<UserStudyPlan> findByStartdate(Date startDate);

  List<UserStudyPlan> findByEndingdate(Date endingDate);

  List<UserStudyPlan> findByEndingdateLessThanEqual(Date endingDate);

  List<UserStudyPlan> findByEndingdateGreaterThanEqual(Date endingDate);

  List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualOrderByRemainingsessionsAsc(User user,
      Date endingDate);

  List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualOrderByEndingdateAsc(User user,
      Date endingDate);

  List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualOrderByEndingdateDesc(User user,
      Date endingDate);

  List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualAndRemainingsessionsGreaterThanOrderByEndingdateAsc(
      User user, Date endingDate, int remainingSessions);

  List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualAndRemainingsessionsGreaterThanOrderByEndingdateDesc(
      User user, Date endingDate, int remainingSessions);

  List<UserStudyPlan> findByStartdateBetween(Date startDate, Date endDate);

  List<UserStudyPlan> findByEndingdateBetween(Date startDate, Date endDate);



}
