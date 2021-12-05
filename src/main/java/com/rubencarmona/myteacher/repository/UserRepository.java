package com.rubencarmona.myteacher.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rubencarmona.myteacher.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  public Optional<User> findByMail(String mail);

}
