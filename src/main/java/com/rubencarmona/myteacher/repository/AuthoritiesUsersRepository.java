package com.rubencarmona.myteacher.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rubencarmona.myteacher.domain.AuthoritiesUsers;

@Repository
public interface AuthoritiesUsersRepository extends JpaRepository<AuthoritiesUsers, Integer> {

  public Optional<AuthoritiesUsers> findByUserid(int userId);

  public Optional<AuthoritiesUsers> findByUseridAndAuthorityid(int userId, int authorityId);

}
