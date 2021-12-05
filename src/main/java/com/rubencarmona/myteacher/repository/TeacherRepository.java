package com.rubencarmona.myteacher.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rubencarmona.myteacher.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

  public Optional<Teacher> findByUserid(Integer id);

}
