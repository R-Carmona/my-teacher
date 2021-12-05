package com.rubencarmona.myteacher.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.rubencarmona.myteacher.domain.Authority;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.repository.UserRepository;

/**
 * Clase UserDetailsServiceImpl * Clase que gestiona y comprueba las autoridades de la aplicación.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  User appUser;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    /**
     * Buscamos el usuario por su nombre, si no existe lanzara un mensaje de error.
     */
    appUser = userRepository.findByMail(username)
        .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario introducido."));

    /**
     * Mapeamos nuestra autoridades en nuestra base de datos, para el usuario.
     */
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    for (Authority authority : appUser.getAuthority()) {

      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
      authorities.add(grantedAuthority);

      // Comprobación del usuario y su rol o roles.
      System.out.println(authorities);
      System.out.println(appUser);
    }

    /**
     * Crearemos el userDetails de la sessión para Spring Security, con los datos de nuestro usuario
     * logueado.
     */
    UserDetails user = new org.springframework.security.core.userdetails.User(appUser.getMail(),
        appUser.getPassword(), true, true, true, true, authorities);
    return user;
  }

  public User getAppUser() {
    return appUser;
  }

}


