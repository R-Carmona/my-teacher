package com.rubencarmona.myteacher.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase para crear nuestro hash de la clave para nuestros usuarios, he introducirlo en nuestra base
 * de datos.
 * 
 * @author Rubén Carmona García.
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * 
 */
public class CreatePassword {

  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    System.out.println("Clave encriptada para usur:");
    System.out.println(bCryptPasswordEncoder.encode("user"));
    System.out.println("Clave encriptada para admin:");
    System.out.println(bCryptPasswordEncoder.encode("admin"));
  }

}

