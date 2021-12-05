package com.rubencarmona.myteacher.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Clase MainController * Gestión de los controladores genéricos y de acceso público.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Controller
public class MainController {

  private static final Logger log = LogManager.getLogger(MainController.class);

  @GetMapping({"/", "/index", "/index.html"})
  public String index(Model model) {
    return "index";
  }

  @GetMapping({"/error", "/error.html"})
  public String error(@ModelAttribute("message") String message) {
    log.info("error()" + message);



    return "error";
  }

  @GetMapping({"/contact.html", "/contact"})
  public String contact() {
    return "contact";
  };

  @GetMapping({"/about.html", "/about"})
  public String about() {
    return "about";
  };

  @GetMapping({"/level.html", "/level"})
  public String level() {
    return "level";
  };

  @GetMapping({"/method.html", "/method"})
  public String method() {
    return "method";
  };

  @GetMapping({"/prices.html", "/prices"})
  public String prices() {
    return "prices";
  };

  @GetMapping({"/teachers.html", "/teachers"})
  public String teacher() {
    return "teachers";
  };

  @GetMapping({"/privacypolicy", "/privacypolicy.html"})
  public String privacypolicy() {
    return "privacypolicy";
  }

}
