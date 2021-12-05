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
import org.springframework.web.bind.annotation.PostMapping;
import com.rubencarmona.myteacher.domain.Teacher;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.service.MyTeacherService;

/**
 * Clase TeacherController * Gestiona las peticiones de la zona de los prefesores.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Controller
public class TeacherController {

  private static final Logger log = LogManager.getLogger(RegisterController.class);

  @Autowired
  MyTeacherService myTeacherService;
  Optional<User> user = Optional.empty();
  Optional<Teacher> teacher = Optional.empty();

  @GetMapping({"/teacher/teacher", "/teacher/teacher.html"})
  public String privateTeacher(Model model, HttpSession session) {
    log.info("privateTeacher()");
    model.addAttribute("today", new Date());

    int[] scoreArray = new int[] {1, 2, 3, 4, 5};
    model.addAttribute("scoreArray", scoreArray);

    List<User> listAllUser = myTeacherService.findAllUser();

    for (int i = 0; i < listAllUser.size(); i++) {
      User user = listAllUser.get(i);
      log.info("USER ADD TEACHER: " + user);
      model.addAttribute("id" + user.getUserid(),
          new String[] {user.getName() + " " + user.getSurname(), user.getPhone() + ""});
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    /**
     * Obtenemos el usuario para añadirlo a la varibale session.
     */
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
    user = myTeacherService.findByMailUser(userDetail.getUsername());

    teacher = myTeacherService.findByUserid(user.get().getUserid());
    session.setAttribute("teacher", teacher.get());

    log.info("Get Profesor. " + teacher);

    model.addAttribute("listLesson",
        myTeacherService.findAllLessonTeacher(teacher.get().getTeacherid()));

    log.info("Get List Seasoon Profesor. "
        + myTeacherService.findAllLessonTeacher(teacher.get().getTeacherid()));

    session.setAttribute("user", user.get());



    if (teacher.get().getEnabled() == (byte) 1) {
      log.info("Is a qualified Teacher." + teacher.get().getEnabled());
      return "/teacher/teacher";

    } else {
      log.info("He is an unqualified Teacher." + teacher.get().getEnabled());
      return "/user/user";
    }
  }

  @GetMapping({"/teacher/profile", "/teacher/profile.html"})
  public String privateProfile(Model model) {
    log.info("privateProfile()");
    model.addAttribute("userEditForm", new User());
    return "/teacher/profile";
  }


  @PostMapping({"/teacher/editprofile", "/teacher/editprofile.html"})
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

    return "/teacher/profile";
  }


  @GetMapping({"/teacher/calendar", "/teacher/calendar.html"})
  public String privateCalendar(Model model, HttpSession session) {
    log.info("privateCalendar()");

    log.info("USER: " + session.getAttribute("user"));


    model.addAttribute("listLesson",
        myTeacherService.findAllLessonTeacher(teacher.get().getTeacherid()));

    log.info("Lesson Schedule Teacher"
        + myTeacherService.findAllLessonTeacher(teacher.get().getTeacherid()));

    return "/teacher/calendar";
  }
}


