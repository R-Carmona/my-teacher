package com.rubencarmona.myteacher.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.rubencarmona.myteacher.controller.RegisterController;

/**
 * Clase CustomAuthenticationSuccessHandler * Dentro de una aplicación Spring MVC, para poder
 * redirigir a una determinad página dependiendo de cada ROL, necesimatos un
 * AuthenticationSuccessHandler, el cual sobreescribe la configuración por defecto.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private static final Logger log = LogManager.getLogger(RegisterController.class);
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {

    /**
     * Comprobamos si el usuario logueado es un administrador.
     */
    if (authentication.getAuthorities().stream()
        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {

      redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin/admin/");
      log.info("redirectAdmin()");

    } else if (authentication.getAuthorities().stream()
        .anyMatch(role -> role.getAuthority().equals("ROLE_TEACHER"))) {

      redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/teacher/teacher/");
      log.info("redirectTeacher()");

    } else {

      redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/user/user/");
      log.info("redirectTeacher()");

    } ;

  }
}
