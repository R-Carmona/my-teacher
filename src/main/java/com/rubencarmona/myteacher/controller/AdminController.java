package com.rubencarmona.myteacher.controller;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.rubencarmona.myteacher.domain.AuthoritiesUsers;
import com.rubencarmona.myteacher.domain.Authority;
import com.rubencarmona.myteacher.domain.Lesson;
import com.rubencarmona.myteacher.domain.StudyPlan;
import com.rubencarmona.myteacher.domain.Teacher;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.domain.UserStudyPlan;
import com.rubencarmona.myteacher.service.MyTeacherService;

/**
 * Clase AdminController * Gestiona todas las peticones web de la zona del administrador.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Controller
public class AdminController {

  private static final Logger log = LogManager.getLogger(AdminController.class);

  @Autowired
  MyTeacherService myTecherService;


  @GetMapping({"/admin/admin", "/admin/admin.html"})
  public String adminUser(Model model) {
    log.info("adminUser()");
    model.addAttribute("today", new Date());

    List<User> listUser = myTecherService.findAllUser();
    model.addAttribute("listUser", listUser);



    return "/admin/admin";
  }

  @GetMapping({"/admin/lesson", "/admin/lesson.html"})
  public String lesson(Model model) {
    log.info("adminLesson()");

    List<Lesson> listLesson = myTecherService.findAllLesson();
    model.addAttribute("listLesson", listLesson);


    return "/admin/lesson";
  }

  @GetMapping({"/admin/listteachers", "/admin/listteachers.html"})
  public String listteacher(Model model) {
    log.info("adminListteachers()");

    List<Teacher> listTeachers = myTecherService.findAllTeacher();
    model.addAttribute("listTeachers", listTeachers);


    return "/admin/listteachers";
  }

  @GetMapping({"/admin/role", "/admin/role.html"})
  public String role(Model model) {
    log.info("adminRole()");
    List<Authority> listRole = myTecherService.findAllAuthority();
    model.addAttribute("listRole", listRole);


    return "/admin/role";
  }

  @GetMapping({"/admin/studyplan", "/admin/studyplan.html"})
  public String studyplan(Model model) {
    log.info("adminStudyplan()");
    List<StudyPlan> listStudyPlan = myTecherService.findAllStudyPlan();
    model.addAttribute("listStudyPlan", listStudyPlan);


    return "/admin/studyplan";
  }

  @GetMapping({"/admin/userstudyplan", "/admin/userstudyplan.html"})
  public String userstudiplan(Model model) {
    log.info("userstudyplan()");
    List<UserStudyPlan> listUserStudyPlan = myTecherService.findAllUserStudyPlan();
    model.addAttribute("listUserStudyPlan", listUserStudyPlan);

    return "/admin/userstudyplan";
  }

  @GetMapping({"/admin/roleuser", "/admin/roleuser.html"})
  public String roleuser(Model model) {
    log.info("roleuser()");
    List<AuthoritiesUsers> listRoleUser = myTecherService.findAllAuthoritiesUsers();


    model.addAttribute("listRoleUser", listRoleUser);

    return "/admin/roleuser";
  }



  /////////////////////// EDIT AND DISABLED USER ////////////////////////////////////

  @GetMapping({"/admin/disabled/user/{id}"})
  public String deleteUser(@PathVariable("id") Integer id) {
    log.info("deleteUser()");

    User user = myTecherService.findByIdUser(id).get();

    if (user.getEnabled() == 0) {
      user.setEnabled((byte) 1);
      log.info("User Enabled");

    } else {
      user.setEnabled((byte) 0);
      log.info("User Disabled");
    }

    myTecherService.updateUser(user);
    log.info("Save User");

    return "redirect:/admin/admin";
  }

  @GetMapping({"/admin/edit/user/{id}"})
  public String editUser(@PathVariable("id") Integer id) {
    log.info("editUser()");

    // myTecherService.saveUser(user);

    return "redirect:/admin/admin";
  }


  /////////////////////// EDIT AND DISABLED TEACHER ////////////////////////////////////

  @GetMapping({"/admin/disabled/teacher/{id}"})
  public String disabledTeacher(@PathVariable("id") Integer id) {
    log.info("disableTeacher()");

    Teacher teacher = myTecherService.findByIdTeacher(id).get();

    if (teacher.getEnabled() == 0) {
      teacher.setEnabled((byte) 1);
      log.info("Teacher Enabled");

    } else {
      teacher.setEnabled((byte) 0);
      log.info("Teacher Disabled");
    }

    myTecherService.updateTeacher(teacher);
    log.info("Save User");

    return "redirect:/admin/listteachers";
  }

  @GetMapping({"/admin/add/teacher/{id}"})
  public String addTeacher(@PathVariable("id") Integer id) {
    log.info("disableTeacher()");

    User user = myTecherService.findByIdUser(id).get();
    Teacher teacher = new Teacher(user.getName(), user.getUserid(), user.getSurname());

    myTecherService.saveTeacher(teacher);
    myTecherService.saveAuthoritiesUsers(new AuthoritiesUsers(user.getUserid(), 3));


    log.info("Save User");

    return "redirect:/admin/listteachers";
  }

  @GetMapping({"/admin/delete/teacher/{id}"})
  public String deleteTeacher(@PathVariable("id") Integer id) {
    log.info("disableTeacher()");

    Teacher teacher = myTecherService.findByIdTeacher(id).get();

    AuthoritiesUsers authoritiesUsers =
        myTecherService.findByIdAuthoritiesUsersAndAndauthorityid(teacher.getUserid(), 3).get();

    log.info("Get Authority Teacher: " + authoritiesUsers);

    myTecherService.deleteByIdTeacher(id);

    myTecherService.deleteAuthoritiesUsers(authoritiesUsers);


    log.info("Delete Teacher ");

    return "redirect:/admin/listteachers";
  }



}
