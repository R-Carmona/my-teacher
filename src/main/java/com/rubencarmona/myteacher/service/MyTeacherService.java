package com.rubencarmona.myteacher.service;

import org.springframework.stereotype.Service;
import com.rubencarmona.myteacher.dao.MyTeacherDAO;

/**
 * Clase MyTeacherService * Otenemos todos los accesos a DAO desde esta clase de servicio. La cual
 * implemente MyTeacherDAO.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Service
public class MyTeacherService extends MyTeacherDAO {
}
