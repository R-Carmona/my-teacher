package com.rubencarmona.myteacher.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


/**
 * Clase LoginController * Gestiona todas las peticones web de la zona del login.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@SessionAttributes({"currentUser"})
@Controller
public class LoginController {

  private static final Logger log = LogManager.getLogger(LoginController.class);

  @GetMapping({"/login.html", "/login"})
  public String login(Model model) {
    log.info("login()");
    return "login";
  }

  @GetMapping("/logout")
  public String logout(SessionStatus sessionStatus) {
    log.info("logout()");
    SecurityContextHolder.getContext().setAuthentication(null);

    sessionStatus.setComplete();
    return "redirect:/index";
  }


}
