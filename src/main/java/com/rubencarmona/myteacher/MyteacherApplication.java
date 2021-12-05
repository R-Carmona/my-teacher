package com.rubencarmona.myteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Clase Main Inicializa toda la aplicaación. *
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@SpringBootApplication
public class MyteacherApplication {

  public static void main(String[] args) {

    SpringApplication.run(MyteacherApplication.class, args);
  }

}
