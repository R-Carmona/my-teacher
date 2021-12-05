package com.rubencarmona.myteacher.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.rubencarmona.myteacher.service.UserDetailsServiceImpl;

/**
 * Clase para la configuración de todo el entorno de nuestra seguridad. Gesionando las rutas a
 * mapear y donde tienen permisos cada uno de los roles. Activa la seguridad de Spring con la
 * anotación @EnableWebSecurity.
 * 
 * @author Rubén Carmona García.
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target= "_blank">www.rubencarmona.com</a>
 *      (debug = true)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Logger log = LogManager.getLogger(SecurityConfig.class);

  @Autowired
  private CustomAuthenticationSuccessHandler successHandler;


  String[] resources = new String[] {"/index.html", "/index", "/about.html", "/about",
      "/contact.html", "/contact", "/level.html", "/level", "/login.html", "/login", "/method.html",
      "/method", "/prices.html", "/prices", "/register.html", "/register", "/privacypolicy.html",
      "/privacypolicy", "/teachers.html", "/teachers", "/error.html", "/error", "/", "/css/**",
      "/fonts/**", "/img/**", "/js/**", "/fullcalendar/**", "/addRegister.html", "addRegister"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers(resources).permitAll().antMatchers("/admin/**")
        .access("hasRole('ADMIN')").antMatchers("/teacher/**").access("hasRole('TEACHER')")
        .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')").anyRequest()
        .authenticated().and().formLogin().loginPage("/login").permitAll()
        .successHandler(successHandler).failureUrl("/login?error").usernameParameter("username")
        .passwordParameter("password").and().logout().permitAll().logoutSuccessUrl("/logout").and()
        .exceptionHandling().accessDeniedPage("/notauthorized");

    log.info("configureSecurity()");

  }


  BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * Encriptador de nuestras contraseñas, cada una se podrá configurar la fuerza de encriptado, se
   * ha optado por poner 4, un valor bajo para el ejemplo y siempre el mismo para todos los casos de
   * uso de nuetra aplicación.
   * 
   * @return
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {

    bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    return bCryptPasswordEncoder;
  }

  /**
   * Creamos un usuario autenticado.
   */
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  /**
   * 
   * @param auth
   * @throws Exception Error de autenticación del usuario.
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

}
