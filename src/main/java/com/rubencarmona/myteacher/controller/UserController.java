package com.rubencarmona.myteacher.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.rubencarmona.myteacher.domain.Lesson;
import com.rubencarmona.myteacher.domain.StudyPlan;
import com.rubencarmona.myteacher.domain.Teacher;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.domain.UserStudyPlan;
import com.rubencarmona.myteacher.service.MyTeacherService;

/**
 * Clase UserController * Gestiona las peticiones de la zona de los usuarios.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Controller
public class UserController {

  private static final Logger log = LogManager.getLogger(RegisterController.class);

  @Autowired
  MyTeacherService myTeacherService;
  Optional<User> user = Optional.empty();
  Optional<Teacher> teacher = Optional.empty();


  private int totalRemainingSessions = 0;


  @GetMapping({"/user/user", "/user/user.html"})
  public String privateUser(Model model, HttpSession session) {
    log.info("privateUser()");
    model.addAttribute("today", new Date());

    teacher = myTeacherService.findByIdTeacher(0);
    session.setAttribute("teacher", teacher.get());

    int[] scoreArray = new int[] {1, 2, 3, 4, 5};
    model.addAttribute("scoreArray", scoreArray);

    List<Teacher> myTeachers = myTeacherService.findAllTeacher();

    for (int i = 0; i < myTeachers.size(); i++) {
      Teacher teacher = myTeachers.get(i);
      log.info(teacher);
      model.addAttribute("id" + teacher.getTeacherid(),
          teacher.getName() + " " + teacher.getSurname());
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();


    /**
     * Comprobamos si el usuario logueado es un administrador.
     */
    if (auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {

      boolean admin = true;

      log.info("Admin Authentication:" + admin);
      session.setAttribute("userAdmin", admin);

    } else {
      boolean admin = false;
      log.info("Admin Authentication: " + admin);
      session.setAttribute("userAdmin", admin);

    } ;

    if (auth.getAuthorities().stream()
        .anyMatch(role -> role.getAuthority().equals("ROLE_TEACHER"))) {

      boolean teacher = true;

      log.info("Teacher Authentication:" + teacher);
      session.setAttribute("userTeacher", teacher);

    } else {
      boolean teacher = false;
      log.info("Teacher Authentication: " + teacher);
      session.setAttribute("userTeacher", teacher);

    } ;


    UserDetails userDetail = (UserDetails) auth.getPrincipal();
    user = myTeacherService.findByMailUser(userDetail.getUsername());
    model.addAttribute("listLesson", myTeacherService.findAllLessonUser(user.get().getUserid()));
    session.setAttribute("user", user.get());


    if (user.get().getEnabled() == (byte) 1) {
      return "/user/user";
    } else {
      return "redirect:/logout";
    }



  }

  @GetMapping({"/user/profile", "/user/profile.html"})
  public String privateProfile(Model model) {
    log.info("privateProfile()");
    model.addAttribute("userEditForm", new User());
    return "/user/profile";
  }

  @GetMapping({"/user/studyplan", "/user/studyplan.html"})
  public String privateStudyPlanProfile(Model model, HttpSession session) {
    log.info("privateStudyPlanProfile()");


    List<UserStudyPlan> userStudyPlan =
        myTeacherService.findByUser((User) session.getAttribute("user"));


    model.addAttribute("userStudyPlan", userStudyPlan);



    List<StudyPlan> listPlan = myTeacherService.findAllStudyPlan();
    listPlan.remove(myTeacherService.findByIdStudyPlan(39).get());



    model.addAttribute("listPlan", listPlan);


    return "/user/studyplan";
  }

  @GetMapping({"/user/assessment/{id}/{assessment}", "/user/assessment/{assessment}"})
  public String privateAssessmentStudyPlan(@PathVariable("id") Integer id,
      @PathVariable("assessment") Integer assessment) {
    log.info("privateAssessmentStudyPlan()");

    Lesson lesson = myTeacherService.findByIdLesson(id).get();
    lesson.setAssessment(assessment);
    myTeacherService.saveLesson(lesson);

    return "redirect:/user/user";
  }

  @GetMapping({"/user/deletestudyplan/{id}", "/user/deletestudyplan.html/{id}"})
  public String privateDeleteStudyPlanProfile(@PathVariable("id") Integer id) {
    log.info("privateDeleteStudyPlanProfile()");
    myTeacherService.deleteByIdUserStudyPlan(id);

    return "redirect:/user/studyplan";
  }

  @GetMapping({"/user/addstudyplan/{id}", "/user/addstudyplan.html/{id}"})
  public String privateAddStudyPlanProfile(@PathVariable("id") Integer id, HttpSession session) {
    log.info("privateAddStudyPlanProfile()");

    StudyPlan studyPlan = myTeacherService.findByIdStudyPlan(id).get();
    User user = (User) session.getAttribute("user");

    myTeacherService.saveUserStudyPlan(new UserStudyPlan(studyPlan, user));

    return "redirect:/user/studyplan";
  }



  @PostMapping({"/user/editprofile", "/user/editprofile.html"})
  public String privateEditProfile(@ModelAttribute("userEditForm") User user) {
    log.info("privateEditProfile()");

    int userId = user.getUserid();
    String name = user.getName();
    String surname = user.getSurname();
    String address = user.getAddress();
    int phone = user.getPhone();
    String mail = user.getMail();
    String dni_nie = user.getDniNie();
    String pwd = user.getPassword();
    String bankaccount = user.getBankaccountnumber();


    myTeacherService
        .saveUser(new User(userId, address, bankaccount, dni_nie, mail, name, pwd, phone, surname));
    log.info("USER EDIT:" + user);

    return "/user/profile";
  }


  @GetMapping({"/user/calendar", "/user/calendar.html"})
  public String privateCalendar(Model model, HttpSession session) {
    log.info("privateCalendar()");
    totalRemainingSessions = 0;

    Date date = new Date();
    List<Teacher> myTeachers = myTeacherService.findAllTeacher();
    model.addAttribute("teachers", myTeachers);

    List<UserStudyPlan> userStudyPlan =
        myTeacherService.findByUserAndEndingdateGreaterThanEqualOrderByRemainingsessionsAsc(
            (User) session.getAttribute("user"), date);

    for (UserStudyPlan userStudyPlanIt : userStudyPlan) {
      totalRemainingSessions += userStudyPlanIt.getRemainingsessions();
    }

    model.addAttribute("totalSessions", totalRemainingSessions);

    log.info("USER: " + session.getAttribute("user"));
    log.info("USER STUDY PLAN: " + userStudyPlan);
    log.info("TOTAL REMAINING SESSIONS: " + totalRemainingSessions);

    model.addAttribute("listLesson",
        myTeacherService.findAllLessonUser(((User) session.getAttribute("user")).getUserid()));

    model.addAttribute("listAllLessonNotLike", myTeacherService
        .findAllLessonUserNotLike(((User) session.getAttribute("user")).getUserid()));

    log.info(myTeacherService.findAllLessonUser(((User) session.getAttribute("user")).getUserid()));
    log.info(myTeacherService
        .findAllLessonUserNotLike(((User) session.getAttribute("user")).getUserid()));
    return "/user/calendar";
  }

  @GetMapping("/user/calendar/{idTeacher}")
  public String privateCalendarTeacherId(@PathVariable("idTeacher") Integer idTeacher,
      HttpSession session, Model model) {
    log.info("privateCalendarTeacherId()");

    teacher = myTeacherService.findByIdTeacher(idTeacher);

    session.setAttribute("teacher", teacher.get());

    model.addAttribute("listLesson",
        myTeacherService.findAllLessonUser(((User) session.getAttribute("user")).getUserid()));

    return "redirect:/user/calendar";
  }


  @GetMapping({"/user/addEvent/{date}"})
  public String userAddEvent(@PathVariable("date") String date, HttpSession session) {
    log.info("userAddEvent()");
    log.info("DATE: " + date + " USER: " + user + " MODEL TEACHER: " + teacher);

    date = date.substring(0, 33);

    log.info("DATE SUBSTRING: " + date);

    if (totalRemainingSessions > 0) {

      List<UserStudyPlan> userStudyPlanOnly = myTeacherService
          .findByUserAndEndingdateGreaterThanEqualAndRemainingsessionsGreaterThanOrderByEndingdateAsc(
              (User) session.getAttribute("user"), new Date(), 0);

      log.info("ALL USER STUDY PLAN GREATER CERO" + userStudyPlanOnly);

      if (userStudyPlanOnly != null) {

        int remainingSession = userStudyPlanOnly.get(0).getRemainingsessions();
        remainingSession--;
        userStudyPlanOnly.get(0).setRemainingsessions(remainingSession);

        myTeacherService.saveLesson(new Lesson(teacher.get(), user.get(), new Date(date)));
        log.info("Save Lesson()");

      }

    } else {
      log.info("No classes left");
    }

    return "redirect:/user/calendar";
  }

  @GetMapping({"/user/deleteEvent/{idEvent}"})
  public String userDeleteEvent(@PathVariable("idEvent") Integer idEvent, HttpSession session) {
    log.info("userDeleteEvent()");

    if (totalRemainingSessions >= 0) {
      myTeacherService.deleteByIdLessoon(idEvent);

      List<UserStudyPlan> userStudyPlanOnly =
          myTeacherService.findByUserAndEndingdateGreaterThanEqualOrderByEndingdateAsc(
              (User) session.getAttribute("user"), new Date());

      log.info("User Study Plan DELETE Event" + userStudyPlanOnly);

      UserStudyPlan remainingSession = userStudyPlanOnly.get(0);
      int remainingSessionPlan = remainingSession.getRemainingsessions();
      remainingSessionPlan++;
      remainingSession.setRemainingsessions(remainingSessionPlan);
      myTeacherService.saveUserStudyPlan(remainingSession);

      log.info("Delete Lesson()" + remainingSession);

    }

    return "redirect:/user/calendar";
  }



}
