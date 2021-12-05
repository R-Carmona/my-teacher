package com.rubencarmona.myteacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rubencarmona.myteacher.domain.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
