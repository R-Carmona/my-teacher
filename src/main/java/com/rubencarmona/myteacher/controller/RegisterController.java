package com.rubencarmona.myteacher.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rubencarmona.myteacher.domain.StudyPlan;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.domain.UserStudyPlan;
import com.rubencarmona.myteacher.service.MyTeacherService;

/**
 * Clase RegisterController * Gestiona los controladores del registro de usuarios.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Controller
public class RegisterController {

  private static final Logger log = LogManager.getLogger(RegisterController.class);

  @Autowired
  MyTeacherService myTecherService;


  @GetMapping({"/register.html", "register"})
  public String register(Model model) {

    model.addAttribute("userForm", new User());
    return "register";
  }


  @PostMapping({"/addRegister.html", "addRegister"})
  public String registerPost(@ModelAttribute("userForm") User user, Model model,
      final RedirectAttributes redirectAttributes) {
    log.info("addRegister()");

    log.info("Find User Mail: " + myTecherService.findByMailUser(user.getMail()));

    try {

      String name = user.getName();
      String surname = user.getSurname();
      String address = user.getAddress();
      int phone = user.getPhone();
      String mail = user.getMail();
      String dni_nie = user.getDniNie();
      String pwd = user.getPassword();
      String bankaccount = user.getBankaccountnumber();

      log.info("USER REGISTER:" + user);

      myTecherService
          .saveUser(new User(name, surname, address, dni_nie, mail, pwd, phone, bankaccount));

      User newUser = myTecherService.findByMailUser(mail).get();
      StudyPlan newStudyPlan = myTecherService.findByIdStudyPlan(39).get();
      myTecherService.saveUserStudyPlan(new UserStudyPlan(newStudyPlan, newUser));

      return "redirect:/login";

    } catch (Exception e) {

      log.info("User Exists. ");

      String message = "El correo electrónico ya existe.";
      redirectAttributes.addFlashAttribute("message", message);
      return "redirect:/error.html";

    }


  }

}


