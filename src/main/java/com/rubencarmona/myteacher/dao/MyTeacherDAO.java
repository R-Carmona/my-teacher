package com.rubencarmona.myteacher.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rubencarmona.myteacher.domain.AuthoritiesUsers;
import com.rubencarmona.myteacher.domain.Authority;
import com.rubencarmona.myteacher.domain.Lesson;
import com.rubencarmona.myteacher.domain.StudyPlan;
import com.rubencarmona.myteacher.domain.Teacher;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.domain.UserStudyPlan;
import com.rubencarmona.myteacher.repository.AuthoritiesUsersRepository;
import com.rubencarmona.myteacher.repository.AuthorityRepository;
import com.rubencarmona.myteacher.repository.LessonRepository;
import com.rubencarmona.myteacher.repository.StudyPlanRespository;
import com.rubencarmona.myteacher.repository.TeacherRepository;
import com.rubencarmona.myteacher.repository.UserRepository;
import com.rubencarmona.myteacher.repository.UserStudyPlanRepository;

/**
 * Clase MyTeacherDAO * Recopila todos los DAO de la aplicación, con solo una declaración de la
 * clase tendremos acceso a todos los DAO.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Service
public class MyTeacherDAO {

  @Autowired
  public AuthoritiesUsersRepository authoritiesUsersRepository;

  @Autowired
  public AuthorityRepository authorityRepository;

  @Autowired
  public LessonRepository lessonRepository;

  @Autowired
  public StudyPlanRespository studyPlanRespository;

  @Autowired
  public TeacherRepository teacherRepository;

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public UserStudyPlanRepository userStudyPlanRepository;


  // AuthoritiesUsersRepository///////////////////////////////////////////////
  public <S extends AuthoritiesUsers> S saveAuthoritiesUsers(S entity) {
    return authoritiesUsersRepository.save(entity);
  }

  public List<AuthoritiesUsers> findAllAuthoritiesUsers() {
    return authoritiesUsersRepository.findAll();
  }

  public Optional<AuthoritiesUsers> findByIdAuthoritiesUsers(Integer id) {
    return authoritiesUsersRepository.findById(id);
  }

  public void deleteByIdAuthoritiesUsers(Integer id) {
    authoritiesUsersRepository.deleteById(id);
  }

  public Optional<AuthoritiesUsers> findByIdAuthoritiesUsersAndAndauthorityid(Integer userId,
      Integer authorityId) {
    return authoritiesUsersRepository.findByUseridAndAuthorityid(userId, authorityId);
  }



  public void deleteAuthoritiesUsers(AuthoritiesUsers entity) {
    authoritiesUsersRepository.delete(entity);
  }

  public <S extends AuthoritiesUsers> S updateAuthoritiesUsers(S entity) {
    return authoritiesUsersRepository.save(entity);
  }
  ///////////////////////////////////////////////////////////////////////////

  // AuthorityRepository//////////////////////////////////////////////////////
  public <S extends Authority> S saveAuthority(S entity) {
    return authorityRepository.save(entity);
  }

  public <S extends Authority> S updateAuthority(S entity) {
    return authorityRepository.save(entity);
  }

  public List<Authority> findAllAuthority() {
    return authorityRepository.findAll();
  }

  public Optional<Authority> findByIdAuthority(Integer id) {
    return authorityRepository.findById(id);
  }

  public void deleteByIdAuthority(Integer id) {
    authorityRepository.deleteById(id);
  }
  ///////////////////////////////////////////////////////////////////////////

  // LessonRepository/////////////////////////////////////////////////////////


  public <S extends Lesson> S updateLesson(S entity) {
    return lessonRepository.save(entity);
  }

  public <S extends Lesson> S saveLesson(S entity) {
    return lessonRepository.save(entity);
  }

  public List<Lesson> findAllLesson() {
    return lessonRepository.findAll();
  }

  public List<Lesson> findAllLessonUser(Integer userId) {
    return lessonRepository.findByUser(userId);
  }

  public List<Lesson> findAllLessonUserNotLike(Integer userId) {
    return lessonRepository.findByUserNotLike(userId);
  }

  public List<Lesson> findAllLessonTeacher(Integer teacherId) {
    return lessonRepository.findByTeacher(teacherId);
  }



  // public List<Lesson> findAllLessonDate(Date date) {
  // return lessonRepository.findByStardate(date);
  // }

  public List<Lesson> findByUserAndTeacherLessson(Integer idUser, Integer idTeacher) {
    return lessonRepository.findByUserAndTeacher(idUser, idTeacher);
  }

  public List<Lesson> findAllLessonAssessment(Integer assessment) {
    return lessonRepository.findByAssessment(assessment);
  }

  public Optional<Lesson> findByIdLesson(Integer id) {
    return lessonRepository.findById(id);
  }

  public void deleteByIdLessoon(Integer id) {
    lessonRepository.deleteById(id);
  }
  ///////////////////////////////////////////////////////////////////////////

  // StudyPlanRespository/////////////////////////////////////////////////////
  public <S extends StudyPlan> S saveStudyPlan(S entity) {
    return studyPlanRespository.save(entity);
  }

  public <S extends StudyPlan> S updateStudyPlan(S entity) {
    return studyPlanRespository.save(entity);
  }

  public List<StudyPlan> findAllStudyPlan() {
    return studyPlanRespository.findAll();
  }

  public Optional<StudyPlan> findByIdStudyPlan(Integer id) {
    return studyPlanRespository.findById(id);
  }

  public void deleteByIdStudyPlan(Integer id) {
    studyPlanRespository.deleteById(id);
  }
  ///////////////////////////////////////////////////////////////////////////

  // TeacherRepository////////////////////////////////////////////////////////
  public <S extends Teacher> S saveTeacher(S entity) {
    return teacherRepository.save(entity);
  }

  public <S extends Teacher> S updateTeacher(S entity) {
    return teacherRepository.save(entity);
  }

  public List<Teacher> findAllTeacher() {
    return teacherRepository.findAll();
  }

  public Optional<Teacher> findByIdTeacher(Integer id) {
    return teacherRepository.findById(id);
  }

  public Optional<Teacher> findByUserid(Integer id) {
    return teacherRepository.findByUserid(id);
  }

  public void deleteByIdTeacher(Integer id) {
    teacherRepository.deleteById(id);
  }

  public void disabledTeacher(Integer idTeacher) {
    Optional<Teacher> getTeacher = teacherRepository.findById(idTeacher);
    Teacher teacher = getTeacher.get();
    teacher.setEnabled((byte) 0);
    teacherRepository.save(teacher);
  }

  public void enabledTeacher(Integer idTeacher) {
    Optional<Teacher> getTeacher = teacherRepository.findById(idTeacher);
    Teacher teacher = getTeacher.get();
    teacher.setEnabled((byte) 1);
    teacherRepository.save(teacher);
  }

  ///////////////////////////////////////////////////////////////////////////

  // UserRepository///////////////////////////////////////////////////////////
  public void saveUser(User user) {
    userRepository.save(user);
    int userId = userRepository.findByMail(user.getMail()).get().getUserid();
    saveAuthoritiesUsers(new AuthoritiesUsers(userId, 2));
  }

  public <S extends User> S updateUser(S entity) {
    return userRepository.save(entity);
  }

  public List<User> findAllUser() {
    return userRepository.findAll();
  }

  public Optional<User> findByIdUser(Integer id) {
    return userRepository.findById(id);
  }

  public void deleteByIdUser(Integer id) {
    userRepository.deleteById(id);
  }

  public Optional<User> findByMailUser(String mail) {
    return userRepository.findByMail(mail);
  }



  public void disableUser(Integer idUser) {
    Optional<User> getUser = userRepository.findById(idUser);
    User user = getUser.get();
    user.setEnabled((byte) 0);
    userRepository.save(user);
  }

  public void enableUser(Integer idUser) {
    Optional<User> getUser = userRepository.findById(idUser);
    User user = getUser.get();
    user.setEnabled((byte) 1);
    userRepository.save(user);
  }
  ///////////////////////////////////////////////////////////////////////////

  // UserStudyPlanRepository//////////////////////////////////////////////////
  public <S extends UserStudyPlan> S saveUserStudyPlan(S entity) {
    return userStudyPlanRepository.save(entity);
  }

  public <S extends UserStudyPlan> S updateUserStudyPlan(S entity) {
    return userStudyPlanRepository.save(entity);
  }

  public List<UserStudyPlan> findAllUserStudyPlan() {
    return userStudyPlanRepository.findAll();
  }

  public Optional<UserStudyPlan> findByIdUserStudyPlan(Integer id) {
    return userStudyPlanRepository.findById(id);
  }

  public void deleteByIdUserStudyPlan(Integer id) {
    userStudyPlanRepository.deleteById(id);
  }

  public List<UserStudyPlan> findByUser(User user) {
    return userStudyPlanRepository.findByUser(user);
  }

  public List<UserStudyPlan> findByStudyPlan(User user) {
    return userStudyPlanRepository.findByStudyPlan(user);
  }

  public List<UserStudyPlan> findByStartdate(Date startDate) {
    return userStudyPlanRepository.findByStartdate(startDate);
  }

  public List<UserStudyPlan> findByEndingdate(Date endingDate) {
    return userStudyPlanRepository.findByEndingdate(endingDate);
  }

  public List<UserStudyPlan> findByEndingdateLessThanEqual(Date endingDate) {
    return userStudyPlanRepository.findByEndingdateLessThanEqual(endingDate);
  }

  public List<UserStudyPlan> findByStartdateBetween(Date startDate, Date endDate) {
    return userStudyPlanRepository.findByStartdateBetween(startDate, endDate);
  }

  public List<UserStudyPlan> findByEndingdateGreaterThanEqual(Date endingDate) {
    return userStudyPlanRepository.findByEndingdateGreaterThanEqual(endingDate);
  }

  public List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualOrderByRemainingsessionsAsc(
      User user, Date endingDate) {
    return userStudyPlanRepository
        .findByUserAndEndingdateGreaterThanEqualOrderByRemainingsessionsAsc(user, endingDate);
  }

  public List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualAndRemainingsessionsGreaterThanOrderByEndingdateAsc(
      User user, Date endingDate, int remainingSessions) {
    return userStudyPlanRepository
        .findByUserAndEndingdateGreaterThanEqualAndRemainingsessionsGreaterThanOrderByEndingdateAsc(
            user, endingDate, remainingSessions);
  }

  public List<UserStudyPlan> findByUserNotLikeUserStudyPlan(User user) {
    return userStudyPlanRepository.findByUserNotLike(user);
  }

  public List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualOrderByEndingdateAsc(User user,
      Date endingDate) {
    return userStudyPlanRepository.findByUserAndEndingdateGreaterThanEqualOrderByEndingdateAsc(user,
        endingDate);
  }

  public List<UserStudyPlan> findByUserAndEndingdateGreaterThanEqualOrderByEndingdateDesc(User user,
      Date endingDate) {
    return userStudyPlanRepository
        .findByUserAndEndingdateGreaterThanEqualOrderByEndingdateDesc(user, endingDate);
  }



  ///////////////////////////////////////////////////////////////////////////


  ///////////////////////////////////////////////////////////////////////////



}
