package com.rubencarmona.myteacher.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * Clase de configuración de la aplicación web. Muchas de las anotaciones y la configuración de la
 * conexión a la BBDD, se puede configurara desde el archivo application.properties de Spring .
 * Respecto a las anotaciones el propio Spring Boot y su anotación @SpringBootApplication hacen el
 * resto, no sería necesario indicar ni el archivo de propiedades y los repositorios a escanear...
 * 
 * @author Rubén Carmona García.
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * 
 */
@Configuration
@EnableJpaAuditing
@ComponentScan(basePackages = "com.rubencarmona")
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories("com.rubencarmona.myteacher.repository")
@EnableTransactionManagement
public class Config {

  @Autowired
  private Environment environment;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getRequiredProperty("bbdd.url"));
    dataSource.setUsername(environment.getRequiredProperty("bbdd.username"));
    dataSource.setPassword(environment.getRequiredProperty("bbdd.password"));
    return dataSource;
  }


  @Bean
  public EntityManagerFactory entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.rubencarmona.myteacher.domain");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();
    return factory.getObject();

  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }

}
